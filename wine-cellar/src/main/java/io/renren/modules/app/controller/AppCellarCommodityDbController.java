package io.renren.modules.app.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.annotation.MemberFootprint;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarCommodityDbEntity;
import io.renren.modules.cellar.entity.CellarMemberAddressDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarCommodityDbService;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;


/**
 * APP酒窖商品表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
@RestController
@RequestMapping("app/cellarcommoditydb")
@Api(value="APP酒窖商品表",tags="APP酒窖商品表")
public class AppCellarCommodityDbController extends AbstractController {
    @Autowired
    private CellarCommodityDbService cellarCommodityDbService;


    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "店铺商品列表",notes = "店铺商品列表",response = CellarCommodityDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="categoryId",value="分类id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="key",value="搜索条件",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="page",value="当前页数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="limit",value="每页个数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="haveHandpick",value="精选商品",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="categoryActivityId",value="分类活动id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="sort",value="排序 1按销量 2按时间 3按价格升序 4按价格倒序 5好评",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore CellarCommodityDbEntity cellarCommodityDb
    ){
        /**
         * 校验前端数据
         */
//        Assert.isNull(cellarCommodityDb.getStoreId(),"店铺id不能为空");
//        Assert.isNull(cellarCommodityDb.getCategoryId(),"分类id不能为空");

        /**
         * 查询
         */
        PageUtils page = cellarCommodityDbService.queryPageApp(cellarCommodityDb);

        return R.data(page);
    }

    /**
     * 积分商城商品列表
     * 平台上传商品都为积分商城商品
     * 只能使用积分购买
     */
    @GetMapping("/listIntegral")
    @ApiOperation(value = "积分商城商品列表",notes = "积分商城商品列表",response = CellarCommodityDbEntity.class)
    @ApiImplicitParams({
//            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
//            @ApiImplicitParam(name="categoryId",value="分类id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="key",value="搜索条件",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="page",value="当前页数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="limit",value="每页个数",dataType="String",required=false,paramType="query"),
//            @ApiImplicitParam(name="haveHandpick",value="精选商品",dataType="String",required=false,paramType="query"),
//            @ApiImplicitParam(name="categoryActivityId",value="分类活动id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="sort",value="排序 1按销量 2按时间 3按价格升序 4按价格倒序 5好评",dataType="String",required=false,paramType="query"),
    })
    public R listIntegral(
            @ApiIgnore CellarCommodityDbEntity cellarCommodityDb
    ){
        /**
         * 查询
         */
        PageUtils page = cellarCommodityDbService.queryPageIntegral(cellarCommodityDb);

        return R.data(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info")
    @MemberFootprint//会员足迹注解
    @ApiOperation(value = "店铺商品详情",notes = "店铺商品详情",response = CellarCommodityDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="commodityId",value="商品id",dataType="String",required=false,paramType="query"),
    })
    public R info(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarCommodityDbEntity cellarCommodityDb
    ){
        /**
         * 校验前端数据
         */
        Assert.isNull(cellarCommodityDb.getStoreId(),"店铺id不能为空");
        Assert.isNull(cellarCommodityDb.getCommodityId(),"商品id不能为空");
        /**
         * 查询
         */
        CellarCommodityDbEntity cellarCommodityDbEntityOne = cellarCommodityDbService.getOne(new QueryWrapper<CellarCommodityDbEntity>()
                .lambda()
                .eq(CellarCommodityDbEntity::getState,Constants.STATE.zero.getKey())
                .eq(ObjectUtil.isNotNull(cellarCommodityDb.getStoreId()) && cellarCommodityDb.getStoreId() > Constants.Number.zero.getKey(),CellarCommodityDbEntity::getStoreId,cellarCommodityDb.getStoreId())
                .eq(CellarCommodityDbEntity::getCommodityId, cellarCommodityDb.getCommodityId())
        );
        /**
         * 判断用户是否收藏商品
         */
        cellarCommodityDbEntityOne.setMemberId(cellarMemberDbEntity == null ? null : cellarMemberDbEntity.getMemberId());
        return R.data(cellarCommodityDbEntityOne);
    }

}
