package io.renren.modules.app.controller;

import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.cellar.entity.CellarMemberCardDbEntity;
import io.renren.modules.cellar.service.CellarMemberCardDbService;
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
 * APP会员储值卡表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-28 13:34:38
 */
@RestController
@RequestMapping("app/cellarmembercarddb")
@Api(value="APP会员储值卡表",tags="APP会员储值卡表")
public class AppCellarMemberCardDbController {
    @Autowired
    private CellarMemberCardDbService cellarMemberCardDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "APP会员储值卡列表",notes = "APP会员储值卡列表",response = CellarMemberCardDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="key",value="搜索条件",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="page",value="当前页数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="limit",value="每页个数",dataType="String",required=false,paramType="query"),
    })
    public R list(
           @ApiIgnore CellarMemberCardDbEntity cellarMemberCardDb
    ){
        PageUtils page = cellarMemberCardDbService.queryPage(cellarMemberCardDb);
        return R.data(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info")
    @ApiOperation(value = "APP会员储值卡详情",notes = "APP会员储值卡详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="memberCardId",value="储值卡id",dataType="String",required=false,paramType="query"),
    })
    public R info(
            Long memberCardId
    ){
			CellarMemberCardDbEntity cellarMemberCardDb = cellarMemberCardDbService.getById(memberCardId);

        return R.data(cellarMemberCardDb);
    }

}
