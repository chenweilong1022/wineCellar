package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarMemberCardDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员储值卡表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-28 13:34:38
 */
@Mapper
public interface CellarMemberCardDbDao extends BaseMapper<CellarMemberCardDbEntity> {
	
}
