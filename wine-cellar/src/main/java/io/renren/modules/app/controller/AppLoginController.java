package io.renren.modules.app.controller;


import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.app.form.LoginForm;
import io.renren.modules.app.service.UserService;
import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarMemberDbService;
import io.renren.modules.sys.entity.SysJwtEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * APP登录授权
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/app")
@Api(value = "APP登录接口",tags = "APP登录接口")
public class AppLoginController {
    @Autowired
    private CellarMemberDbService cellarMemberDbService;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 登录
     */
    @PostMapping("login")
    @ApiOperation("登录")
    public R login(
            CellarMemberDbEntity cellarMemberDbEntity
    ){
        /**
         * 校验表单
         */
        Assert.isNull(cellarMemberDbEntity,"登录失败");
        Assert.isBlank(cellarMemberDbEntity.getMobilePhone(), "手机号不能为空");
        Assert.isBlank(cellarMemberDbEntity.getPassword(), "密码不能为空");
        Assert.isPhone(cellarMemberDbEntity.getMobilePhone());
        /**
         * 登录
         */
        CellarMemberDbEntity cellarMemberDbEntityLogin = cellarMemberDbService.login(cellarMemberDbEntity);
        //生成token
        String token = jwtUtils.generateToken(cellarMemberDbEntityLogin.getMemberId());

        /**
         * 返回前端token
         */
        SysJwtEntity sysJwtEntity = new SysJwtEntity();
        sysJwtEntity.setToken(token);
        sysJwtEntity.setExpire(jwtUtils.getExpire());
        /**
         * 修改登录token
         */
        cellarMemberDbEntityLogin.setLoginToken(token);
        cellarMemberDbService.updateById(cellarMemberDbEntityLogin);
        return R.data(sysJwtEntity);
    }

}
