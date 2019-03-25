package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarStoreDbEntity;

import java.util.List;
import java.util.Map;

/**
 * 酒窖店铺表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
public interface CellarStoreDbService extends IService<CellarStoreDbEntity> {

    PageUtils queryPage(CellarStoreDbEntity cellarStoreDb);

    PageUtils nearStoreList(CellarStoreDbEntity cellarStoreDb);

    PageUtils indexList(CellarStoreDbEntity cellarStoreDb);
}

