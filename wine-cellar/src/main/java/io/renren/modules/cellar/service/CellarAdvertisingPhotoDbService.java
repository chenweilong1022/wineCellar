package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarAdvertisingPhotoDbEntity;

import java.util.Map;

/**
 * 首页广告图表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-18 09:47:37
 */
public interface CellarAdvertisingPhotoDbService extends IService<CellarAdvertisingPhotoDbEntity> {

    PageUtils queryPage(CellarAdvertisingPhotoDbEntity cellarAdvertisingPhotoDb);
}

