package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarCartDbEntity;

import java.util.Map;

/**
 * 酒窖购物车
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-28 13:42:16
 */
public interface CellarCartDbService extends IService<CellarCartDbEntity> {

    PageUtils queryPage(CellarCartDbEntity cellarCartDb);
}

