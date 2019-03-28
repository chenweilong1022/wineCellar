package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarCommodityEntryDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品入驻表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-27 14:36:40
 */
@Mapper
public interface CellarCommodityEntryDbDao extends BaseMapper<CellarCommodityEntryDbEntity> {
	
}
