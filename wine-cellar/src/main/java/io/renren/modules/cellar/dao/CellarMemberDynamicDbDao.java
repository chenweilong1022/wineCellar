package io.renren.modules.cellar.dao;

import io.renren.modules.cellar.entity.CellarMemberDynamicDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 会员动态表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-05-13 09:58:25
 */
@Mapper
public interface CellarMemberDynamicDbDao extends BaseMapper<CellarMemberDynamicDbEntity> {

    /**
     * 活跃动态用户查询 根据动态数量判断
     * @return
     */
    List<CellarMemberDynamicDbEntity> activeMember();

}
