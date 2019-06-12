package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarCommodityEvaluationDbDao;
import io.renren.modules.cellar.entity.CellarCommodityEvaluationDbEntity;
import io.renren.modules.cellar.service.CellarCommodityEvaluationDbService;


@Service("cellarCommodityEvaluationDbService")
public class CellarCommodityEvaluationDbServiceImpl extends ServiceImpl<CellarCommodityEvaluationDbDao, CellarCommodityEvaluationDbEntity> implements CellarCommodityEvaluationDbService {

    @Override
    public PageUtils queryPage(CellarCommodityEvaluationDbEntity cellarCommodityEvaluationDb) {
        IPage<CellarCommodityEvaluationDbEntity> page = baseMapper.selectPage(
                new Query<CellarCommodityEvaluationDbEntity>(cellarCommodityEvaluationDb).getPage(),
                new QueryWrapper<CellarCommodityEvaluationDbEntity>().lambda()
                .eq(CellarCommodityEvaluationDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(ObjectUtil.isNotNull(cellarCommodityEvaluationDb.getCommodityId()),CellarCommodityEvaluationDbEntity::getCommodityId,cellarCommodityEvaluationDb.getCommodityId())
                .eq(ObjectUtil.isNotNull(cellarCommodityEvaluationDb.getStoreId()),CellarCommodityEvaluationDbEntity::getStoreId,cellarCommodityEvaluationDb.getStoreId())
                .orderByDesc(CellarCommodityEvaluationDbEntity::getCreateTime)
        );
        return new PageUtils(page);
    }

}
