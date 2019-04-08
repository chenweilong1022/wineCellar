package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberBalanceChangeRecordDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 会员余额变动记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-25 11:07:23
 */
public interface CellarMemberBalanceChangeRecordDbService extends IService<CellarMemberBalanceChangeRecordDbEntity> {

    PageUtils queryPage(CellarMemberBalanceChangeRecordDbEntity cellarMemberBalanceChangeRecordDb);

    void balancePay(
            CellarMemberDbEntity cellarMemberDbEntity,
            BigDecimal payOrderAmount,
            String orderNo,
            String payPassword
    );
    void balanceRefund(
            CellarMemberDbEntity cellarMemberDbEntity,
            BigDecimal payOrderAmount,
            String orderNo,
            String payPassword
    );
}

