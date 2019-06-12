package io.renren.modules.app.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.cellar.entity.CellarMemberDynamicCommentDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDynamicDbEntity;
import io.renren.modules.cellar.service.CellarMemberDynamicCommentDbService;
import io.renren.modules.cellar.service.CellarMemberDynamicDbService;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Date;


/**
 * 会员动态评论表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-05-15 13:18:56
 */
@RestController
@RequestMapping("app/cellarmemberdynamiccommentdb")
@Api(value="APP会员动态评论表",tags="APP会员动态评论表")
public class AppCellarMemberDynamicCommentDbController extends AbstractController {
    @Autowired
    private CellarMemberDynamicCommentDbService cellarMemberDynamicCommentDbService;
    @Autowired
    private CellarMemberDynamicDbService cellarMemberDynamicDbService;

    /**
     * 会员动态评论列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "会员动态评论列表",notes = "会员动态评论列表",response = R.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="memberDynamicId",value="会员动态id",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore CellarMemberDynamicCommentDbEntity cellarMemberDynamicCommentDb
    ){
        Assert.isNull(cellarMemberDynamicCommentDb.getMemberDynamicId(),"动态id不能为空");
        PageUtils page = cellarMemberDynamicCommentDbService.queryPage(cellarMemberDynamicCommentDb);
        return R.data(page);
    }

    /**
     * 会员动态评论接口
     */
    @PostMapping("/save")
    @ApiOperation(value = "会员动态评论接口",notes = "会员动态评论接口",response = R.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberDynamicId",value="会员动态id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="commentContent",value="评论内容",dataType="String",required=false,paramType="query"),
    })
    @Transactional
    public R save(
            @ApiIgnore CellarMemberDynamicCommentDbEntity cellarMemberDynamicCommentDb
    ){
        Assert.isNull(cellarMemberDynamicCommentDb.getMemberDynamicId(),"动态id不能为空");
        CellarMemberDynamicDbEntity cellarMemberDynamicDbEntity = cellarMemberDynamicDbService.getById(cellarMemberDynamicCommentDb.getMemberDynamicId());
        Assert.isNull(cellarMemberDynamicDbEntity,"动态不存在");
        Assert.isNull(cellarMemberDynamicCommentDb.getCommentContent(),"评论内容不能为空");

        BeanUtils.copyProperties(this.cellarMemberDbEntity,cellarMemberDynamicCommentDb);
        cellarMemberDynamicCommentDb.setCreateTime(new Date());
        cellarMemberDynamicCommentDb.setState(Constants.STATE.zero.getKey());
        cellarMemberDynamicCommentDbService.save(cellarMemberDynamicCommentDb);
        cellarMemberDynamicDbEntity.setCommentNumber(cellarMemberDynamicDbEntity.getCommentNumber() + Constants.Number.one.getKey());
        cellarMemberDynamicDbService.updateById(cellarMemberDynamicDbEntity);
        return R.ok();
    }

    /**
     * 删除动态评论
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除动态评论",notes = "删除动态评论",response = R.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberDynamicCommentIds",value="会员动态评论id",dataType="Long",required=false,paramType="query",allowMultiple = true),
    })
    public R delete(
            @ApiIgnore Long[] memberDynamicCommentIds
    ){
        Assert.isNullArray(memberDynamicCommentIds,"会员动态评论id不能为空");

        /**
         * 根据动态评论ids查询动态
         */
        cellarMemberDynamicCommentDbService.listByIds(Arrays.asList(memberDynamicCommentIds)).forEach(cellarMemberDynamicCommentDbEntity -> {
            /**
             * 删除
             */
            boolean b = cellarMemberDynamicCommentDbService.remove(new QueryWrapper<CellarMemberDynamicCommentDbEntity>().lambda()
                    .eq(CellarMemberDynamicCommentDbEntity::getMemberDynamicCommentId, cellarMemberDynamicCommentDbEntity.getMemberDynamicCommentId())
//                    .eq(CellarMemberDynamicCommentDbEntity::getMemberId, this.cellarMemberDbEntity.getMemberId())
            );
            if (b) {
                /**
                 * 修改数量
                 */
                CellarMemberDynamicDbEntity cellarMemberDynamicDbEntity = cellarMemberDynamicDbService.getById(cellarMemberDynamicCommentDbEntity.getMemberDynamicId());
                cellarMemberDynamicDbEntity.setCommentNumber(cellarMemberDynamicDbEntity.getCommentNumber() - Constants.Number.one.getKey());
                cellarMemberDynamicDbService.updateById(cellarMemberDynamicDbEntity);
            }
        });
        return R.ok();
    }

}
