package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarConfigDbEntity;

import java.util.Map;

/**
 * 全局配置表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-14 15:13:04
 */
public interface CellarConfigDbService extends IService<CellarConfigDbEntity> {

    PageUtils queryPage(CellarConfigDbEntity cellarConfigDb);
}

