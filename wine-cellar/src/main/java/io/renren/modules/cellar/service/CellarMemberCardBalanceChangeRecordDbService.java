package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberCardBalanceChangeRecordDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 会员储值卡余额变动记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-28 14:31:48
 */
public interface CellarMemberCardBalanceChangeRecordDbService extends IService<CellarMemberCardBalanceChangeRecordDbEntity> {

    PageUtils queryPage(CellarMemberCardBalanceChangeRecordDbEntity cellarMemberCardBalanceChangeRecordDb);

    void cardBalancePay(
            CellarMemberDbEntity cellarMemberDbEntity,
            BigDecimal payOrderAmount,
            String orderNo,
            String payPassword
    );
    void cardBalanceRefund(
            CellarMemberDbEntity cellarMemberDbEntity,
            BigDecimal payOrderAmount,
            String orderNo,
            String payPassword
    );
}

