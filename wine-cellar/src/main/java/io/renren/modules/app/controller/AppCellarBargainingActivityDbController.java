package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarBargainingActivityDbEntity;
import io.renren.modules.cellar.entity.CellarGroupActivityDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarBargainingActivityDbService;
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
 * APP砍价活动表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-03 13:47:40
 */
@RestController
@RequestMapping("app/cellarbargainingactivitydb")
@Api(value="APP砍价活动表",tags="APP砍价活动表")
public class AppCellarBargainingActivityDbController {
    @Autowired
    private CellarBargainingActivityDbService cellarBargainingActivityDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "APP砍价活动列表",notes = "APP砍价活动列表",response = CellarBargainingActivityDbEntity.class)
//    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="key",value="搜索条件",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="page",value="当前页数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="limit",value="每页个数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarBargainingActivityDbEntity cellarBargainingActivityDb
    ){
        Assert.isNull(cellarBargainingActivityDb.getStoreId(),"店铺id不能为空");
        PageUtils page = cellarBargainingActivityDbService.queryPage(cellarBargainingActivityDb);

        return R.data(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info")
    @ApiOperation(value = "APP砍价活动详情",notes = "APP砍价活动详情",response = CellarBargainingActivityDbEntity.class)
//    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="bargainingActivityId",value="秒杀活动id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
    })
    public R info(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarBargainingActivityDbEntity  cellarBargainingActivityDb
    ){
        /**
         * 校验前端数据
         */
        Assert.isNull(cellarBargainingActivityDb.getStoreId(),"店铺id不能为空");
        Assert.isNull(cellarBargainingActivityDb.getBargainingActivityId(),"商品id不能为空");
        CellarBargainingActivityDbEntity cellarBargainingActivityDbEntity = cellarBargainingActivityDbService.getOne(
                new QueryWrapper<CellarBargainingActivityDbEntity>().lambda()
                        .eq(CellarBargainingActivityDbEntity::getStoreId, cellarBargainingActivityDb.getStoreId())
                        .eq(CellarBargainingActivityDbEntity::getBargainingActivityId, cellarBargainingActivityDb.getBargainingActivityId())
                        .eq(CellarBargainingActivityDbEntity::getState, Constants.STATE.zero.getKey())
        );
        return R.data(cellarBargainingActivityDbEntity);
    }

}
