package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarCartDbEntity;
import io.renren.modules.cellar.entity.CellarMemberAddressDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarCartDbService;
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
import java.util.List;


/**
 * APP酒窖购物车
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-28 13:42:16
 */
@RestController
@RequestMapping("app/cellarcartdb")
@Api(value="APP酒窖购物车",tags="APP酒窖购物车")
public class AppCellarCartDbController {
    @Autowired
    private CellarCartDbService cellarCartDbService;

    /**
     * 用户购物车列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "用户购物车列表",notes = "用户购物车列表",response = CellarCartDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarCartDbEntity cellarCartDb
    ){
        /**
         * 根据店铺id分组购物车
         */
        List<CellarCartDbEntity> list = cellarCartDbService.list(new QueryWrapper<CellarCartDbEntity>().lambda()
                .groupBy(CellarCartDbEntity::getStoreId)
                .eq(CellarCartDbEntity::getState,Constants.STATE.zero.getKey())
                .eq(CellarCartDbEntity::getMemberId,cellarMemberDbEntity.getMemberId())
        );
        /**
         * 如果为空直接返回
         */
        if (list == null || list.size() == Constants.Number.zero.getKey()) {
            return R.data(list);
        }

        /**
         * 遍历分组之后的店铺 将购物车商品
         * 填充到下一级集合中
         */
        for (CellarCartDbEntity cellarCartDbEntity : list) {

            List<CellarCartDbEntity> cellarCartDbEntities = cellarCartDbService.list(new QueryWrapper<CellarCartDbEntity>().lambda()
                    .eq(CellarCartDbEntity::getState, Constants.STATE.zero.getKey())
                    .eq(CellarCartDbEntity::getMemberId, cellarMemberDbEntity.getMemberId())
                    .eq(CellarCartDbEntity::getStoreId, cellarCartDbEntity.getStoreId())
            );

            cellarCartDbEntity.setCellarCartDbEntities(cellarCartDbEntities);
        }


        return R.data(list);
    }

    /**
     * 商品加入购物车
     */
    @PostMapping("/save")
    @ApiOperation(value = "商品加入购物车",notes = "商品加入购物车",response = CellarCartDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="commodityId",value="商品id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="number",value="商品数量",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="type",value="类型0:普通商品1:自提商品2:预约商品3:预售商品",dataType="String",required=false,paramType="query"),
    })
    public R save(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarCartDbEntity cellarCartDb
    ){
        /**
         * 校验数据
         */
        Assert.isNull(cellarCartDb.getNumber(),"商品数量不能为空");
        Assert.isNull(cellarCartDb.getCommodityId(),"商品id不能为空");
        Assert.isNull(cellarCartDb.getStoreId(),"店铺id不能为空");
        Assert.isNull(cellarCartDb.getType(),"类型不能为空");
        /**
         * 设置购物车
         */
        cellarCartDb.setCreateTime(new Date());
        cellarCartDb.setState(Constants.STATE.zero.getKey());
        cellarCartDb.setMemberId(cellarMemberDbEntity.getMemberId());

        /**
         * 查询购物车是否重复
         */
        CellarCartDbEntity cellarCartDbEntityOne = cellarCartDbService.getOne(new QueryWrapper<CellarCartDbEntity>().lambda()
                .eq(CellarCartDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(CellarCartDbEntity::getMemberId, cellarCartDb.getMemberId())
                .eq(CellarCartDbEntity::getStoreId, cellarCartDb.getStoreId())
                .eq(CellarCartDbEntity::getCommodityId, cellarCartDb.getCommodityId())
        );

        /**
         * 存在修改库存
         */
        if (cellarCartDbEntityOne != null) {
            cellarCartDbEntityOne.setNumber(cellarCartDb.getNumber());
            cellarCartDbService.updateById(cellarCartDbEntityOne);
            return R.ok();
        }
        /**
         * 不存在新增购物车
         */
        cellarCartDbService.save(cellarCartDb);
        return R.ok();
    }

    /**
     * 购物车修改数量
     */
    @PostMapping("/update")
    @ApiOperation(value = "购物车修改数量",notes = "购物车修改数量",response = CellarCartDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="cartId",value="购物车",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="number",value="数量",dataType="String",required=false,paramType="query"),
    })
    public R update(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarCartDbEntity cellarCartDb
    ){

        Assert.isNull(cellarCartDb,"购物车id不能为空");
        Assert.isNull(cellarCartDb.getCartId(),"购物车id不能为空");
        Assert.isNull(cellarCartDb.getNumber(),"购物车数量不能为空");
        if (cellarCartDb.getNumber().equals(BigDecimal.ZERO)) {
            return R.error("数量不能为0");
        }

        cellarCartDbService.updateById(cellarCartDb);
        return R.ok();
    }

    /**
     * 购物车删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "购物车删除",notes = "购物车删除",response = CellarCartDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="cartIds",value="购物车id 支持多个",dataType="String",required=false,paramType="query"),
    })
    public R delete(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            Long[] cartIds
    ){

//			cellarCartDbService.removeByIds(Arrays.asList(cartIds));
        /**
         * 校验数据
         */
        Assert.isNull(cartIds,"购物车id不能为空");
        /**
         * 删除购物车
         */
        CellarCartDbEntity cellarCartDbEntity = new CellarCartDbEntity();
        cellarCartDbEntity.setState(Constants.STATE.funine.getKey());
        cellarCartDbService.update(cellarCartDbEntity,new QueryWrapper<CellarCartDbEntity>().lambda()
                .in(CellarCartDbEntity::getCartId,cartIds)
                .eq(CellarCartDbEntity::getMemberId,cellarMemberDbEntity.getMemberId())
        );

        return R.ok();
    }

}
