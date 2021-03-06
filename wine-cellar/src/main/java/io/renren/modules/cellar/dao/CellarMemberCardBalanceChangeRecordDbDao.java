package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarMemberCardBalanceChangeRecordDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员储值卡余额变动记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-28 14:31:48
 */
@Mapper
public interface CellarMemberCardBalanceChangeRecordDbDao extends BaseMapper<CellarMemberCardBalanceChangeRecordDbEntity> {
	
}
