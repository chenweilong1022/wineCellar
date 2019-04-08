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

import io.renren.modules.cellar.dao.CellarMemberCardBalanceChangeRecordDbDao;
import io.renren.modules.cellar.entity.CellarMemberCardBalanceChangeRecordDbEntity;
import io.renren.modules.cellar.service.CellarMemberCardBalanceChangeRecordDbService;


@Service("cellarMemberCardBalanceChangeRecordDbService")
public class CellarMemberCardBalanceChangeRecordDbServiceImpl extends ServiceImpl<CellarMemberCardBalanceChangeRecordDbDao, CellarMemberCardBalanceChangeRecordDbEntity> implements CellarMemberCardBalanceChangeRecordDbService {
    @Autowired
    private CellarMemberDbService cellarMemberDbService;
    @Autowired
    private CellarMemberCardBalanceChangeRecordDbService cellarMemberCardBalanceChangeRecordDbService;
    @Autowired
    private CellarOrderDbService cellarOrderDbService;

    @Override
    public PageUtils queryPage(CellarMemberCardBalanceChangeRecordDbEntity cellarMemberCardBalanceChangeRecordDb) {
        IPage<CellarMemberCardBalanceChangeRecordDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberCardBalanceChangeRecordDbEntity>(cellarMemberCardBalanceChangeRecordDb).getPage(),
                new QueryWrapper<CellarMemberCardBalanceChangeRecordDbEntity>().lambda()
                .eq(CellarMemberCardBalanceChangeRecordDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(ObjectUtil.isNotNull(cellarMemberCardBalanceChangeRecordDb.getMemberId()),CellarMemberCardBalanceChangeRecordDbEntity::getMemberId,cellarMemberCardBalanceChangeRecordDb.getMemberId())
                .eq(CellarMemberCardBalanceChangeRecordDbEntity::getRecordStatus, Constants.RECORDSTATUS.TWO.getKey())
                .orderByDesc(CellarMemberCardBalanceChangeRecordDbEntity::getPaymentTime)
        );

        return new PageUtils(page);
    }

    /**
     * 储值卡余额支付
     * @param cellarMemberDbEntity
     * @param payOrderAmount
     * @param orderNo
     */
    public void cardBalancePay(
            CellarMemberDbEntity cellarMemberDbEntity,
            BigDecimal payOrderAmount,
            String orderNo,
            String payPassword
    ) {
        /**
         * 判断储值卡余额
         */
        Assert.isBlank(payPassword,"支付密码不能为空");
        Assert.isTrue(payOrderAmount.compareTo(cellarMemberDbEntity.getCardBalance()) > 0,"储值卡余额不足");
        Assert.isTrue(!DigestUtils.sha256Hex(payPassword).equals(cellarMemberDbEntity.getPayPassword()),"支付密码错误");
        BigDecimal changeCardBalance = payOrderAmount.multiply(BigDecimal.valueOf(-1));//变动金额
        BigDecimal beforeCardBalance = cellarMemberDbEntity.getCardBalance();//当前金额
        BigDecimal afterCardBalance = beforeCardBalance.add(changeCardBalance);//充值之后金额
        /**
         *修改用户储值卡余额
         */
        cellarMemberDbEntity.setCardBalance(afterCardBalance);
        cellarMemberDbService.updateById(cellarMemberDbEntity);
        /**
         * 增加储值卡余额记录
         */
        CellarMemberCardBalanceChangeRecordDbEntity cellarMemberCardBalanceChangeRecordDbEntity = new CellarMemberCardBalanceChangeRecordDbEntity();
        cellarMemberCardBalanceChangeRecordDbEntity.setMemberId(cellarMemberDbEntity.getMemberId());
        cellarMemberCardBalanceChangeRecordDbEntity.setChangeCardBalance(changeCardBalance);
        cellarMemberCardBalanceChangeRecordDbEntity.setBeforeCardBalance(beforeCardBalance);
        cellarMemberCardBalanceChangeRecordDbEntity.setAfterCardBalance(afterCardBalance);
        cellarMemberCardBalanceChangeRecordDbEntity.setCreateTime(new Date());
        cellarMemberCardBalanceChangeRecordDbEntity.setState(Constants.STATE.zero.getKey());
        cellarMemberCardBalanceChangeRecordDbEntity.setChangeType(Constants.CHANGETYPE.TWO.getKey());
        cellarMemberCardBalanceChangeRecordDbEntity.setChangeDesc(Constants.CHANGETYPE.TWO.getValue());
        cellarMemberCardBalanceChangeRecordDbEntity.setPaymentTime(new Date());
        cellarMemberCardBalanceChangeRecordDbEntity.setRecordStatus(Constants.RECORDSTATUS.TWO.getKey());
        cellarMemberCardBalanceChangeRecordDbEntity.setOrderNo(orderNo);
        cellarMemberCardBalanceChangeRecordDbEntity.setMethodPayment(Constants.METHODPAYMENT.CARDBALANCE.getKey());
        cellarMemberCardBalanceChangeRecordDbService.save(cellarMemberCardBalanceChangeRecordDbEntity);
        cellarOrderDbService.paySuccess(orderNo);
    }

    /**
     * 储值卡余额退款
     * @param cellarMemberDbEntity
     * @param payOrderAmount
     * @param orderNo
     */
    public void cardBalanceRefund(
            CellarMemberDbEntity cellarMemberDbEntity,
            BigDecimal payOrderAmount,
            String orderNo,
            String payPassword
    ) {
        /**
         * 判断储值卡余额
         */

//        Assert.isNull(StringUtils.isBlank(payPassword),"支付密码不能为空");
//        Assert.isTrue(payOrderAmount.compareTo(cellarMemberDbEntity.getCardBalance()) > 0,"储值卡余额不足");
//        Assert.isTrue(!DigestUtils.sha256Hex(payPassword).equals(cellarMemberDbEntity.getPayPassword()),"支付密码错误");
        BigDecimal changeCardBalance = payOrderAmount.multiply(BigDecimal.valueOf(1));//变动金额
        BigDecimal beforeCardBalance = cellarMemberDbEntity.getCardBalance();//当前金额
        BigDecimal afterCardBalance = beforeCardBalance.add(changeCardBalance);//充值之后金额
        /**
         *修改用户储值卡余额
         */
        cellarMemberDbEntity.setCardBalance(afterCardBalance);
        cellarMemberDbService.updateById(cellarMemberDbEntity);
        /**
         * 增加储值卡余额记录
         */
        CellarMemberCardBalanceChangeRecordDbEntity cellarMemberCardBalanceChangeRecordDbEntity = new CellarMemberCardBalanceChangeRecordDbEntity();
        cellarMemberCardBalanceChangeRecordDbEntity.setMemberId(cellarMemberDbEntity.getMemberId());
        cellarMemberCardBalanceChangeRecordDbEntity.setChangeCardBalance(changeCardBalance);
        cellarMemberCardBalanceChangeRecordDbEntity.setBeforeCardBalance(beforeCardBalance);
        cellarMemberCardBalanceChangeRecordDbEntity.setAfterCardBalance(afterCardBalance);
        cellarMemberCardBalanceChangeRecordDbEntity.setCreateTime(new Date());
        cellarMemberCardBalanceChangeRecordDbEntity.setState(Constants.STATE.zero.getKey());
        cellarMemberCardBalanceChangeRecordDbEntity.setChangeType(Constants.CHANGETYPE.THREE.getKey());
        cellarMemberCardBalanceChangeRecordDbEntity.setChangeDesc(Constants.CHANGETYPE.THREE.getValue());
        cellarMemberCardBalanceChangeRecordDbEntity.setPaymentTime(new Date());
        cellarMemberCardBalanceChangeRecordDbEntity.setRecordStatus(Constants.RECORDSTATUS.TWO.getKey());
        cellarMemberCardBalanceChangeRecordDbEntity.setOrderNo(orderNo);
        cellarMemberCardBalanceChangeRecordDbEntity.setMethodPayment(Constants.METHODPAYMENT.CARDBALANCE.getKey());
        cellarMemberCardBalanceChangeRecordDbService.save(cellarMemberCardBalanceChangeRecordDbEntity);
    }

}
