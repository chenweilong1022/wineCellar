package io.renren.modules.app.controller;

import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarCouponDbEntity;
import io.renren.modules.cellar.entity.CellarFeedbackDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarFeedbackDbService;
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
 * APP意见反馈表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-26 17:15:05
 */
@RestController
@RequestMapping("app/cellarfeedbackdb")
@Api(value="APP意见反馈表",tags="APP意见反馈表")
public class AppCellarFeedbackDbController {
    @Autowired
    private CellarFeedbackDbService cellarFeedbackDbService;

    /**
     * 保存
     */
    @PostMapping("/save")
    @Login
    @ApiOperation(value = "保存",notes = "保存")
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="feedbackContent",value="反馈内容",dataType="String",required=false,paramType="query"),
    })
    public R save(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarFeedbackDbEntity cellarFeedbackDb
    ){
        Assert.isBlank(cellarFeedbackDb.getFeedbackContent(),"反馈意见不能为空");
        cellarFeedbackDb.setState(Constants.STATE.zero.getKey());
        cellarFeedbackDb.setCreateTime(new Date());
        cellarFeedbackDb.setMemberId(cellarMemberDbEntity == null ? null : cellarMemberDbEntity.getMemberId());
        cellarFeedbackDbService.save(cellarFeedbackDb);
        return R.ok();
    }

}
