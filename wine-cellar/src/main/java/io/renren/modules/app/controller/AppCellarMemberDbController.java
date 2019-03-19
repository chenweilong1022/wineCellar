package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.annotation.MemberMessage;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarMemberCaptchaEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarMemberCaptchaService;
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
    @Autowired
    private CellarMemberCaptchaService cellarMemberCaptchaService;

    /**
     * 登录
     */
    @PostMapping("info")
    @ApiOperation(value = "根据token查询用户信息",notes = "根据token查询用户信息",response = CellarMemberDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
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

    /**
     * 修改用户基本信息
     */
    @PostMapping("update")
    @ApiOperation("修改用户基本信息")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="headPortrait",value="用户头像",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="nickname",value="用户昵称",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="gender",value="性别1:男 2:女",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="birthday",value="生日",dataType="date-time",required=false,paramType="query"),
    })
    public R update(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,//登录用户
            @ApiIgnore CellarMemberDbEntity cellarMemberDbEntityUpdate//修改信息
    ){

        Assert.isNull(cellarMemberDbEntityUpdate,"修改信息不能为空");
        /**
         * 修改用户信息
         */
        cellarMemberDbEntityUpdate.setMemberId(cellarMemberDbEntity.getMemberId());
        cellarMemberDbService.updateById(cellarMemberDbEntityUpdate);

        return R.ok();
    }

    /**
     * 用户手机号该绑
     */
    @PostMapping("toBind")
    @ApiOperation("用户手机号该绑")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="uuid",value="随机数,与注册接口随机数保持一致",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="code",value="验证码",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberMobile",value="手机号",dataType="String",required=false,paramType="query"),

    })
    public R toBind(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,//登录用户
            @ApiIgnore CellarMemberCaptchaEntity cellarMemberCaptchaEntity//修改信息
    ){

        /**
         * 校验表单
         */
        Assert.isNull(cellarMemberCaptchaEntity,"手机号不能为空");
        Assert.isBlank(cellarMemberCaptchaEntity.getMemberMobile(), "手机号不能为空");
        Assert.isPhone(cellarMemberCaptchaEntity.getMemberMobile());
        Assert.isBlank(cellarMemberCaptchaEntity.getCode(), "验证码不能为空");
        Assert.isBlank(cellarMemberCaptchaEntity.getUuid(), "uuid不能为空");

        /**
         * 判断手机号是否注册
         */
        CellarMemberDbEntity cellarMemberDbEntityByPhone = cellarMemberDbService.getOne(new QueryWrapper<CellarMemberDbEntity>().lambda()
                .eq(CellarMemberDbEntity::getMobilePhone, cellarMemberCaptchaEntity.getMemberMobile())
                .notIn(CellarMemberDbEntity::getMemberId,cellarMemberDbEntity.getMemberId()));
        if (cellarMemberDbEntityByPhone != null) {
            return R.error("该手机号已被使用");
        }
        /**
         * 校验验证码
         */
        cellarMemberCaptchaService.checkCode(cellarMemberCaptchaEntity.getUuid(),cellarMemberCaptchaEntity.getMemberMobile(),cellarMemberCaptchaEntity.getCode());
        /**
         * 手机号该绑
         */
        CellarMemberDbEntity cellarMemberDbEntityBind = new CellarMemberDbEntity();
        cellarMemberDbEntityBind.setMemberId(cellarMemberDbEntity.getMemberId());
        cellarMemberDbEntityBind.setMobilePhone(cellarMemberCaptchaEntity.getMemberMobile());
        cellarMemberDbService.updateById(cellarMemberDbEntityBind);

        return R.ok();
    }

}
