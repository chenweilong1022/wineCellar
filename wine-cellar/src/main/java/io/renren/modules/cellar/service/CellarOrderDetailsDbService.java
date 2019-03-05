package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarOrderDetailsDbEntity;

import java.util.Map;

/**
 * 酒窖订单明细表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-01 13:16:42
 */
public interface CellarOrderDetailsDbService extends IService<CellarOrderDetailsDbEntity> {

    PageUtils queryPage(CellarOrderDetailsDbEntity cellarOrderDetailsDb);
}

