package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarMemberBalanceChangeRecordDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员余额变动记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-25 11:07:23
 */
@Mapper
public interface CellarMemberBalanceChangeRecordDbDao extends BaseMapper<CellarMemberBalanceChangeRecordDbEntity> {
	
}
