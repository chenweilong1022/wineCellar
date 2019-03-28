package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarMemberIntegralChangeRecordDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员积分变动记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-26 13:19:43
 */
@Mapper
public interface CellarMemberIntegralChangeRecordDbDao extends BaseMapper<CellarMemberIntegralChangeRecordDbEntity> {
	
}
