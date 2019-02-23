package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarCommodityDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 酒窖商品表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
@Mapper
public interface CellarCommodityDbDao extends BaseMapper<CellarCommodityDbEntity> {
	
}
