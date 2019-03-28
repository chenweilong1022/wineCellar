package io.renren.modules.app.controller;

import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarCommodityEntryDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarCommodityEntryDbService;
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
 * APP产品入驻表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-27 14:36:40
 */
@RestController
@RequestMapping("app/cellarcommodityentrydb")
@Api(value="APP产品入驻表",tags="APP产品入驻表")
public class AppCellarCommodityEntryDbController {
    @Autowired
    private CellarCommodityEntryDbService cellarCommodityEntryDbService;

    /**
     * 保存
     */
    @PostMapping("/save")
    @Login
    @ApiOperation(value = "产品入驻保存",notes = "产品入驻保存")
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
//            @ApiImplicitParam(name="commodityEntryId",value="产品入驻id",dataType="String",required=false,paramType="query"),
//            @ApiImplicitParam(name="state",value="状态",dataType="String",required=false,paramType="query"),
//            @ApiImplicitParam(name="createTime",value="创建时间",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="companyName",value="公司名称",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="commodityBrand",value="产品品牌",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="contact",value="联系人",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="contactMobilePhone",value="联系号码",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="commodityPicturesList",value="产品图片",dataType="String",required=false,paramType="query"),
//            @ApiImplicitParam(name="reviewStatus",value="审核状态",dataType="String",required=false,paramType="query"),
//            @ApiImplicitParam(name="memberId",value="会员id",dataType="String",required=false,paramType="query"),
    })
    public R save(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarCommodityEntryDbEntity cellarCommodityEntryDb
    ){
        Assert.isBlank(cellarCommodityEntryDb.getCompanyName(),"公司名称不能为空");
        Assert.isBlank(cellarCommodityEntryDb.getCommodityBrand(),"产品品牌不能为空");
        Assert.isBlank(cellarCommodityEntryDb.getContact(),"联系人不能为空");
        Assert.isBlank(cellarCommodityEntryDb.getContactMobilePhone(),"联系号码不能为空");
        Assert.isPhone(cellarCommodityEntryDb.getContactMobilePhone());
        Assert.isNullArray(cellarCommodityEntryDb.getCommodityPicturesList(),"产品图片不能为空");
        cellarCommodityEntryDb.setState(Constants.STATE.zero.getKey());
        cellarCommodityEntryDb.setCreateTime(new Date());
        cellarCommodityEntryDb.setReviewStatus(Constants.REVIEWSTATUS.zero.getKey());
        cellarCommodityEntryDb.setMemberId(cellarMemberDbEntity.getMemberId());
        cellarCommodityEntryDbService.save(cellarCommodityEntryDb);

        return R.ok();
    }

}
