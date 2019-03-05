package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarOrderDetailsDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 酒窖订单明细表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-01 13:16:42
 */
@Mapper
public interface CellarOrderDetailsDbDao extends BaseMapper<CellarOrderDetailsDbEntity> {
	
}
