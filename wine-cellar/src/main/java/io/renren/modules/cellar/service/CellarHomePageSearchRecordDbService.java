package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarHomePageSearchRecordDbEntity;

import java.util.Map;

/**
 * 首页搜索记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-22 16:25:21
 */
public interface CellarHomePageSearchRecordDbService extends IService<CellarHomePageSearchRecordDbEntity> {

    PageUtils queryPage(CellarHomePageSearchRecordDbEntity cellarHomePageSearchRecordDb);
}

