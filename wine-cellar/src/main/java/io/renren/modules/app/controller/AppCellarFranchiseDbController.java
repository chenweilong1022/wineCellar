package io.renren.modules.app.controller;

import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarFranchiseDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarFranchiseDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Date;


/**
 * APP酒窖加盟表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-27 13:25:34
 */
@RestController
@RequestMapping("app/cellarfranchisedb")
@Api(value="APP酒窖加盟表",tags="APP酒窖加盟表")
public class AppCellarFranchiseDbController {
    @Autowired
    private CellarFranchiseDbService cellarFranchiseDbService;

    /**
     * 保存
     */
    @PostMapping("/save")
    @Login
    @ApiOperation(value = "酒窖加盟保存",notes = "酒窖加盟保存")
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="companyName",value="公司名称",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="commodityBrand",value="产品品牌",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="contact",value="联系人",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="contactMobilePhone",value="联系号码",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="shopFrontPhotoList",value="店铺门面照片",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="shopSizePhotoList",value="店铺面积照片",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="shopDecorationPhotosList",value="店铺装修照片",dataType="String",required=false,paramType="query"),
    })
    public R save(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarFranchiseDbEntity cellarFranchiseDb
    ){

        /**
         * 校验
         */
        Assert.isBlank(cellarFranchiseDb.getCompanyName(),"公司名称不能为空");
        Assert.isBlank(cellarFranchiseDb.getCommodityBrand(),"产品品牌不能为空");
        Assert.isBlank(cellarFranchiseDb.getContact(),"联系人不能为空");
        Assert.isBlank(cellarFranchiseDb.getContactMobilePhone(),"联系号码不能为空");
        Assert.isPhone(cellarFranchiseDb.getContactMobilePhone());
        Assert.isNullArray(cellarFranchiseDb.getShopFrontPhotoList(),"店铺门面照片不能为空");
        Assert.isNullArray(cellarFranchiseDb.getShopSizePhotoList(),"店铺面积照片不能为空");
        Assert.isNullArray(cellarFranchiseDb.getShopDecorationPhotosList(),"店铺装修照片不能为空");
        /**
         * 保存
         */
        cellarFranchiseDb.setMemberId(cellarMemberDbEntity.getMemberId());
        cellarFranchiseDb.setCreateTime(new Date());
        cellarFranchiseDb.setState(Constants.STATE.zero.getKey());
        cellarFranchiseDb.setReviewStatus(Constants.REVIEWSTATUS.zero.getKey());
        cellarFranchiseDbService.save(cellarFranchiseDb);

        return R.ok();
    }

}
