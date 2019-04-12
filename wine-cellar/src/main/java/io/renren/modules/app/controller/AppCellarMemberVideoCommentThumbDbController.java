package io.renren.modules.app.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.entity.CellarMemberVideoCommentDbEntity;
import io.renren.modules.cellar.entity.CellarMemberVideoCommentThumbDbEntity;
import io.renren.modules.cellar.service.CellarMemberVideoCommentDbService;
import io.renren.modules.cellar.service.CellarMemberVideoCommentThumbDbService;
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
 * APP会员交友视频评论点赞记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 16:09:08
 */
@RestController
@RequestMapping("app/cellarmembervideocommentthumbdb")
@Api(value="APP会员交友视频评论点赞记录表",tags="APP会员交友视频评论点赞记录表")
public class AppCellarMemberVideoCommentThumbDbController {
    @Autowired
    private CellarMemberVideoCommentThumbDbService cellarMemberVideoCommentThumbDbService;
    @Autowired
    private CellarMemberVideoCommentDbService cellarMemberVideoCommentDbService;


    /**
     * 会员交友视频评论点赞/取消点赞
     */
    @PostMapping("/commentThumb")
    @ApiOperation(value = "会员交友视频评论点赞/取消点赞",notes = "会员交友视频评论点赞/取消点赞",response = CellarMemberVideoCommentDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberVideoCommentId",value="会员交友视频评论id",dataType="String",required=false,paramType="query"),
    })
    public R commentThumb(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberVideoCommentThumbDbEntity cellarMemberVideoCommentThumbDb
    ){

        /**
         * 校验
         */
        Assert.isNull(cellarMemberVideoCommentThumbDb.getMemberVideoCommentId(),"会员交友视频评论id不能为空");
        CellarMemberVideoCommentDbEntity cellarMemberVideoCommentDbEntity = cellarMemberVideoCommentDbService.getById(cellarMemberVideoCommentThumbDb.getMemberVideoCommentId());
        Assert.isNull(cellarMemberVideoCommentDbEntity,"会员交友视频评论不存在");


        /**
         * 查询用户给改评论是否已经点赞
         */
        CellarMemberVideoCommentThumbDbEntity cellarMemberVideoCommentThumbDbEntity = cellarMemberVideoCommentThumbDbService.getOne(new QueryWrapper<CellarMemberVideoCommentThumbDbEntity>().lambda()
                .eq(CellarMemberVideoCommentThumbDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(CellarMemberVideoCommentThumbDbEntity::getMemberVideoId, cellarMemberVideoCommentDbEntity.getMemberVideoId())
                .eq(CellarMemberVideoCommentThumbDbEntity::getMemberVideoCommentId, cellarMemberVideoCommentThumbDb.getMemberVideoCommentId())
                .eq(CellarMemberVideoCommentThumbDbEntity::getThumbMemberId, cellarMemberDbEntity.getMemberId())
        );

        /**
         * 如果点赞
         */
        if (ObjectUtil.isNull(cellarMemberVideoCommentThumbDbEntity)) {
            /**
             * 保存点赞记录
             */
            cellarMemberVideoCommentThumbDb.setCreateTime(new Date());
            cellarMemberVideoCommentThumbDb.setState(Constants.STATE.zero.getKey());
            cellarMemberVideoCommentThumbDb.setThumbMemberId(cellarMemberDbEntity.getMemberId());
            cellarMemberVideoCommentThumbDb.setMemberVideoId(cellarMemberVideoCommentDbEntity.getMemberVideoId());
            cellarMemberVideoCommentThumbDbService.save(cellarMemberVideoCommentThumbDb);

            /**
             * 点赞数量 +1
             */

            cellarMemberVideoCommentDbEntity.setCommentThumbNumber(cellarMemberVideoCommentDbEntity.getCommentThumbNumber() + Constants.Number.one.getKey());
            cellarMemberVideoCommentDbService.updateById(cellarMemberVideoCommentDbEntity);

        }else {
            /**
             * 删除点赞记录
             */
            cellarMemberVideoCommentThumbDbService.removeById(cellarMemberVideoCommentThumbDbEntity.getMemberVideoCommentThumbId());

            /**
             * 点赞数量 -1
             */
            cellarMemberVideoCommentDbEntity.setCommentThumbNumber(cellarMemberVideoCommentDbEntity.getCommentThumbNumber() - Constants.Number.one.getKey());
            cellarMemberVideoCommentDbService.updateById(cellarMemberVideoCommentDbEntity);
        }

        return R.ok();
    }
}
