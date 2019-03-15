package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarCouponDbEntity;
import io.renren.modules.cellar.entity.CellarMemberCouponDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarCouponDbService;
import io.renren.modules.cellar.service.CellarMemberCouponDbService;
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
 * APP会员优惠券表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-13 14:22:14
 */
@RestController
@RequestMapping("app/cellarmembercoupondb")
@Api(value="APP会员优惠券表",tags="APP会员优惠券表")
public class AppCellarMemberCouponDbController {
    @Autowired
    private CellarMemberCouponDbService cellarMemberCouponDbService;
    @Autowired
    private CellarCouponDbService cellarCouponDbService;

    /**
     * 会员优惠券列表
     */
    @GetMapping("/list")
    @Login
    @ApiOperation(value = "会员优惠券列表",notes = "会员优惠券列表",response = CellarMemberCouponDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="usingState",value="使用状态 0未使用 1已使用 2已过期",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberCouponDbEntity cellarMemberCouponDb
    ){
        cellarMemberCouponDb.setMemberId(cellarMemberDbEntity.getMemberId());
        PageUtils page = cellarMemberCouponDbService.queryPage(cellarMemberCouponDb);

        return R.data(page);
    }
    /**
     * 会员领取优惠券
     */
    @PostMapping("/save")
    @Login
    @ApiOperation(value = "会员领取优惠券",notes = "会员领取优惠券",response = CellarMemberCouponDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="couponId",value="优惠券id",dataType="String",required=false,paramType="query"),
    })
    public R save(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberCouponDbEntity cellarMemberCouponDb
    ){
        Assert.isNull(cellarMemberCouponDb.getCouponId(),"优惠券id不能为空");
        /**
         * 查询优惠券
         * 状态为正常
         * 数量大于0
         */
        CellarCouponDbEntity cellarCouponDbEntity = cellarCouponDbService.getOne(new QueryWrapper<CellarCouponDbEntity>().lambda()
                .eq(CellarCouponDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(CellarCouponDbEntity::getCouponId, cellarMemberCouponDb.getCouponId())
                .gt(CellarCouponDbEntity::getCouponNumbers, Constants.Number.zero.getKey())
        );
        Assert.isNull(cellarCouponDbEntity,"优惠券不存在");

        /**
         * 领取优惠券
         */
        cellarMemberCouponDb.setMemberId(cellarMemberDbEntity.getMemberId());
        cellarMemberCouponDb.setState(Constants.STATE.zero.getKey());
        cellarMemberCouponDb.setCreateTime(new Date());
        cellarMemberCouponDb.setUsingState(Constants.USINGSTATE.ONE.getKey());
        cellarMemberCouponDb.setStoreId(cellarCouponDbEntity.getStoreId());
        cellarMemberCouponDbService.save(cellarMemberCouponDb);

        /**
         * 修改优惠券数量
         */
        cellarCouponDbEntity.setCouponNumbers(cellarCouponDbEntity.getCouponNumbers() - Constants.Number.one.getKey());
        cellarCouponDbService.updateById(cellarCouponDbEntity);

        return R.ok();
    }
}
