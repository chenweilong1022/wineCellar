package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarCategoryDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 酒窖类别表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-23 15:22:27
 */
@Mapper
public interface CellarCategoryDbDao extends BaseMapper<CellarCategoryDbEntity> {
	
}
