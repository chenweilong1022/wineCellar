package io.renren.modules.app.controller;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.sms.chuanglan.demo.SmsSendUtils;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.form.LoginForm;
import io.renren.modules.app.service.UserService;
import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.cellar.entity.CellarMemberCaptchaEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarMemberCaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * app验证码
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-01-29 14:59:19
 */
@RestController
@RequestMapping("/app/cellarmembercaptcha")
@Api(value = "APP验证码接口",tags = "APP验证码接口")
public class AppCaptchaController {

    @Autowired
    private CellarMemberCaptchaService cellarMemberCaptchaService;

    @Autowired
    private SmsSendUtils smsSendUtils;

    @PostMapping("sendCaptcha")
    @ApiOperation("发送验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name="uuid",value="随机数,与注册接口随机数保持一致",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberMobile",value="手机号",dataType="String",required=false,paramType="query"),
    })
    @Transactional
    public R sendCaptcha(
            @ApiIgnore CellarMemberCaptchaEntity cellarMemberCaptchaEntity
    ){
        Assert.isNull(cellarMemberCaptchaEntity,"手机号不能为空");
        Assert.isBlank(cellarMemberCaptchaEntity.getMemberMobile(), "手机号不能为空");
        Assert.isBlank(cellarMemberCaptchaEntity.getUuid(), "uuid不能为空");
        Assert.isPhone(cellarMemberCaptchaEntity.getMemberMobile());

        /**
         * 删除该手机号所有发送记录
         */
        cellarMemberCaptchaService.remove(new QueryWrapper<CellarMemberCaptchaEntity>().lambda().
                eq(CellarMemberCaptchaEntity::getMemberMobile,cellarMemberCaptchaEntity.getMemberMobile()));

        /**
         * 生成验证码
         * 保存验证码数据
         */
        String code = RandomUtil.randomNumbers(Constants.Number.six.getKey());//验证码
        cellarMemberCaptchaEntity.setCode(code);//设置验证码
        cellarMemberCaptchaEntity.setExpireTime(DateUtils.addDateMinutes(new Date(), Constants.Number.five.getKey()));//过期时间5分钟
        cellarMemberCaptchaService.save(cellarMemberCaptchaEntity);//保存验证码

        smsSendUtils.send(cellarMemberCaptchaEntity.getMemberMobile(),code);//发送验证码

        return R.ok();
    }


    /**
     * 校验验证码
     * @param cellarMemberDbEntity
     * @param cellarMemberCaptchaEntity
     * @return
     */
    @PostMapping("checkCode")
    @ApiOperation("校验验证码")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="uuid",value="随机数,与注册接口随机数保持一致",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="code",value="验证码",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberMobile",value="手机号",dataType="String",required=false,paramType="query"),
    })
    public R checkCode(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
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
        Assert.isTrue(!cellarMemberCaptchaEntity.getMemberMobile().equals(cellarMemberDbEntity.getMobilePhone()),"手机号与登录手机号不一致");
        /**
         * 校验验证码
         */
        cellarMemberCaptchaService.checkCode(cellarMemberCaptchaEntity.getUuid(),cellarMemberCaptchaEntity.getMemberMobile(),cellarMemberCaptchaEntity.getCode());
        return R.ok();
    }


}
