package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.SysAreaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 地域信息表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 15:12:55
 */
@Mapper
public interface SysAreaDao extends BaseMapper<SysAreaEntity> {
	
}
