package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarMemberMessageDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员消息
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-19 11:07:06
 */
@Mapper
public interface CellarMemberMessageDbDao extends BaseMapper<CellarMemberMessageDbEntity> {
	
}
