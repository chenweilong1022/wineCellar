package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarMemberCollectionDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员收藏表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-23 13:51:41
 */
@Mapper
public interface CellarMemberCollectionDbDao extends BaseMapper<CellarMemberCollectionDbEntity> {
	
}
