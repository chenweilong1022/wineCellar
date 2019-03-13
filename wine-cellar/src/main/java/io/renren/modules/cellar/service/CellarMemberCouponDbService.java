package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberCouponDbEntity;

import java.util.Map;

/**
 * 会员优惠券表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-13 14:22:14
 */
public interface CellarMemberCouponDbService extends IService<CellarMemberCouponDbEntity> {

    PageUtils queryPage(CellarMemberCouponDbEntity cellarMemberCouponDb);
}

