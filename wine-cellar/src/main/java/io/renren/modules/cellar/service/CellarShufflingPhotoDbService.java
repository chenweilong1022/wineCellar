package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarShufflingPhotoDbEntity;

import java.util.Map;

/**
 * 首页轮播图表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-15 09:53:34
 */
public interface CellarShufflingPhotoDbService extends IService<CellarShufflingPhotoDbEntity> {

    PageUtils queryPage(CellarShufflingPhotoDbEntity cellarShufflingPhotoDb);
}

