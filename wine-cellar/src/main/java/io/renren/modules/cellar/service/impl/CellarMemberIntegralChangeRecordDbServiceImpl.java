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

import io.renren.modules.cellar.dao.CellarMemberIntegralChangeRecordDbDao;
import io.renren.modules.cellar.entity.CellarMemberIntegralChangeRecordDbEntity;
import io.renren.modules.cellar.service.CellarMemberIntegralChangeRecordDbService;


@Service("cellarMemberIntegralChangeRecordDbService")
public class CellarMemberIntegralChangeRecordDbServiceImpl extends ServiceImpl<CellarMemberIntegralChangeRecordDbDao, CellarMemberIntegralChangeRecordDbEntity> implements CellarMemberIntegralChangeRecordDbService {

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

}
