package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarMemberDynamicCommentDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员动态评论表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-05-15 13:18:56
 */
@Mapper
public interface CellarMemberDynamicCommentDbDao extends BaseMapper<CellarMemberDynamicCommentDbEntity> {
	
}
