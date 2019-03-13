package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarCouponDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-12 17:59:13
 */
@Mapper
public interface CellarCouponDbDao extends BaseMapper<CellarCouponDbEntity> {
	
}
