package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarCategoryActivityDbEntity;

import java.util.Map;

/**
 * 分类活动表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-21 10:06:01
 */
public interface CellarCategoryActivityDbService extends IService<CellarCategoryActivityDbEntity> {

    PageUtils queryPage(CellarCategoryActivityDbEntity cellarCategoryActivityDb);
}

