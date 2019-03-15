package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarConfigDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 全局配置表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-14 15:13:04
 */
@Mapper
public interface CellarConfigDbDao extends BaseMapper<CellarConfigDbEntity> {
	
}
