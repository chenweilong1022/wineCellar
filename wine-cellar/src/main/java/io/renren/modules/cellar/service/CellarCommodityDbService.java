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
}

