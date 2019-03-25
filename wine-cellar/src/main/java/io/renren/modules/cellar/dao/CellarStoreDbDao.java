package io.renren.modules.cellar.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.cellar.entity.CellarStoreDbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 酒窖店铺表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
@Mapper
public interface CellarStoreDbDao extends BaseMapper<CellarStoreDbEntity> {

    /**
     * 查询
     */
    IPage<CellarStoreDbEntity> nearStoreList(IPage<CellarStoreDbEntity> page, @Param(Constants.WRAPPER)CellarStoreDbEntity cellarStoreDbEntity);

    IPage<CellarStoreDbEntity> indexList(IPage<CellarStoreDbEntity> page,@Param(Constants.WRAPPER)CellarStoreDbEntity cellarStoreDb);
	
}
