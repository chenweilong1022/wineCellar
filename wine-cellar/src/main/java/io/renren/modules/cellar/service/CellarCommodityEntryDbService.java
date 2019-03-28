package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarCommodityEntryDbEntity;

import java.util.Map;

/**
 * 产品入驻表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-27 14:36:40
 */
public interface CellarCommodityEntryDbService extends IService<CellarCommodityEntryDbEntity> {

    PageUtils queryPage(CellarCommodityEntryDbEntity cellarCommodityEntryDb);
}

