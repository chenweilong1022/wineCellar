package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarMemberCaptchaEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-01-29 14:59:19
 */
@Mapper
public interface CellarMemberCaptchaDao extends BaseMapper<CellarMemberCaptchaEntity> {
	
}
