package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarHandpickCommodityReviewDbEntity;

import java.util.Map;

/**
 * 精选商品审核表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-20 10:56:44
 */
public interface CellarHandpickCommodityReviewDbService extends IService<CellarHandpickCommodityReviewDbEntity> {

    PageUtils queryPage(CellarHandpickCommodityReviewDbEntity cellarHandpickCommodityReviewDb);
}

