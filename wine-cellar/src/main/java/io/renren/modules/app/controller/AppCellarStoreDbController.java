package io.renren.modules.app.controller;

import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.entity.CellarStoreDbEntity;
import io.renren.modules.cellar.service.CellarStoreDbService;
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
 * app酒窖店铺表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
@RestController
@RequestMapping("app/cellarstoredb")
@Api(value="APP酒窖店铺",tags="APP酒窖店铺")
public class AppCellarStoreDbController {
    @Autowired
    private CellarStoreDbService cellarStoreDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "app店铺列表",notes = "app店铺列表",response = CellarStoreDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="key",value="搜索条件",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="page",value="当前页数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="limit",value="每页个数",dataType="String",required=false,paramType="query"),
    })
    public R list(@ApiIgnore CellarStoreDbEntity cellarStoreDb){
        PageUtils page = cellarStoreDbService.queryPage(cellarStoreDb);

        return R.data(page);
    }


    /**
     * 店铺详情
     */
    @GetMapping("/info")
    @ApiOperation(value = "app店铺详情",notes = "app店铺详情",response = CellarStoreDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
    })
    public R info(@ApiIgnore CellarStoreDbEntity cellarStoreDbEntity){
        CellarStoreDbEntity cellarStoreDb = cellarStoreDbService.getById(cellarStoreDbEntity.getStoreId());
        return R.data(cellarStoreDb);
    }

//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    @RequiresPermissions("cellar:cellarstoredb:save")
//    public R save(@RequestBody CellarStoreDbEntity cellarStoreDb){
//        cellarStoreDb.setCreateTime(new Date());
//        cellarStoreDb.setState(Constants.STATE.zero.getKey());
//        cellarStoreDbService.save(cellarStoreDb);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    @RequiresPermissions("cellar:cellarstoredb:update")
//    public R update(@RequestBody CellarStoreDbEntity cellarStoreDb){
//			cellarStoreDbService.updateById(cellarStoreDb);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    @RequiresPermissions("cellar:cellarstoredb:delete")
//    public R delete(@RequestBody Long[] storeIds){
//			cellarStoreDbService.removeByIds(Arrays.asList(storeIds));
//
//        return R.ok();
//    }

}
