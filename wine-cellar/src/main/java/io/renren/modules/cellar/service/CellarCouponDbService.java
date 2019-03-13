package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarCouponDbEntity;

import java.util.Map;

/**
 * 优惠券表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-12 17:59:13
 */
public interface CellarCouponDbService extends IService<CellarCouponDbEntity> {

    PageUtils queryPage(CellarCouponDbEntity cellarCouponDb);
}

