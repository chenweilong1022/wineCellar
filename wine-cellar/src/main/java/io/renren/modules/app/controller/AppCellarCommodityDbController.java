package io.renren.modules.app.controller;

import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.cellar.entity.CellarCommodityDbEntity;
import io.renren.modules.cellar.entity.CellarMemberAddressDbEntity;
import io.renren.modules.cellar.service.CellarCommodityDbService;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;


/**
 * APP酒窖商品表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
@RestController
@RequestMapping("app/cellarcommoditydb")
public class AppCellarCommodityDbController extends AbstractController {
    @Autowired
    private CellarCommodityDbService cellarCommodityDbService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @ApiOperation(value = "店铺商品列表",notes = "店铺商品列表",response = CellarCommodityDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="categoryId",value="分类id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="key",value="搜索条件",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="page",value="当前页数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="limit",value="每页个数",dataType="String",required=false,paramType="query"),
    })
    public R list(
            CellarCommodityDbEntity cellarCommodityDb
    ){
        Assert.isNull(cellarCommodityDb.getStoreId(),"店铺id不能为空");
        PageUtils page = cellarCommodityDbService.queryPageApp(cellarCommodityDb);

        return R.data(page);
    }
//
//
//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{commodityId}")
//    @RequiresPermissions("cellar:cellarcommoditydb:info")
//    public R info(@PathVariable("commodityId") Long commodityId){
//			CellarCommodityDbEntity cellarCommodityDb = cellarCommodityDbService.getById(commodityId);
//
//        return R.ok().put("cellarCommodityDb", cellarCommodityDb);
//    }
//
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    @RequiresPermissions("cellar:cellarcommoditydb:save")
//    public R save(@RequestBody CellarCommodityDbEntity cellarCommodityDb){
//        cellarCommodityDb.setState(Constants.STATE.zero.getKey());
//        cellarCommodityDb.setCreationTime(new Date());
//        cellarCommodityDb.setMonthSales(BigDecimal.ZERO);
//        cellarCommodityDb.setTotalSales(BigDecimal.ZERO);
//        cellarCommodityDb.setHighPraise(BigDecimal.ZERO);
//        cellarCommodityDb.setStoreId(getUser().getStoreId());
//
//        cellarCommodityDbService.save(cellarCommodityDb);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    @RequiresPermissions("cellar:cellarcommoditydb:update")
//    public R update(@RequestBody CellarCommodityDbEntity cellarCommodityDb){
//			cellarCommodityDbService.updateById(cellarCommodityDb);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    @RequiresPermissions("cellar:cellarcommoditydb:delete")
//    public R delete(@RequestBody Long[] commodityIds){
//			cellarCommodityDbService.removeByIds(Arrays.asList(commodityIds));
//
//        return R.ok();
//    }

}
