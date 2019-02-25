package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarCategoryDbEntity;

import java.util.List;
import java.util.Map;

/**
 * 酒窖类别表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-23 15:22:27
 */
public interface CellarCategoryDbService extends IService<CellarCategoryDbEntity> {

    PageUtils queryPage(CellarCategoryDbEntity cellarCategoryDb);

    List<CellarCategoryDbEntity> listAllLevel();
}

