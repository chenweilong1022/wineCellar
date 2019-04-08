package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.BargainingUtil;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.*;
import io.renren.modules.cellar.service.CellarBargainingActivityDbService;
import io.renren.modules.cellar.service.CellarMemberBargainingInformationDbService;
import io.renren.modules.cellar.service.CellarMemberBargainingRecordDbService;
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
 * APP会员砍价记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-03 16:45:35
 */
@RestController
@RequestMapping("app/cellarmemberbargainingrecorddb")
@Api(value="APP会员砍价记录表",tags="APP会员砍价记录表")
public class AppCellarMemberBargainingRecordDbController {
    @Autowired
    private CellarMemberBargainingRecordDbService cellarMemberBargainingRecordDbService;
    @Autowired
    private CellarMemberBargainingInformationDbService cellarMemberBargainingInformationDbService;
    @Autowired
    private CellarBargainingActivityDbService cellarBargainingActivityDbService;

    /**
     * 信息
     */
    @GetMapping("/list")
    @ApiOperation(value = "APP会员砍价记录列表",notes = "APP会员砍价记录列表",response = CellarMemberBargainingRecordDbEntity.class)
//    @Login
    @ApiImplicitParams({
//            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberBargainingInformationId",value="会员砍价信息id",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberBargainingRecordDbEntity cellarMemberBargainingRecordDb
    ){
        /**
         * 校验
         */
        Assert.isNull(cellarMemberBargainingRecordDb,"会员砍价信息不能为空");
        /**
         * 查询被砍价信息是否存在 并校验
         */
        Long memberBargainingInformationId = cellarMemberBargainingRecordDb.getMemberBargainingInformationId();
        Assert.isNull(memberBargainingInformationId,"会员砍价信息id不能为空");
        CellarMemberBargainingInformationDbEntity cellarMemberBargainingInformationDbEntity = cellarMemberBargainingInformationDbService.getById(memberBargainingInformationId);
        Assert.isNull(cellarMemberBargainingInformationDbEntity,"会员砍价信息不存在");

        PageUtils page = cellarMemberBargainingRecordDbService.queryPage(cellarMemberBargainingRecordDb);
        return R.data(page);
    }

    /**
     * APP会员砍价记录,帮组好友砍价
     */
    @PostMapping("/save")
    @ApiOperation(value = "APP会员砍价记录,帮组好友砍价",notes = "APP会员砍价记录,帮组好友砍价",response = CellarMemberBargainingRecordDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberBargainingInformationId",value="会员砍价信息id",dataType="String",required=false,paramType="query"),
    })
    public R save(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberBargainingRecordDbEntity cellarMemberBargainingRecordDb
    ){
        /**
         * 校验
         */
        Assert.isNull(cellarMemberBargainingRecordDb,"会员砍价信息不能为空");
        /**
         * 查询被砍价信息是否存在 并校验
         */
        Long memberBargainingInformationId = cellarMemberBargainingRecordDb.getMemberBargainingInformationId();
        Assert.isNull(memberBargainingInformationId,"会员砍价信息id不能为空");
        CellarMemberBargainingInformationDbEntity cellarMemberBargainingInformationDbEntity = cellarMemberBargainingInformationDbService.getById(memberBargainingInformationId);
        Assert.isNull(cellarMemberBargainingInformationDbEntity,"APP会员砍价信息不存在");
        /**
         * 判断用户是否已经参与过砍价
         */
        int count = cellarMemberBargainingRecordDbService.count(new QueryWrapper<CellarMemberBargainingRecordDbEntity>().lambda()
                .eq(CellarMemberBargainingRecordDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(CellarMemberBargainingRecordDbEntity::getMemberId, cellarMemberDbEntity.getMemberId())
                .eq(CellarMemberBargainingRecordDbEntity::getMemberBargainingInformationId, memberBargainingInformationId)
        );
        Assert.isTrue(count > Constants.Number.zero.getKey(),"您已经砍过价了");
        BigDecimal subtract = cellarMemberBargainingInformationDbEntity.getBargainingParticipation().subtract(cellarMemberBargainingInformationDbEntity.getCuttingDownNumber());
        Assert.isTrue(subtract.compareTo(BigDecimal.ZERO) == 0,"已经砍到最低价了");
        /**
         * 调用砍价方法砍价
         * bigDecimal 砍掉金额
         */
        BigDecimal bigDecimal = BargainingUtil.sfReduceRule(cellarMemberBargainingInformationDbEntity.getFinalPrice().subtract(cellarMemberBargainingInformationDbEntity.getBargainingLowestPrice()), subtract.intValue());
        /**
         * 砍价
         */
        BigDecimal cutPrice = bigDecimal.multiply(BigDecimal.valueOf(-1));//砍掉金额
        BigDecimal beforeCutPrice = cellarMemberBargainingInformationDbEntity.getFinalPrice();//之前金额
        BigDecimal afterCutPrice = beforeCutPrice.add(cutPrice);//之后金额
        /**
         * 设置砍价记录
         * 保存
         */
        cellarMemberBargainingRecordDb.setCutPrice(cutPrice);
        cellarMemberBargainingRecordDb.setBeforeCutPrice(beforeCutPrice);
        cellarMemberBargainingRecordDb.setAfterCutPrice(afterCutPrice);
        cellarMemberBargainingRecordDb.setState(Constants.STATE.zero.getKey());
        cellarMemberBargainingRecordDb.setCreateTime(new Date());
        cellarMemberBargainingRecordDb.setMemberId(cellarMemberDbEntity.getMemberId());
        cellarMemberBargainingRecordDbService.save(cellarMemberBargainingRecordDb);
        /**
         * 修改会员砍价信息
         */
        cellarMemberBargainingInformationDbEntity.setFinalPrice(afterCutPrice);
        cellarMemberBargainingInformationDbEntity.setCuttingDownNumber(cellarMemberBargainingInformationDbEntity.getCuttingDownNumber().add(BigDecimal.ONE));
        cellarMemberBargainingInformationDbService.updateById(cellarMemberBargainingInformationDbEntity);
        return R.data(cellarMemberBargainingRecordDb);
    }

}
