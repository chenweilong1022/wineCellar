package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarBargainingActivityDbEntity;

import java.util.Map;

/**
 * 砍价活动表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-03 13:47:40
 */
public interface CellarBargainingActivityDbService extends IService<CellarBargainingActivityDbEntity> {

    PageUtils queryPage(CellarBargainingActivityDbEntity cellarBargainingActivityDb);
}

