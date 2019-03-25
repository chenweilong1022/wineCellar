package io.renren.modules.app.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarMemberAddressDbEntity;
import io.renren.modules.cellar.entity.CellarMemberBalanceChangeRecordDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarMemberBalanceChangeRecordDbService;
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
 * APP会员余额变动记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-25 11:07:23
 */
@RestController
@RequestMapping("app/cellarmemberbalancechangerecorddb")
@Api(value="APP会员余额变动记录表",tags="APP会员余额变动记录表")
public class AppCellarMemberBalanceChangeRecordDbController {
    @Autowired
    private CellarMemberBalanceChangeRecordDbService cellarMemberBalanceChangeRecordDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "会员余额变动记录列表",notes = "会员余额变动记录列表",response = CellarMemberAddressDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="key",value="搜索条件",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="page",value="当前页数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="limit",value="每页个数",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberBalanceChangeRecordDbEntity cellarMemberBalanceChangeRecordDb
    ){
        cellarMemberBalanceChangeRecordDb.setMemberId(cellarMemberDbEntity.getMemberId());
        PageUtils page = cellarMemberBalanceChangeRecordDbService.queryPage(cellarMemberBalanceChangeRecordDb);

        return R.data(page);
    }
}
