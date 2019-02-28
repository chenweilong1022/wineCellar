package io.renren.modules.app.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.cellar.entity.CellarCategoryDbEntity;
import io.renren.modules.cellar.entity.CellarMemberAddressDbEntity;
import io.renren.modules.cellar.entity.CellarStoreDbEntity;
import io.renren.modules.cellar.service.CellarCategoryDbService;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * APP酒窖类别表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-23 15:22:27
 */
@RestController
@RequestMapping("app/cellarcategorydb")
@Api(value="APP酒窖类别表",tags="APP酒窖类别表")
public class AppCellarCategoryDbController extends AbstractController {
    @Autowired
    private CellarCategoryDbService cellarCategoryDbService;

    /**
     * 查询一级类别
     */
    @GetMapping("/listOneLevel")
    @ApiOperation(value = "查询一级类别",notes = "查询一级类别",response = CellarCategoryDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
    })
    public R listOneLevel(
            @ApiIgnore CellarCategoryDbEntity cellarCategoryDbEntity
    ){
        Assert.isNull(cellarCategoryDbEntity,"店铺id不能为空");
        Assert.isNull(cellarCategoryDbEntity.getStoreId(),"店铺id不能为空");
        /**
         * 设置顶级类别
         */
        CellarCategoryDbEntity cellarCategoryDb = new CellarCategoryDbEntity();
        cellarCategoryDb.setLevel(Constants.STATE.zero.getKey());
        cellarCategoryDb.setCategoryName("顶级类别");
        cellarCategoryDb.setSupCategoryId(Constants.STATE.fuone.getKey().longValue());
        cellarCategoryDb.setCategoryId(Constants.STATE.zero.getKey().longValue());
        /**
         * 查询一级类别列表
         */
        List<CellarCategoryDbEntity> list = cellarCategoryDbService.list(new QueryWrapper<CellarCategoryDbEntity>().lambda().
                eq(CellarCategoryDbEntity::getSupCategoryId,cellarCategoryDb.getCategoryId()).
                eq(ObjectUtil.isNotNull(cellarCategoryDbEntity.getStoreId()),CellarCategoryDbEntity::getStoreId,cellarCategoryDbEntity.getStoreId()).
                eq(CellarCategoryDbEntity::getState,Constants.STATE.zero.getKey())
        );
        cellarCategoryDb.setCellarCategoryDbEntities(list);

        return R.data(list);
    }

    /**
     * 根据1级类别查询2级类别
     */
    @GetMapping("/listTwoLevel")
    @ApiOperation(value = "根据1级类别查询2级类别",notes = "根据1级类别查询2级类别",response = CellarCategoryDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="categoryId",value="一级类别id",dataType="String",required=false,paramType="query"),
    })
    public R listAllLevel(
            @ApiIgnore CellarCategoryDbEntity cellarCategoryDbEntity
    ){
        /**
         * 校验前端数据
         */
        Assert.isNull(cellarCategoryDbEntity,"一级类别不能为空");
        Assert.isNull(cellarCategoryDbEntity.getStoreId(),"店铺id不能为空");
        Assert.isNull(cellarCategoryDbEntity.getCategoryId(),"一级类别不能为空");

        /**
         * 查询1级类别
         */
        CellarCategoryDbEntity cellarCategoryDbEntityOne = cellarCategoryDbService.getOne(new QueryWrapper<CellarCategoryDbEntity>().lambda().
                eq(CellarCategoryDbEntity::getState, Constants.STATE.zero.getKey()).
                eq(CellarCategoryDbEntity::getStoreId, cellarCategoryDbEntity.getStoreId()).
                eq(CellarCategoryDbEntity::getCategoryId, cellarCategoryDbEntity.getCategoryId())
        );

        /**
         * 判断是否为空
         */
        if (cellarCategoryDbEntityOne == null) {
            return R.error("该类别不存在");
        }

        /**
         * 判断是否为一级类别
         */
        if (!cellarCategoryDbEntityOne.getLevel().equals(Constants.Number.one.getKey())) {
            return R.error("该类别不是一级类别");
        }


        /**
         * 根据1级类别查询2级类别
         */
        List<CellarCategoryDbEntity> cellarCategoryDbEntities = cellarCategoryDbService.list(new QueryWrapper<CellarCategoryDbEntity>().lambda().
                eq(CellarCategoryDbEntity::getState, Constants.STATE.zero.getKey()).
                eq(CellarCategoryDbEntity::getStoreId, cellarCategoryDbEntity.getStoreId()).
                eq(CellarCategoryDbEntity::getSupCategoryId, cellarCategoryDbEntity.getCategoryId())
        );
        return R.data(cellarCategoryDbEntities);
    }

}
