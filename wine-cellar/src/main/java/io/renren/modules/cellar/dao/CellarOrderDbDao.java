package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarOrderDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 酒窖订单表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-01 13:16:42
 */
@Mapper
public interface CellarOrderDbDao extends BaseMapper<CellarOrderDbEntity> {
	
}
