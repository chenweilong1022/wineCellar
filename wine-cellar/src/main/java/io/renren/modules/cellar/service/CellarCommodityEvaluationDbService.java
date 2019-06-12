package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarCommodityEvaluationDbEntity;

import java.util.Map;

/**
 * 商品评价表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-11 15:12:42
 */
public interface CellarCommodityEvaluationDbService extends IService<CellarCommodityEvaluationDbEntity> {

    PageUtils queryPage(CellarCommodityEvaluationDbEntity cellarCommodityEvaluationDb);
}

