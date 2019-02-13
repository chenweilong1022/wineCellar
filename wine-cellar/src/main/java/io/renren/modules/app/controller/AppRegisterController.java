package io.renren.modules.app.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.app.form.RegisterForm;
import io.renren.modules.app.service.UserService;
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
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(value = "APP注册接口",tags = "APP注册接口")
public class AppRegisterController {

    @Autowired
    private CellarMemberCaptchaService cellarMemberCaptchaService;
    @Autowired
    private CellarMemberDbService cellarMemberDbService;

    @PostMapping("register")
    @ApiOperation("注册")
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
        Assert.isNull(cellarMemberCaptchaEntity,"注册失败");
        Assert.isBlank(cellarMemberCaptchaEntity.getMemberMobile(), "手机号不能为空");
        Assert.isBlank(cellarMemberCaptchaEntity.getCode(), "验证码不能为空");
        Assert.isBlank(cellarMemberCaptchaEntity.getUuid(), "uuid不能为空");
        Assert.isPhone(cellarMemberCaptchaEntity.getMemberMobile());
        Assert.isBlank(cellarMemberCaptchaEntity.getPassword(),"密码不能为空");
        /**
         * 判断手机号是否注册
         */
        CellarMemberDbEntity cellarMemberDbEntityByPhone = cellarMemberDbService.getOne(new QueryWrapper<CellarMemberDbEntity>().lambda()
                .eq(CellarMemberDbEntity::getMobilePhone, cellarMemberCaptchaEntity.getMemberMobile()));

        if (cellarMemberDbEntityByPhone != null) {
            return R.error("手机号已经注册");
        }

        /**
         * 校验验证码
         */
        cellarMemberCaptchaService.checkCode(cellarMemberCaptchaEntity.getUuid(),cellarMemberCaptchaEntity.getMemberMobile(),cellarMemberCaptchaEntity.getCode());
        /**
         * 插入会员
         */
        CellarMemberDbEntity cellarMemberDbEntity = new CellarMemberDbEntity();
        cellarMemberDbEntity.setCreateTime(new Date());
        cellarMemberDbEntity.setState(Constants.STATE.zero.getKey());
        cellarMemberDbEntity.setLevel(Constants.LEVEL.one.getKey());
        cellarMemberDbEntity.setNickname(cellarMemberCaptchaEntity.getMemberMobile());
        cellarMemberDbEntity.setMobilePhone(cellarMemberCaptchaEntity.getMemberMobile());
        cellarMemberDbEntity.setPassword(DigestUtils.sha256Hex(cellarMemberCaptchaEntity.getPassword()));
        cellarMemberDbEntity.setBalance(BigDecimal.ZERO);
        cellarMemberDbEntity.setIntegral(BigDecimal.ZERO);
        cellarMemberDbEntity.setCardBalance(BigDecimal.ZERO);
        cellarMemberDbService.save(cellarMemberDbEntity);
        return R.ok();
    }
}
