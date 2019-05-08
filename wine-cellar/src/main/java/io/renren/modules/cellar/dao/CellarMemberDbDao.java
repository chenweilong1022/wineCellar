package io.renren.modules.cellar.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.cellar.entity.CellarStoreDbEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 酒窖会员表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-01-16 17:34:20
 */
@Mapper
public interface CellarMemberDbDao extends BaseMapper<CellarMemberDbEntity> {
    /**
     * 查询附近好友
     * @param page
     * @param memberDbEntity
     * @return
     */
    public IPage<CellarStoreDbEntity> selectNearMember(IPage<CellarMemberDbEntity> page,@Param(Constants.WRAPPER)CellarMemberDbEntity memberDbEntity);
}
