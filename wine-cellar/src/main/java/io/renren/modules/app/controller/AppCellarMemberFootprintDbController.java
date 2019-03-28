package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.entity.CellarMemberFootprintDbEntity;
import io.renren.modules.cellar.service.CellarMemberFootprintDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;


/**
 * APP会员足迹表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-25 17:29:13
 */
@RestController
@RequestMapping("app/cellarmemberfootprintdb")
@Api(value="APP会员足迹表",tags="APP会员足迹表")
public class AppCellarMemberFootprintDbController {
    @Autowired
    private CellarMemberFootprintDbService cellarMemberFootprintDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("查看用户足迹列表")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberFootprintDbEntity cellarMemberFootprintDb
    ){
        cellarMemberFootprintDb.setMemberId(cellarMemberDbEntity.getMemberId());
        PageUtils page = cellarMemberFootprintDbService.queryPage(cellarMemberFootprintDb);

        return R.data(page);
    }

    /**
     * 清空
     */
    @PostMapping("/setEmpty")
    @ApiOperation("清空足迹")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
    })
    public R setEmpty(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberFootprintDbEntity cellarMemberFootprintDb
    ){

        cellarMemberFootprintDbService.remove(new QueryWrapper<CellarMemberFootprintDbEntity>().lambda()
                .eq(CellarMemberFootprintDbEntity::getMemberId,cellarMemberDbEntity.getMemberId())
        );

        return R.ok();
    }

    /**
     * 删除足迹
     */
    @PostMapping("/delete")
    @ApiOperation("删除足迹")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
    })
    public R delete(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
             Long[] memberFootprintId
    ){

        cellarMemberFootprintDbService.remove(new QueryWrapper<CellarMemberFootprintDbEntity>().lambda()
                .in(CellarMemberFootprintDbEntity::getMemberId,memberFootprintId)
                .eq(CellarMemberFootprintDbEntity::getMemberId,cellarMemberDbEntity.getMemberId())
        );

        return R.ok();
    }

}
