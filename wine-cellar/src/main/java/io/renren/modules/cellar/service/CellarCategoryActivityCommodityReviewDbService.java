package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarCategoryActivityCommodityReviewDbEntity;

import java.util.Map;

/**
 * 分类活动商品审核表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-21 13:43:20
 */
public interface CellarCategoryActivityCommodityReviewDbService extends IService<CellarCategoryActivityCommodityReviewDbEntity> {

    PageUtils queryPage(CellarCategoryActivityCommodityReviewDbEntity cellarCategoryActivityCommodityReviewDb);
}

