package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarCartDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 酒窖购物车
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-28 13:42:16
 */
@Mapper
public interface CellarCartDbDao extends BaseMapper<CellarCartDbEntity> {
	
}
