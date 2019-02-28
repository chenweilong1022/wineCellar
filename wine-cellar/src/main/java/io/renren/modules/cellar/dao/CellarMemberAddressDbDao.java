package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarMemberAddressDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员地址表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-26 10:56:54
 */
@Mapper
public interface CellarMemberAddressDbDao extends BaseMapper<CellarMemberAddressDbEntity> {
	
}
