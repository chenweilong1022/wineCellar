package io.renren.modules.app.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.cellar.entity.CellarMemberCaptchaEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarMemberCaptchaService;
import io.renren.modules.cellar.service.CellarMemberDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 注册
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-26 17:27
 */
@RestController
@RequestMapping("/app")
@Api(value = "APP重置密码接口",tags = "APP重置密码接口")
public class AppResetPasswordController {

    @Autowired
    private CellarMemberCaptchaService cellarMemberCaptchaService;
    @Autowired
    private CellarMemberDbService cellarMemberDbService;

    @PostMapping("forgotPassword")
    @ApiOperation(value = "忘记密码",notes = "忘记密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name="uuid",value="随机数,与注册接口随机数保持一致",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="code",value="验证码",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="password",value="密码",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberMobile",value="手机号",dataType="String",required=false,paramType="query"),
    })
    public R register(
            @ApiIgnore CellarMemberCaptchaEntity cellarMemberCaptchaEntity
    ){
        /**
         * 校验表单
         */
        Assert.isNull(cellarMemberCaptchaEntity,"手机号不能为空");
        Assert.isBlank(cellarMemberCaptchaEntity.getMemberMobile(), "手机号不能为空");
        Assert.isPhone(cellarMemberCaptchaEntity.getMemberMobile());
        Assert.isBlank(cellarMemberCaptchaEntity.getCode(), "验证码不能为空");
        Assert.isBlank(cellarMemberCaptchaEntity.getUuid(), "uuid不能为空");
        Assert.isBlank(cellarMemberCaptchaEntity.getPassword(),"密码不能为空");
        /**
         * 判断手机号是否注册
         */
        CellarMemberDbEntity cellarMemberDbEntityByPhone = cellarMemberDbService.getOne(new QueryWrapper<CellarMemberDbEntity>().lambda()
                .eq(CellarMemberDbEntity::getMobilePhone, cellarMemberCaptchaEntity.getMemberMobile()));
        if (cellarMemberDbEntityByPhone == null) {
            return R.error("该会员没有注册");
        }
        /**
         * 校验验证码
         */
        cellarMemberCaptchaService.checkCode(cellarMemberCaptchaEntity.getUuid(),cellarMemberCaptchaEntity.getMemberMobile(),cellarMemberCaptchaEntity.getCode());
        /**
         * 修改密码
         */
        cellarMemberDbEntityByPhone.setPassword(DigestUtils.sha256Hex(cellarMemberCaptchaEntity.getPassword()));
        cellarMemberDbService.updateById(cellarMemberDbEntityByPhone);
        return R.ok();
    }
}
