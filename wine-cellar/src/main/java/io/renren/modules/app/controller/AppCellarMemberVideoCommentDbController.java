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
import io.renren.modules.cellar.service.CellarMemberVideoCommentDbService;
import io.renren.modules.cellar.service.CellarMemberVideoDbService;
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
import java.util.List;


/**
 * 会员交友视频评论记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 14:11:13
 */
@RestController
@RequestMapping("app/cellarmembervideocommentdb")
@Api(value="APP会员交友视频评论记录表",tags="APP会员交友视频评论记录表")
public class AppCellarMemberVideoCommentDbController {
    @Autowired
    private CellarMemberVideoCommentDbService cellarMemberVideoCommentDbService;
    @Autowired
    private CellarMemberVideoDbService cellarMemberVideoDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "视频评论列表",notes = "视频评论列表",response = CellarMemberVideoCommentDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberVideoId",value="会员交友视频id",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberVideoCommentDbEntity cellarMemberVideoCommentDb
    ){
        PageUtils page = cellarMemberVideoCommentDbService.queryPage(cellarMemberVideoCommentDb);

        List<CellarMemberVideoCommentDbEntity> list = (List<CellarMemberVideoCommentDbEntity>) page.getList();
        list.forEach( cellarMemberVideoCommentDbEntity -> cellarMemberVideoCommentDbEntity.setThumbMemberId(cellarMemberDbEntity.getMemberId()));
        return R.data(page);
    }


    /**
     * 视频评论
     */
    @PostMapping("/comment")
    @ApiOperation(value = "视频评论",notes = "视频评论",response = CellarMemberVideoCommentDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="commentContent",value="评论内容",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberVideoId",value="会员交友视频id",dataType="String",required=false,paramType="query"),
    })
    public R comment(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberVideoCommentDbEntity cellarMemberVideoCommentDb
    ){
        Assert.isNull(cellarMemberVideoCommentDb.getMemberVideoId(),"会员交友视频id不能为空");
        Assert.isNull(cellarMemberVideoCommentDb.getCommentContent(),"评论内容不能为空");
        /**
         * 保存用户评论
         */
        cellarMemberVideoCommentDb.setCommentMemberId(cellarMemberDbEntity.getMemberId());
        cellarMemberVideoCommentDb.setCommentThumbNumber(Constants.Number.zero.getKey());
        cellarMemberVideoCommentDb.setCreateTime(new Date());
        cellarMemberVideoCommentDb.setState(Constants.STATE.zero.getKey());
        cellarMemberVideoCommentDbService.save(cellarMemberVideoCommentDb);
        /**
         * 增加视频评论数量
         */
        CellarMemberVideoDbEntity cellarMemberVideoDbEntity = cellarMemberVideoDbService.getById(cellarMemberVideoCommentDb.getMemberVideoId());
        Assert.isNull(cellarMemberVideoDbEntity,"会员交友视频不存在");
        cellarMemberVideoDbEntity.setCommentNumber(cellarMemberVideoDbEntity.getCommentNumber() + Constants.Number.one.getKey());
        cellarMemberVideoDbService.updateById(cellarMemberVideoDbEntity);
        return R.ok();
    }

}
