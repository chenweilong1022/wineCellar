package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import io.renren.common.validator.Assert;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarMemberDbService;
import io.renren.modules.cellar.service.CellarOrderDbService;
import org.apache.commons.codec.digest.DigestUtils;
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

import io.renren.modules.cellar.dao.CellarMemberBalanceChangeRecordDbDao;
import io.renren.modules.cellar.entity.CellarMemberBalanceChangeRecordDbEntity;
import io.renren.modules.cellar.service.CellarMemberBalanceChangeRecordDbService;


@Service("cellarMemberBalanceChangeRecordDbService")
public class CellarMemberBalanceChangeRecordDbServiceImpl extends ServiceImpl<CellarMemberBalanceChangeRecordDbDao, CellarMemberBalanceChangeRecordDbEntity> implements CellarMemberBalanceChangeRecordDbService {

    @Autowired
    private CellarMemberDbService cellarMemberDbService;
    @Autowired
    private CellarOrderDbService cellarOrderDbService;
    @Autowired
    private CellarMemberBalanceChangeRecordDbService cellarMemberBalanceChangeRecordDbService;

    @Override
    public PageUtils queryPage(CellarMemberBalanceChangeRecordDbEntity cellarMemberBalanceChangeRecordDb) {
        IPage<CellarMemberBalanceChangeRecordDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberBalanceChangeRecordDbEntity>(cellarMemberBalanceChangeRecordDb).getPage(),
                new QueryWrapper<CellarMemberBalanceChangeRecordDbEntity>().lambda()
                .eq(CellarMemberBalanceChangeRecordDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(ObjectUtil.isNotNull(cellarMemberBalanceChangeRecordDb.getMemberId()),CellarMemberBalanceChangeRecordDbEntity::getMemberId, cellarMemberBalanceChangeRecordDb.getMemberId())
                .eq(CellarMemberBalanceChangeRecordDbEntity::getRecordStatus, Constants.RECORDSTATUS.TWO.getKey())
                .orderByDesc(CellarMemberBalanceChangeRecordDbEntity::getPaymentTime)
        );

        return new PageUtils(page);
    }

    /**
     * 余额支付
     * @param cellarMemberDbEntity
     * @param payOrderAmount
     * @param orderNo
     */
    @Override
    public void balancePay(
            CellarMemberDbEntity cellarMemberDbEntity,
            BigDecimal payOrderAmount,
            String orderNo,
            String payPassword
    ) {
        /**
         * 判断余额
         */

        Assert.isBlank(payPassword,"支付密码不能为空");
        Assert.isTrue(payOrderAmount.compareTo(cellarMemberDbEntity.getBalance()) > 0,"余额不足");
        Assert.isTrue(!DigestUtils.sha256Hex(payPassword).equals(cellarMemberDbEntity.getPayPassword()),"支付密码错误");
        BigDecimal changeBalance = payOrderAmount.multiply(BigDecimal.valueOf(-1));//变动金额
        BigDecimal beforeBalance = cellarMemberDbEntity.getBalance();//当前金额
        BigDecimal afterBalance = beforeBalance.add(changeBalance);//充值之后金额
        /**
         *修改用户余额
         */
        cellarMemberDbEntity.setBalance(afterBalance);
        cellarMemberDbService.updateById(cellarMemberDbEntity);
        /**
         * 增加余额记录
         */
        CellarMemberBalanceChangeRecordDbEntity cellarMemberBalanceChangeRecordDbEntity = new CellarMemberBalanceChangeRecordDbEntity();
        cellarMemberBalanceChangeRecordDbEntity.setMemberId(cellarMemberDbEntity.getMemberId());
        cellarMemberBalanceChangeRecordDbEntity.setChangeBalance(changeBalance);
        cellarMemberBalanceChangeRecordDbEntity.setBeforeBalance(beforeBalance);
        cellarMemberBalanceChangeRecordDbEntity.setAfterBalance(afterBalance);
        cellarMemberBalanceChangeRecordDbEntity.setCreateTime(new Date());
        cellarMemberBalanceChangeRecordDbEntity.setState(Constants.STATE.zero.getKey());
        cellarMemberBalanceChangeRecordDbEntity.setChangeType(Constants.CHANGETYPE.TWO.getKey());
        cellarMemberBalanceChangeRecordDbEntity.setChangeDesc(Constants.CHANGETYPE.TWO.getValue());
        cellarMemberBalanceChangeRecordDbEntity.setPaymentTime(new Date());
        cellarMemberBalanceChangeRecordDbEntity.setRecordStatus(Constants.RECORDSTATUS.TWO.getKey());
        cellarMemberBalanceChangeRecordDbEntity.setOrderNo(orderNo);
        cellarMemberBalanceChangeRecordDbEntity.setMethodPayment(Constants.METHODPAYMENT.BALANCE.getKey());
        cellarMemberBalanceChangeRecordDbService.save(cellarMemberBalanceChangeRecordDbEntity);
        cellarOrderDbService.paySuccess(orderNo);
    }

    /**
     * 余额退款
     * @param cellarMemberDbEntity
     * @param payOrderAmount
     * @param orderNo
     */
    @Override
    public void balanceRefund(
            CellarMemberDbEntity cellarMemberDbEntity,
            BigDecimal payOrderAmount,
            String orderNo,
            String payPassword
    ) {
        /**
         * 判断余额
         */

//        Assert.isNull(StringUtils.isBlank(payPassword),"支付密码不能为空");
//        Assert.isTrue(payOrderAmount.compareTo(cellarMemberDbEntity.getBalance()) > 0,"余额不足");
//        Assert.isTrue(!DigestUtils.sha256Hex(payPassword).equals(cellarMemberDbEntity.getPayPassword()),"支付密码错误");
        BigDecimal changeBalance = payOrderAmount.multiply(BigDecimal.valueOf(1));//变动金额
        BigDecimal beforeBalance = cellarMemberDbEntity.getBalance();//当前金额
        BigDecimal afterBalance = beforeBalance.add(changeBalance);//充值之后金额
        /**
         *修改用户余额
         */
        cellarMemberDbEntity.setBalance(afterBalance);
        cellarMemberDbService.updateById(cellarMemberDbEntity);
        /**
         * 增加余额记录
         */
        CellarMemberBalanceChangeRecordDbEntity cellarMemberBalanceChangeRecordDbEntity = new CellarMemberBalanceChangeRecordDbEntity();
        cellarMemberBalanceChangeRecordDbEntity.setMemberId(cellarMemberDbEntity.getMemberId());
        cellarMemberBalanceChangeRecordDbEntity.setChangeBalance(changeBalance);
        cellarMemberBalanceChangeRecordDbEntity.setBeforeBalance(beforeBalance);
        cellarMemberBalanceChangeRecordDbEntity.setAfterBalance(afterBalance);
        cellarMemberBalanceChangeRecordDbEntity.setCreateTime(new Date());
        cellarMemberBalanceChangeRecordDbEntity.setState(Constants.STATE.zero.getKey());
        cellarMemberBalanceChangeRecordDbEntity.setChangeType(Constants.CHANGETYPE.THREE.getKey());
        cellarMemberBalanceChangeRecordDbEntity.setChangeDesc(Constants.CHANGETYPE.THREE.getValue());
        cellarMemberBalanceChangeRecordDbEntity.setPaymentTime(new Date());
        cellarMemberBalanceChangeRecordDbEntity.setRecordStatus(Constants.RECORDSTATUS.TWO.getKey());
        cellarMemberBalanceChangeRecordDbEntity.setOrderNo(orderNo);
        cellarMemberBalanceChangeRecordDbEntity.setMethodPayment(Constants.METHODPAYMENT.BALANCE.getKey());
        cellarMemberBalanceChangeRecordDbService.save(cellarMemberBalanceChangeRecordDbEntity);
    }

}
