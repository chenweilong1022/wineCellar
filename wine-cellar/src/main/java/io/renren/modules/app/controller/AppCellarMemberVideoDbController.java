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
import io.renren.modules.cellar.entity.CellarMemberVideoDbEntity;
import io.renren.modules.cellar.entity.CellarMemberVideoThumbDbEntity;
import io.renren.modules.cellar.service.CellarMemberVideoDbService;
import io.renren.modules.cellar.service.CellarMemberVideoThumbDbService;
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
 * 会员交友视频表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 10:54:07
 */
@RestController
@RequestMapping("app/cellarmembervideodb")
@Api(value="APP会员交友视频表",tags="APP会员交友视频表")
public class AppCellarMemberVideoDbController {
    @Autowired
    private CellarMemberVideoDbService cellarMemberVideoDbService;
    @Autowired
    private CellarMemberVideoThumbDbService cellarMemberVideoThumbDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "用户视频列表",notes = "用户视频列表",response = CellarMemberVideoDbEntity.class)
//    @Login
    @ApiImplicitParams({
//            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberId",value="用户memberId",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberVideoDbEntity cellarMemberVideoDb
    ){
//        cellarMemberVideoDb.setMemberId(cellarMemberDbEntity.getMemberId());
        PageUtils page = cellarMemberVideoDbService.queryPage(cellarMemberVideoDb);

        /**
         * 查看用户是否点赞
         */
        List<CellarMemberVideoDbEntity> list = (List<CellarMemberVideoDbEntity>) page.getList();
        list.forEach( cellarMemberVideoDbEntity -> cellarMemberVideoDbEntity.setThumbMemberId(cellarMemberVideoDb.getMemberId()));
        return R.data(page);
    }

    /**
     * 列表
     */
    @GetMapping("/listAll")
    @ApiOperation(value = "视频列表--抖音",notes = "视频列表--抖音",response = CellarMemberVideoDbEntity.class)
//    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
    })
    public R listAll(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberVideoDbEntity cellarMemberVideoDb
    ){
        PageUtils page = cellarMemberVideoDbService.queryPage(cellarMemberVideoDb);

        /**
         * 查看用户是否点赞
         */
        List<CellarMemberVideoDbEntity> list = (List<CellarMemberVideoDbEntity>) page.getList();
        list.forEach( cellarMemberVideoDbEntity -> cellarMemberVideoDbEntity.setThumbMemberId(cellarMemberDbEntity == null?null:cellarMemberDbEntity.getMemberId()));
        return R.data(page);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "用户上传视频",notes = "用户上传视频",response = CellarMemberVideoDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="title",value="标题",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="introduction",value="简介",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="videoId",value="阿里云视频id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="cover",value="封面图",dataType="String",required=false,paramType="query"),
    })
    public R save(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberVideoDbEntity cellarMemberVideoDb
    ){

//        https://outin-8a6687975a9611e98dcc00163e1a3b4a.oss-cn-shanghai.aliyuncs.com/image/cover/FB0BEDA284E0401DB5BCAA9532896D96-6-2.png?Expires=1555064305&amp;OSSAccessKeyId=LTAItL9Co9nUDU5r&amp;Signature=t77fy52knLg7gZg2dcKZxDJ2BaY%3D
        cellarMemberVideoDb.setState(Constants.STATE.zero.getKey());
        cellarMemberVideoDb.setCreateTime(new Date());
        cellarMemberVideoDb.setMemberId(cellarMemberDbEntity.getMemberId());
        cellarMemberVideoDb.setHeadPortrait(cellarMemberDbEntity.getHeadPortrait());
        cellarMemberVideoDb.setCommentNumber(Constants.Number.zero.getKey());
        cellarMemberVideoDb.setThumbNumber(Constants.Number.zero.getKey());
        cellarMemberVideoDb.setShareNumber(Constants.Number.zero.getKey());
        cellarMemberVideoDbService.save(cellarMemberVideoDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除视频",notes = "删除视频",response = CellarMemberVideoDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberVideoIds",value="会员交友视频id",dataType="String",required=false,paramType="query"),
    })
    public R delete(
            Long[] memberVideoIds
    ){
        cellarMemberVideoDbService.removeByIds(Arrays.asList(memberVideoIds));
        return R.ok();
    }

    /**
     * 视频点赞/取消点赞
     */
    @PostMapping("/thumb")
    @ApiOperation(value = "视频点赞/取消点赞",notes = "视频点赞/取消点赞",response = CellarMemberVideoDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberVideoId",value="会员交友视频id",dataType="String",required=false,paramType="query"),
    })
    public R thumb(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberVideoDbEntity cellarMemberVideoDb
    ){
        /**
         * 校验前端数据
         * 并且修改点赞数量
         */
        Assert.isNull(cellarMemberVideoDb.getMemberVideoId(),"会员交友视频id不能为空");
        CellarMemberVideoDbEntity cellarMemberVideoDbEntity = cellarMemberVideoDbService.getById(cellarMemberVideoDb.getMemberVideoId());
        Assert.isNull(cellarMemberVideoDbEntity,"会员交友视频不存在");

        /**
         * 判断用户点赞状态
         */
        CellarMemberVideoThumbDbEntity memberVideoThumbDbEntity = cellarMemberVideoThumbDbService.getOne(new QueryWrapper<CellarMemberVideoThumbDbEntity>().lambda()
                .eq(CellarMemberVideoThumbDbEntity::getMemberVideoId, cellarMemberVideoDb.getMemberVideoId())
                .eq(CellarMemberVideoThumbDbEntity::getThumbMemberId, cellarMemberDbEntity.getMemberId())
                .eq(CellarMemberVideoThumbDbEntity::getState, Constants.STATE.zero.getKey())
        );
        /**
         * 如果为空
         */
        if (ObjectUtil.isNull(memberVideoThumbDbEntity)) {
            /**
             * 增加点赞数量
             */
            cellarMemberVideoDbEntity.setThumbNumber(cellarMemberVideoDbEntity.getThumbNumber() + Constants.Number.one.getKey());
            cellarMemberVideoDbService.updateById(cellarMemberVideoDbEntity);
            /**
             * 增加点赞记录
             */
            CellarMemberVideoThumbDbEntity cellarMemberVideoThumbDbEntity = new CellarMemberVideoThumbDbEntity();
            cellarMemberVideoThumbDbEntity.setState(Constants.STATE.zero.getKey());
            cellarMemberVideoThumbDbEntity.setCreateTime(new Date());
            cellarMemberVideoThumbDbEntity.setMemberVideoId(cellarMemberVideoDb.getMemberVideoId());
            cellarMemberVideoThumbDbEntity.setThumbMemberId(cellarMemberDbEntity.getMemberId());
            cellarMemberVideoThumbDbService.save(cellarMemberVideoThumbDbEntity);
        }else {
            /**
             * 减少数量
             */
            cellarMemberVideoDbEntity.setThumbNumber(cellarMemberVideoDbEntity.getThumbNumber() - Constants.Number.one.getKey());
            cellarMemberVideoDbService.updateById(cellarMemberVideoDbEntity);
            /**
             * 删除记录
             */
            cellarMemberVideoThumbDbService.removeById(memberVideoThumbDbEntity.getMemberVideoThumbId());
        }

        return R.ok();
    }

}
