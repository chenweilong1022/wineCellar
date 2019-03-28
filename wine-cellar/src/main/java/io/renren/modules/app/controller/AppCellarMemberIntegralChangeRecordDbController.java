package io.renren.modules.app.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.entity.CellarMemberIntegralChangeRecordDbEntity;
import io.renren.modules.cellar.service.CellarMemberIntegralChangeRecordDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;


/**
 * APP会员积分变动记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-26 13:19:43
 */
@RestController
@RequestMapping("app/cellarmemberintegralchangerecorddb")
@Api(value="APP会员积分变动记录表",tags="APP会员积分变动记录表")
public class AppCellarMemberIntegralChangeRecordDbController {
    @Autowired
    private CellarMemberIntegralChangeRecordDbService cellarMemberIntegralChangeRecordDbService;

    /**
     * APP会员积分变动记录列表
     */
    @GetMapping("/list")
    @ApiOperation("APP会员积分变动记录列表")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberIntegralChangeRecordDbEntity cellarMemberIntegralChangeRecordDb
    ){
        cellarMemberIntegralChangeRecordDb.setMemberId(cellarMemberDbEntity.getMemberId());
        PageUtils page = cellarMemberIntegralChangeRecordDbService.queryPage(cellarMemberIntegralChangeRecordDb);

        return R.data(page);
    }

}
