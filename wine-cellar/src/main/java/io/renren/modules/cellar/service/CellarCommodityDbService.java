package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarCommodityDbEntity;

import java.util.Map;

/**
 * 酒窖商品表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
public interface CellarCommodityDbService extends IService<CellarCommodityDbEntity> {

    PageUtils queryPage(CellarCommodityDbEntity cellarCommodityDb);

    PageUtils queryPageApp(CellarCommodityDbEntity cellarCommodityDb);

    /**
     * 计算评价星数
     * @param commodityId 商品id
     * @param evaluationStarNumber 当前评价
     */
    void evaluationStarNumbers(Long commodityId,Integer evaluationStarNumber);
}

