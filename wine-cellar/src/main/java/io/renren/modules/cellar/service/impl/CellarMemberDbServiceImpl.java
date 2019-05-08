package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.common.constants.Constants;
import io.renren.common.exception.RRException;
import io.renren.common.validator.Assert;
import io.renren.modules.cellar.entity.CellarMemberBalanceChangeRecordDbEntity;
import io.renren.modules.cellar.entity.CellarMemberCardBalanceChangeRecordDbEntity;
import io.renren.modules.cellar.entity.CellarStoreDbEntity;
import io.renren.modules.cellar.service.CellarMemberBalanceChangeRecordDbService;
import io.renren.modules.cellar.service.CellarMemberCardBalanceChangeRecordDbService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberDbDao;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarMemberDbService;
import org.springframework.transaction.annotation.Transactional;


@Service("cellarMemberDbService")
public class CellarMemberDbServiceImpl extends ServiceImpl<CellarMemberDbDao, CellarMemberDbEntity> implements CellarMemberDbService {

    @Autowired
    private CellarMemberBalanceChangeRecordDbService cellarMemberBalanceChangeRecordDbService;
    @Autowired
    private CellarMemberCardBalanceChangeRecordDbService cellarMemberCardBalanceChangeRecordDbService;

    @Override
    public PageUtils queryPage(CellarMemberDbEntity cellarMemberDbEntity) {
        IPage<CellarMemberDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberDbEntity>(cellarMemberDbEntity).getPage(),
                new QueryWrapper<CellarMemberDbEntity>().lambda()
                        .notIn(CellarMemberDbEntity::getState, Constants.STATE.funine.getKey())
                        .eq(ObjectUtil.isNotNull(cellarMemberDbEntity.getGender()),CellarMemberDbEntity::getGender,cellarMemberDbEntity.getGender())
                        .eq(ObjectUtil.isNotNull(cellarMemberDbEntity.getState()),CellarMemberDbEntity::getState,cellarMemberDbEntity.getState())
                        .apply(StringUtils.isNotBlank(cellarMemberDbEntity.getKey().toString()),"concat(mobile_phone,',',nickname) like concat('%','"+cellarMemberDbEntity.getKey()+"','%')")
        );
        return new PageUtils(page);
    }

    @Override
    public CellarMemberDbEntity login(CellarMemberDbEntity cellarMemberDbEntity) {
        /**
         * 根据手机号查询账号
         */
        CellarMemberDbEntity cellarMemberDbEntityByPhone = baseMapper.selectOne(new QueryWrapper<CellarMemberDbEntity>()
                .lambda().eq(CellarMemberDbEntity::getMobilePhone, cellarMemberDbEntity.getMobilePhone()));
        /**
         * 判断是否为空
         */
        Assert.isNull(cellarMemberDbEntityByPhone, "手机号未注册");
        /**
         * 校验密码
         */
        if (!DigestUtils.sha256Hex(cellarMemberDbEntity.getPassword()).equals(cellarMemberDbEntityByPhone.getPassword())) {
            throw new RRException("账号或密码错误");
        }
        return cellarMemberDbEntityByPhone;
    }

    @Override
    @Transactional
    public void rechargeBalanceSuccess(String outtradeno) {

        CellarMemberBalanceChangeRecordDbEntity cellarMemberBalanceChangeRecordDbEntity = cellarMemberBalanceChangeRecordDbService.getOne(new QueryWrapper<CellarMemberBalanceChangeRecordDbEntity>().lambda()
                .eq(CellarMemberBalanceChangeRecordDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(CellarMemberBalanceChangeRecordDbEntity::getOrderNo, outtradeno)
        );
        Assert.isNull(cellarMemberBalanceChangeRecordDbEntity,"支付异常");
        Assert.isTrue(cellarMemberBalanceChangeRecordDbEntity.getRecordStatus().equals(Constants.RECORDSTATUS.TWO.getKey()),"重复调用");
        /**
         * 充值用户
         */
        CellarMemberDbEntity cellarMemberDbEntity = baseMapper.selectOne(new QueryWrapper<CellarMemberDbEntity>().lambda()
                .eq(CellarMemberDbEntity::getMemberId, cellarMemberBalanceChangeRecordDbEntity.getMemberId())
                .eq(CellarMemberDbEntity::getState, Constants.STATE.zero.getKey())
        );
        Assert.isNull(cellarMemberDbEntity,"该用户不存在");
        BigDecimal changeBalance = cellarMemberBalanceChangeRecordDbEntity.getChangeBalance();//充值金额
        BigDecimal beforeBalance = cellarMemberDbEntity.getBalance();//当前金额
        BigDecimal afterBalance = beforeBalance.add(changeBalance);//充值之后金额
        /**
         * 修改充值记录
         */
        cellarMemberBalanceChangeRecordDbEntity.setBeforeBalance(beforeBalance);
        cellarMemberBalanceChangeRecordDbEntity.setAfterBalance(afterBalance);
        cellarMemberBalanceChangeRecordDbEntity.setPaymentTime(new Date());
        cellarMemberBalanceChangeRecordDbEntity.setRecordStatus(Constants.RECORDSTATUS.TWO.getKey());
        cellarMemberBalanceChangeRecordDbService.updateById(cellarMemberBalanceChangeRecordDbEntity);
        /**
         * 修改用户金额
         */
        cellarMemberDbEntity.setBalance(afterBalance);
        baseMapper.updateById(cellarMemberDbEntity);
    }

    @Override
    public void rechargeCardBalanceSuccess(String outtradeno) {
        /**
         * 查询充值记录
         */
        CellarMemberCardBalanceChangeRecordDbEntity memberCardBalanceChangeRecordDbEntity = cellarMemberCardBalanceChangeRecordDbService.getOne(new QueryWrapper<CellarMemberCardBalanceChangeRecordDbEntity>().lambda()
                .eq(CellarMemberCardBalanceChangeRecordDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(CellarMemberCardBalanceChangeRecordDbEntity::getOrderNo, outtradeno)
        );
        /**
         *判断是否重复支付
         */
        Assert.isNull(memberCardBalanceChangeRecordDbEntity,"支付异常");
        Assert.isTrue(memberCardBalanceChangeRecordDbEntity.getRecordStatus().equals(Constants.RECORDSTATUS.TWO.getKey()),"重复调用");
        /**
         * 充值用户
         */
        CellarMemberDbEntity cellarMemberDbEntity = baseMapper.selectOne(new QueryWrapper<CellarMemberDbEntity>().lambda()
                .eq(CellarMemberDbEntity::getMemberId, memberCardBalanceChangeRecordDbEntity.getMemberId())
                .eq(CellarMemberDbEntity::getState, Constants.STATE.zero.getKey())
        );
        Assert.isNull(cellarMemberDbEntity,"该用户不存在");
        BigDecimal changeCardBalance = memberCardBalanceChangeRecordDbEntity.getChangeCardBalance();//充值金额
        BigDecimal beforeCardBalance = cellarMemberDbEntity.getCardBalance();//当前金额
        BigDecimal afterCardBalance = beforeCardBalance.add(changeCardBalance);//充值之后金额
        /**
         * 修改充值记录
         */
        memberCardBalanceChangeRecordDbEntity.setBeforeCardBalance(beforeCardBalance);
        memberCardBalanceChangeRecordDbEntity.setAfterCardBalance(afterCardBalance);
        memberCardBalanceChangeRecordDbEntity.setPaymentTime(new Date());
        memberCardBalanceChangeRecordDbEntity.setRecordStatus(Constants.RECORDSTATUS.TWO.getKey());
        cellarMemberCardBalanceChangeRecordDbService.updateById(memberCardBalanceChangeRecordDbEntity);
        /**
         * 修改用户金额
         */
        cellarMemberDbEntity.setCardBalance(afterCardBalance);
        baseMapper.updateById(cellarMemberDbEntity);
    }

    @Override
    public PageUtils selectNearMember(CellarMemberDbEntity cellarMemberDbEntity) {
        IPage<CellarStoreDbEntity> page = baseMapper.selectNearMember(
                new Query<CellarMemberDbEntity>(cellarMemberDbEntity).getPage(),
                cellarMemberDbEntity
        );
        return new PageUtils(page);
    }

}
