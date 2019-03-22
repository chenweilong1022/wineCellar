package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarHandpickCommodityReviewDbDao;
import io.renren.modules.cellar.entity.CellarHandpickCommodityReviewDbEntity;
import io.renren.modules.cellar.service.CellarHandpickCommodityReviewDbService;


@Service("cellarHandpickCommodityReviewDbService")
public class CellarHandpickCommodityReviewDbServiceImpl extends ServiceImpl<CellarHandpickCommodityReviewDbDao, CellarHandpickCommodityReviewDbEntity> implements CellarHandpickCommodityReviewDbService {

    @Override
    public PageUtils queryPage(CellarHandpickCommodityReviewDbEntity cellarHandpickCommodityReviewDb) {
        IPage<CellarHandpickCommodityReviewDbEntity> page = baseMapper.selectPage(
                new Query<CellarHandpickCommodityReviewDbEntity>(cellarHandpickCommodityReviewDb).getPage(),
                new QueryWrapper<CellarHandpickCommodityReviewDbEntity>()
        );

        return new PageUtils(page);
    }

}
