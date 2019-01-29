package io.renren.modules.app.controller;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import io.renren.common.constants.Constants;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.app.form.LoginForm;
import io.renren.modules.app.service.UserService;
import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.cellar.entity.CellarMemberCaptchaEntity;
import io.renren.modules.cellar.service.CellarMemberCaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("sendCaptcha")
    @ApiOperation("发送验证码")
    public R sendCaptcha(
            CellarMemberCaptchaEntity cellarMemberCaptchaEntity
    ){
        Assert.isNull(cellarMemberCaptchaEntity,"手机号不能为空");
        Assert.isBlank(cellarMemberCaptchaEntity.getMemberMobile(), "手机号不能为空");
        Assert.isBlank(cellarMemberCaptchaEntity.getUuid(), "uuid不能为空");
        Assert.isPhone(cellarMemberCaptchaEntity.getMemberMobile());
        /**
         * 生成验证码
         * 保存验证码数据
         */
        String code = RandomUtil.randomNumbers(Constants.Number.six.getKey());//验证码
        cellarMemberCaptchaEntity.setCode(code);//设置验证码
        cellarMemberCaptchaEntity.setExpireTime(DateUtils.addDateMinutes(new Date(), Constants.Number.five.getKey()));//过期时间5分钟
        cellarMemberCaptchaService.save(cellarMemberCaptchaEntity);//保存验证码

        return R.ok();
    }


}
