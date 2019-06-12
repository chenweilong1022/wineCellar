package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import io.renren.common.validator.Assert;
import io.renren.modules.cellar.entity.CellarMemberCardBalanceChangeRecordDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarMemberDbService;
import io.renren.modules.cellar.service.CellarOrderDbService;
import org.apache.commons.codec.digest.DigestUtils;
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

import io.renren.modules.cellar.dao.CellarMemberIntegralChangeRecordDbDao;
import io.renren.modules.cellar.entity.CellarMemberIntegralChangeRecordDbEntity;
import io.renren.modules.cellar.service.CellarMemberIntegralChangeRecordDbService;


@Service("cellarMemberIntegralChangeRecordDbService")
public class CellarMemberIntegralChangeRecordDbServiceImpl extends ServiceImpl<CellarMemberIntegralChangeRecordDbDao, CellarMemberIntegralChangeRecordDbEntity> implements CellarMemberIntegralChangeRecordDbService {

    @Autowired
    private CellarMemberDbService cellarMemberDbService;
    @Autowired
    private CellarMemberIntegralChangeRecordDbService cellarMemberIntegralChangeRecordDbService;
    @Autowired
    private CellarOrderDbService cellarOrderDbService;

    @Override
    public PageUtils queryPage(CellarMemberIntegralChangeRecordDbEntity cellarMemberIntegralChangeRecordDb) {
        IPage<CellarMemberIntegralChangeRecordDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberIntegralChangeRecordDbEntity>(cellarMemberIntegralChangeRecordDb).getPage(),
                new QueryWrapper<CellarMemberIntegralChangeRecordDbEntity>().lambda()
                .eq(CellarMemberIntegralChangeRecordDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(ObjectUtil.isNotNull(cellarMemberIntegralChangeRecordDb.getMemberId()),CellarMemberIntegralChangeRecordDbEntity::getMemberId,cellarMemberIntegralChangeRecordDb.getMemberId())
        );

        return new PageUtils(page);
    }

    /**
     * 积分购买商品
     * @param cellarMemberDbEntity
     * @param integralPrice
     * @param orderNo
     */
    @Override
    public void integralPay(
            CellarMemberDbEntity cellarMemberDbEntity,
            BigDecimal integralPrice,
            String orderNo,
            String payPassword
    ) {
        /**
         * 判断储值卡余额
         */
        Assert.isBlank(payPassword,"支付密码不能为空");
        Assert.isTrue(integralPrice.compareTo(cellarMemberDbEntity.getIntegral()) > 0,"用户酒币不足");
        Assert.isTrue(!DigestUtils.sha256Hex(payPassword).equals(cellarMemberDbEntity.getPayPassword()),"支付密码错误");
        BigDecimal changeIntegral = integralPrice.multiply(BigDecimal.valueOf(-1));//变动积分
        BigDecimal beforeIntegral = cellarMemberDbEntity.getIntegral();//当前积分
        BigDecimal afterIntegral = beforeIntegral.add(changeIntegral);//充值之后金额
        /**
         *修改用户积分
         */
        cellarMemberDbEntity.setIntegral(afterIntegral);
        cellarMemberDbService.updateById(cellarMemberDbEntity);
        /**
         * 增加储值卡余额记录
         */

        CellarMemberIntegralChangeRecordDbEntity cellarMemberIntegralChangeRecordDbEntity = new CellarMemberIntegralChangeRecordDbEntity();
        cellarMemberIntegralChangeRecordDbEntity.setCreateTime(new Date());
        cellarMemberIntegralChangeRecordDbEntity.setState(Constants.STATE.zero.getKey());
        cellarMemberIntegralChangeRecordDbEntity.setOrderId(null);
        cellarMemberIntegralChangeRecordDbEntity.setOrderNo(orderNo);
        cellarMemberIntegralChangeRecordDbEntity.setChangeType(Constants.CHANGETYPE.FOUR.getKey());
        cellarMemberIntegralChangeRecordDbEntity.setChangeDesc(Constants.CHANGETYPE.FOUR.getValue());
        cellarMemberIntegralChangeRecordDbEntity.setMemberId(cellarMemberDbEntity.getMemberId());
        cellarMemberIntegralChangeRecordDbEntity.setChangeIntegral(changeIntegral);
        cellarMemberIntegralChangeRecordDbEntity.setBeforeChange(beforeIntegral);
        cellarMemberIntegralChangeRecordDbEntity.setAfterIntegral(afterIntegral);
        cellarMemberIntegralChangeRecordDbService.save(cellarMemberIntegralChangeRecordDbEntity);

        cellarOrderDbService.paySuccess(orderNo);
    }

}
