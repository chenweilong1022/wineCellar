package io.renren.modules.app.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarConfigDbEntity;
import io.renren.modules.cellar.entity.CellarHomePageSearchRecordDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.entity.CellarStoreDbEntity;
import io.renren.modules.cellar.service.CellarConfigDbService;
import io.renren.modules.cellar.service.CellarHomePageSearchRecordDbService;
import io.renren.modules.cellar.service.CellarStoreDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * app酒窖店铺表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
@RestController
@RequestMapping("app/cellarstoredb")
@Api(value="APP酒窖店铺",tags="APP酒窖店铺")
public class AppCellarStoreDbController {
    @Autowired
    private CellarStoreDbService cellarStoreDbService;
    @Autowired
    private CellarConfigDbService cellarConfigDbService;
    @Autowired
    private CellarHomePageSearchRecordDbService cellarHomePageSearchRecordDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "app店铺列表",notes = "app店铺列表",response = CellarStoreDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="key",value="搜索条件",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="page",value="当前页数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="limit",value="每页个数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="longitude",value="经度",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="latitude",value="纬度",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="storeType",value="类型",dataType="String",required=false,paramType="query"),
   })
    public R list(@ApiIgnore CellarStoreDbEntity cellarStoreDb){
//        PageUtils page = cellarStoreDbService.queryPage(cellarStoreDb);

        CellarConfigDbEntity cellarConfigDb = cellarConfigDbService.getById(Constants.Number.one.getKey());
        Assert.isNull(cellarStoreDb.getLongitude(),"经度不能为空");
        Assert.isNull(cellarStoreDb.getLatitude(),"纬度不能为空");
        cellarStoreDb.setDistance(cellarConfigDb.getDistance());
        PageUtils page = cellarStoreDbService.nearStoreList(cellarStoreDb);
        return R.data(page);
    }

    /**
     * 列表
     */
    @GetMapping("/indexList")
    @ApiOperation(value = "首页搜索",notes = "首页搜索",response = CellarStoreDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="key",value="搜索条件",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="page",value="当前页数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="limit",value="每页个数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="longitude",value="经度",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="latitude",value="纬度",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="sort",value="排序 1按距离 2按好评",dataType="String",required=false,paramType="query"),
    })
    public R indexList(@ApiIgnore CellarStoreDbEntity cellarStoreDb){

        Assert.isNull(cellarStoreDb.getLongitude(),"经度不能为空");
        Assert.isNull(cellarStoreDb.getLatitude(),"纬度不能为空");

        PageUtils pageUtils = cellarStoreDbService.indexList(cellarStoreDb);

        if (StringUtils.isNotBlank(cellarStoreDb.getKey())) {

            CellarHomePageSearchRecordDbEntity homePageSearchRecordDbEntity = cellarHomePageSearchRecordDbService.getOne(new QueryWrapper<CellarHomePageSearchRecordDbEntity>().lambda()
                    .eq(CellarHomePageSearchRecordDbEntity::getState, Constants.STATE.zero.getKey())
                    .eq(CellarHomePageSearchRecordDbEntity::getSearchKeywords, cellarStoreDb.getKey())
            );
            if (ObjectUtil.isNotNull(homePageSearchRecordDbEntity)) {
                homePageSearchRecordDbEntity.setUpdateTime(new Date());
                homePageSearchRecordDbEntity.setSearchNumber(homePageSearchRecordDbEntity.getSearchNumber() + Constants.Number.one.getKey());
                cellarHomePageSearchRecordDbService.updateById(homePageSearchRecordDbEntity);
            }else {
                CellarHomePageSearchRecordDbEntity cellarHomePageSearchRecordDbEntity = new CellarHomePageSearchRecordDbEntity();
                cellarHomePageSearchRecordDbEntity.setCreateTime(new Date());
                cellarHomePageSearchRecordDbEntity.setSearchKeywords(cellarStoreDb.getKey());
                cellarHomePageSearchRecordDbEntity.setUpdateTime(new Date());
                cellarHomePageSearchRecordDbEntity.setSearchNumber(Constants.Number.one.getKey());
                cellarHomePageSearchRecordDbEntity.setState(Constants.STATE.zero.getKey());
                cellarHomePageSearchRecordDbService.save(cellarHomePageSearchRecordDbEntity);
            }
        }
        return R.data(pageUtils);
    }

    /**
     * 列表
     */
    @GetMapping("/nearStoreList")
    @ApiOperation(value = "附近店铺",notes = "附近店铺",response = CellarStoreDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="key",value="搜索条件",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="page",value="当前页数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="limit",value="每页个数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="longitude",value="经度",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="latitude",value="纬度",dataType="String",required=false,paramType="query"),
    })
    public R nearStoreList(@ApiIgnore CellarStoreDbEntity cellarStoreDb){
        CellarConfigDbEntity cellarConfigDb = cellarConfigDbService.getById(Constants.Number.one.getKey());
        Assert.isNull(cellarStoreDb.getLongitude(),"经度不能为空");
        Assert.isNull(cellarStoreDb.getLatitude(),"纬度不能为空");
        cellarStoreDb.setDistance(cellarConfigDb.getDistance());
        PageUtils page = cellarStoreDbService.nearStoreList(cellarStoreDb);
        return R.data(page);
    }


    /**
     * 店铺详情
     */
    @GetMapping("/info")
    @ApiOperation(value = "app店铺详情",notes = "app店铺详情",response = CellarStoreDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
    })
    public R info(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarStoreDbEntity cellarStoreDbEntity
    ){
        CellarStoreDbEntity cellarStoreDb = cellarStoreDbService.getById(cellarStoreDbEntity.getStoreId());
        if (ObjectUtil.isNotNull(cellarStoreDb)) {
            cellarStoreDb.setMemberId(cellarMemberDbEntity == null ? null : cellarMemberDbEntity.getMemberId());
        }
        return R.data(cellarStoreDb);
    }

//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    @RequiresPermissions("cellar:cellarstoredb:save")
//    public R save(@RequestBody CellarStoreDbEntity cellarStoreDb){
//        cellarStoreDb.setCreateTime(new Date());
//        cellarStoreDb.setState(Constants.STATE.zero.getKey());
//        cellarStoreDbService.save(cellarStoreDb);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    @RequiresPermissions("cellar:cellarstoredb:update")
//    public R update(@RequestBody CellarStoreDbEntity cellarStoreDb){
//			cellarStoreDbService.updateById(cellarStoreDb);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    @RequiresPermissions("cellar:cellarstoredb:delete")
//    public R delete(@RequestBody Long[] storeIds){
//			cellarStoreDbService.removeByIds(Arrays.asList(storeIds));
//
//        return R.ok();
//    }

}
