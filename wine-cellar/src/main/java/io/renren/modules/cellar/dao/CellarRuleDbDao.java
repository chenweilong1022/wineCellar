package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarRuleDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 酒窖规则表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-30 10:12:45
 */
@Mapper
public interface CellarRuleDbDao extends BaseMapper<CellarRuleDbEntity> {
	
}
