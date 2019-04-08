package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarMemberBargainingRecordDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员砍价记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-03 16:45:35
 */
@Mapper
public interface CellarMemberBargainingRecordDbDao extends BaseMapper<CellarMemberBargainingRecordDbEntity> {
	
}
