package io.renren.modules.app.controller;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.cellar.entity.CellarMemberDynamicDbEntity;
import io.renren.modules.cellar.entity.CellarStoreDbEntity;
import io.renren.modules.cellar.service.CellarMemberDynamicDbService;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;


/**
 * 会员动态表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-05-13 09:58:25
 */
@RestController
@RequestMapping("app/cellarmemberdynamicdb")
@Api(value="APP会员动态表",tags="APP会员动态表")
public class AppCellarMemberDynamicDbController extends AbstractController {
    @Autowired
    private CellarMemberDynamicDbService cellarMemberDynamicDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "APP会员动态表列表",notes = "APP会员动态表列表",response = CellarMemberDynamicDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="key",value="搜索条件",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="page",value="当前页数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="limit",value="每页个数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberId",value="用户id,此接口使用memberId来标识用户",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore CellarMemberDynamicDbEntity cellarMemberDynamicDb
    ){
//        Assert.isNull(cellarMemberDynamicDb.getMemberId(),"用户id不能为空");
        cellarMemberDynamicDb.setMemberId(cellarMemberDynamicDb.getMemberId());
        PageUtils page = cellarMemberDynamicDbService.queryPage(cellarMemberDynamicDb);
        return R.data(page);
    }
    /**
     * 发布动态
     */
    @PostMapping("/release")
    @ApiOperation("发布动态")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="writtenContent",value="文字内容",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="imageContent",value="图片内容",dataType="String",required=false,paramType="query"),
    })
    public R release(
            @ApiIgnore CellarMemberDynamicDbEntity cellarMemberDynamicDb
    ){

        Assert.isBlank(cellarMemberDynamicDb.getWrittenContent(),"文字内容不能为空");
        cellarMemberDynamicDb.setCreateTime(new Date());
        cellarMemberDynamicDb.setState(Constants.STATE.zero.getKey());
        cellarMemberDynamicDb.setThumbNumber(Constants.Number.zero.getKey());
        cellarMemberDynamicDb.setCommentNumber(Constants.Number.zero.getKey());
        cellarMemberDynamicDb.setMemberId(this.cellarMemberDbEntity.getMemberId());
        cellarMemberDynamicDb.setNickname(this.cellarMemberDbEntity.getNickname());
        cellarMemberDynamicDb.setHeadPortrait(this.cellarMemberDbEntity.getHeadPortrait());
        cellarMemberDynamicDbService.save(cellarMemberDynamicDb);
        return R.ok();
    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    @RequiresPermissions("cellar:cellarmemberdynamicdb:update")
//    public R update(@RequestBody CellarMemberDynamicDbEntity cellarMemberDynamicDb){
//			cellarMemberDynamicDbService.updateById(cellarMemberDynamicDb);
//
//        return R.ok();
//    }
//
    /**
     * 删除动态
     */
    @PostMapping("/delete")
    @ApiOperation("删除动态")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberDynamicIds",value="会员动态id",dataType="Long",required=false,paramType="query",allowMultiple = true),
    })
    public R delete(
            @ApiIgnore Long[] memberDynamicIds
    ){
        Assert.isNullArray(memberDynamicIds,"会员动态id不能为空");
        cellarMemberDynamicDbService.remove(new QueryWrapper<CellarMemberDynamicDbEntity>().lambda()
                .in(CellarMemberDynamicDbEntity::getMemberDynamicId,memberDynamicIds)
                .eq(CellarMemberDynamicDbEntity::getMemberId,this.cellarMemberDbEntity.getMemberId())
        );
        return R.ok();
    }

}
