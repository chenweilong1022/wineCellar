package io.renren.modules.app.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarMemberAddressDbEntity;
import io.renren.modules.cellar.entity.CellarMemberCollectionDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarMemberCollectionDbService;
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
 * APP会员收藏表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-23 13:51:41
 */
@RestController
@RequestMapping("app/cellarmembercollectiondb")
@Api(value="APP会员收藏表",tags="APP会员收藏表")
public class AppCellarMemberCollectionDbController {
    @Autowired
    private CellarMemberCollectionDbService cellarMemberCollectionDbService;

    /**
     * 列表
     */
    @PostMapping("/collection")
    @ApiOperation(value = "用户收藏",notes = "用户收藏",response = CellarMemberCollectionDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="collectionType",value="收藏类型1商品2店铺",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="commodityId",value="会员id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
    })
    public R collection(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberCollectionDbEntity cellarMemberCollectionDb
    ){
        cellarMemberCollectionDb.setMemberId(cellarMemberDbEntity.getMemberId());
        /**
         * 校验前端数据
         */
        Assert.isNull(cellarMemberCollectionDb.getCollectionType(),"收藏类型不能为空");
        if (cellarMemberCollectionDb.getCollectionType().equals(Constants.COLLECTIONTYPE.ONE.getKey())) {
            Assert.isNull(cellarMemberCollectionDb.getCommodityId(),"商品id不能为空");
        }else if (cellarMemberCollectionDb.getCollectionType().equals(Constants.COLLECTIONTYPE.TWO.getKey())) {
            Assert.isNull(cellarMemberCollectionDb.getStoreId(),"店铺id不能为空");
        }


        CellarMemberCollectionDbEntity memberCollectionDbEntity = cellarMemberCollectionDbService.getOne(new QueryWrapper<CellarMemberCollectionDbEntity>().lambda()
                .setEntity(cellarMemberCollectionDb)
        );

        if (ObjectUtil.isNotNull(memberCollectionDbEntity)) {
            cellarMemberCollectionDbService.removeById(memberCollectionDbEntity.getMemberCollectionId());
            return R.ok();
        }

        /**
         * 保存用户收藏记录
         */
        cellarMemberCollectionDb.setMemberId(cellarMemberDbEntity.getMemberId());
        cellarMemberCollectionDb.setState(Constants.STATE.zero.getKey());
        cellarMemberCollectionDb.setCreateTime(new Date());
        cellarMemberCollectionDbService.save(cellarMemberCollectionDb);
        return R.ok();
    }


    /**
     * 收藏列表
     */
    @GetMapping("/list")
    @Login
    @ApiOperation(value = "用户收藏列表",notes = "用户收藏列表",response = CellarMemberCollectionDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="collectionType",value="收藏类型1商品2店铺",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberCollectionDbEntity cellarMemberCollectionDb
    ){
        Assert.isNull(cellarMemberCollectionDb,"收藏类型不能为空");
        cellarMemberCollectionDb.setMemberId(cellarMemberDbEntity.getMemberId());
        PageUtils page = cellarMemberCollectionDbService.queryPage(cellarMemberCollectionDb);
        return R.data(page);
    }

    /**
     * 收藏列表
     */
    @PostMapping("/delete")
    @Login
    @ApiOperation(value = "删除用户收藏",notes = "删除用户收藏",response = CellarMemberCollectionDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
    })
    public R delete(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            Long[] memberCollectionId
    ){
        Assert.isNullArray(memberCollectionId,"收藏id不能为空");
        for (Long aLong : memberCollectionId) {
            cellarMemberCollectionDbService.remove(new QueryWrapper<CellarMemberCollectionDbEntity>().lambda()
                    .eq(CellarMemberCollectionDbEntity::getMemberId,cellarMemberDbEntity.getMemberId())
                    .eq(CellarMemberCollectionDbEntity::getMemberCollectionId,aLong)
            );
        }
        return R.ok();
    }

}
