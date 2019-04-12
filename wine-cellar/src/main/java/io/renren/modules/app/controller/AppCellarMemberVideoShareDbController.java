package io.renren.modules.app.controller;

import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.entity.CellarMemberVideoCommentDbEntity;
import io.renren.modules.cellar.entity.CellarMemberVideoDbEntity;
import io.renren.modules.cellar.entity.CellarMemberVideoShareDbEntity;
import io.renren.modules.cellar.service.CellarMemberVideoDbService;
import io.renren.modules.cellar.service.CellarMemberVideoShareDbService;
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
 * 会员交友视频分享记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 15:10:37
 */
@RestController
@RequestMapping("app/cellarmembervideosharedb")
@Api(value="APP会员交友视频分享记录表",tags="APP会员交友视频分享记录表")
public class AppCellarMemberVideoShareDbController {
    @Autowired
    private CellarMemberVideoShareDbService cellarMemberVideoShareDbService;
    @Autowired
    private CellarMemberVideoDbService cellarMemberVideoDbService;

    /**
     * 保存
     */
    @PostMapping("/share")
    @ApiOperation(value = "视频分享",notes = "视频分享",response = CellarMemberVideoShareDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberVideoId",value="会员交友视频id",dataType="String",required=false,paramType="query"),
    })
    public R share(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberVideoShareDbEntity cellarMemberVideoShareDb
    ){
        /**
         * 校验
         */
        Assert.isNull(cellarMemberVideoShareDb.getMemberVideoId(),"会员交友视频id不能为空");
        /**
         * 保存视频分享记录
         */
        cellarMemberVideoShareDb.setShareMemberId(cellarMemberDbEntity.getMemberId());
        cellarMemberVideoShareDb.setCreateTime(new Date());
        cellarMemberVideoShareDb.setState(Constants.STATE.zero.getKey());
        cellarMemberVideoShareDbService.save(cellarMemberVideoShareDb);
        /**
         * 增加视频分享数量
         */
        CellarMemberVideoDbEntity cellarMemberVideoDbEntity = cellarMemberVideoDbService.getById(cellarMemberVideoShareDb.getMemberVideoId());
        Assert.isNull(cellarMemberVideoDbEntity,"会员交友视频不存在");
        cellarMemberVideoDbEntity.setShareNumber(cellarMemberVideoDbEntity.getShareNumber() + Constants.Number.one.getKey());
        cellarMemberVideoDbService.updateById(cellarMemberVideoDbEntity);
        return R.ok();
    }

}
