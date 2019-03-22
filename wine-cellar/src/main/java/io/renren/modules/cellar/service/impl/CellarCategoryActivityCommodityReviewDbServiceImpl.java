package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarCategoryActivityCommodityReviewDbDao;
import io.renren.modules.cellar.entity.CellarCategoryActivityCommodityReviewDbEntity;
import io.renren.modules.cellar.service.CellarCategoryActivityCommodityReviewDbService;


@Service("cellarCategoryActivityCommodityReviewDbService")
public class CellarCategoryActivityCommodityReviewDbServiceImpl extends ServiceImpl<CellarCategoryActivityCommodityReviewDbDao, CellarCategoryActivityCommodityReviewDbEntity> implements CellarCategoryActivityCommodityReviewDbService {

    @Override
    public PageUtils queryPage(CellarCategoryActivityCommodityReviewDbEntity cellarCategoryActivityCommodityReviewDb) {
        IPage<CellarCategoryActivityCommodityReviewDbEntity> page = baseMapper.selectPage(
                new Query<CellarCategoryActivityCommodityReviewDbEntity>(cellarCategoryActivityCommodityReviewDb).getPage(),
                new QueryWrapper<CellarCategoryActivityCommodityReviewDbEntity>()
        );

        return new PageUtils(page);
    }

}
