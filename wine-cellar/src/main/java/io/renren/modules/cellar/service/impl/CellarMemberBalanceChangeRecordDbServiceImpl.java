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

import io.renren.modules.cellar.dao.CellarMemberBalanceChangeRecordDbDao;
import io.renren.modules.cellar.entity.CellarMemberBalanceChangeRecordDbEntity;
import io.renren.modules.cellar.service.CellarMemberBalanceChangeRecordDbService;


@Service("cellarMemberBalanceChangeRecordDbService")
public class CellarMemberBalanceChangeRecordDbServiceImpl extends ServiceImpl<CellarMemberBalanceChangeRecordDbDao, CellarMemberBalanceChangeRecordDbEntity> implements CellarMemberBalanceChangeRecordDbService {

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

}
