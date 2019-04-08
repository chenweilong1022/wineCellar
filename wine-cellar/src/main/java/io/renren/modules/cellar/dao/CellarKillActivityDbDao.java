package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarKillActivityDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-29 10:13:13
 */
@Mapper
public interface CellarKillActivityDbDao extends BaseMapper<CellarKillActivityDbEntity> {
	
}
