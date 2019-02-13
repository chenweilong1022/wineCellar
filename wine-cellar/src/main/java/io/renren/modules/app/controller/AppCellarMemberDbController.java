package io.renren.modules.app.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarMemberDbService;
import io.renren.modules.sys.entity.SysJwtEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Map;


/**
 * app酒窖会员表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-01-16 17:34:20
 */
@RestController
@RequestMapping("app/cellarmemberdb")
@Api(value="APP会员接口",tags="APP会员接口")
public class AppCellarMemberDbController {
    @Autowired
    private CellarMemberDbService cellarMemberDbService;


    /**
     * 登录
     */
    @PostMapping("info")
    @ApiOperation("根据token查询用户信息")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用户校验当前用户",dataType="String",required=false,paramType="query"),
    })
    public R info(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity
    ){
        /**
         * 获取用户信息
         */
        CellarMemberDbEntity cellarMemberDbEntity1 = cellarMemberDbService.getById(cellarMemberDbEntity.getMemberId());
        return R.data(cellarMemberDbEntity1);
    }

}
