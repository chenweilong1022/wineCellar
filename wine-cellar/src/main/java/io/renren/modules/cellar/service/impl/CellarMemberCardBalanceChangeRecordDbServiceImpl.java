package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import org.springframework.stereotype.Service;
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

}
