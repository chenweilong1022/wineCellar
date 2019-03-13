package io.renren.modules.app.controller;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.form.AppSubmitCellarCommodityEvaluationDbEntity;
import io.renren.modules.cellar.entity.CellarCommodityDbEntity;
import io.renren.modules.cellar.entity.CellarCommodityEvaluationDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.entity.CellarOrderDbEntity;
import io.renren.modules.cellar.service.CellarCommodityDbService;
import io.renren.modules.cellar.service.CellarCommodityEvaluationDbService;
import io.renren.modules.cellar.service.CellarOrderDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;


/**
 * APP商品评价表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-11 15:12:42
 */
@RestController
@RequestMapping("app/cellarcommodityevaluationdb")
@Api(value="APP商品评价表",tags="APP商品评价表")
public class AppCellarCommodityEvaluationDbController {
    @Autowired
    private CellarCommodityEvaluationDbService cellarCommodityEvaluationDbService;
    @Autowired
    private CellarCommodityDbService cellarCommodityDbService;
    @Autowired
    private CellarOrderDbService cellarOrderDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
//    @Login
    @ApiOperation(value = "商品评价",notes = "商品评价")
    @ApiImplicitParams({
            @ApiImplicitParam(name="commodityId",value="商品id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
    })
    public R list(
           @ApiIgnore CellarCommodityEvaluationDbEntity cellarCommodityEvaluationDb
    ){
        PageUtils page = cellarCommodityEvaluationDbService.queryPage(cellarCommodityEvaluationDb);

        return R.data(page);
    }
//
//
//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{evaluationId}")
//    @RequiresPermissions("cellar:cellarcommodityevaluationdb:info")
//    public R info(@PathVariable("evaluationId") Long evaluationId){
//			CellarCommodityEvaluationDbEntity cellarCommodityEvaluationDb = cellarCommodityEvaluationDbService.getById(evaluationId);
//
//        return R.ok().put("cellarCommodityEvaluationDb", cellarCommodityEvaluationDb);
//    }
//
    /**
     * 保存
     */
    @PostMapping("/save")
    @Login
    @ApiOperation(value = "商品评价",notes = "商品评价")
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
    })
    public R save(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            AppSubmitCellarCommodityEvaluationDbEntity appSubmitCellarCommodityEvaluationDbEntity
    ){
        Assert.isNull(appSubmitCellarCommodityEvaluationDbEntity.getOrderId(),"订单id不能为空");
        Assert.isNull(appSubmitCellarCommodityEvaluationDbEntity.getStoreId(),"店铺id不能为空");
        Assert.isNullArray(appSubmitCellarCommodityEvaluationDbEntity.getCommodityId(),"商品id不能为空");
        Assert.isNullArray(appSubmitCellarCommodityEvaluationDbEntity.getEvaluationImage(),"评价图片不能为空");
        Assert.isNullArray(appSubmitCellarCommodityEvaluationDbEntity.getEvaluationContent(),"评价内容不能为空");
        Assert.isNullArray(appSubmitCellarCommodityEvaluationDbEntity.getEvaluationStarNumber(),"评价星数不能为空");

        for (int i = 0; i < appSubmitCellarCommodityEvaluationDbEntity.getCommodityId().length; i++) {
            /**
             * 创建商品评价对象
             */
            CellarCommodityEvaluationDbEntity cellarCommodityEvaluationDbEntity = new CellarCommodityEvaluationDbEntity();
            Long commodityId = appSubmitCellarCommodityEvaluationDbEntity.getCommodityId()[i];//商品id
            String evaluationImage = appSubmitCellarCommodityEvaluationDbEntity.getEvaluationImage()[i];//评论图片
            String evaluationContent = appSubmitCellarCommodityEvaluationDbEntity.getEvaluationContent()[i];//评价内容
            Integer evaluationStarNumber  = appSubmitCellarCommodityEvaluationDbEntity.getEvaluationStarNumber()[i];//评价星数
            /**
             * 设置商品评价
             */
            cellarCommodityEvaluationDbEntity.setCommodityId(commodityId);
            cellarCommodityEvaluationDbEntity.setEvaluationImage(evaluationImage);
            cellarCommodityEvaluationDbEntity.setEvaluationContent(evaluationContent);
            cellarCommodityEvaluationDbEntity.setEvaluationStarNumber(evaluationStarNumber);
            cellarCommodityEvaluationDbEntity.setMemberId(cellarMemberDbEntity.getMemberId());
            cellarCommodityEvaluationDbEntity.setOrderId(appSubmitCellarCommodityEvaluationDbEntity.getOrderId());
            cellarCommodityEvaluationDbEntity.setStoreId(appSubmitCellarCommodityEvaluationDbEntity.getStoreId());
            cellarCommodityEvaluationDbEntity.setState(Constants.STATE.zero.getKey());
            cellarCommodityEvaluationDbEntity.setCreateTime(new Date());
            cellarCommodityEvaluationDbEntity.setNickname(cellarMemberDbEntity.getNickname());
            cellarCommodityEvaluationDbEntity.setHeadPortrait(cellarMemberDbEntity.getHeadPortrait());
            /**
             * 保存商品评价
             */
            cellarCommodityEvaluationDbService.save(cellarCommodityEvaluationDbEntity);
            /**
             * 修改评价平均数
             */
            cellarCommodityDbService.evaluationStarNumbers(commodityId,evaluationStarNumber);
        }

        /**
         * 修改状态
         */
        CellarOrderDbEntity c = new CellarOrderDbEntity();
        c.setOrderStatus(Constants.ORDERSTATUS.THREE.getKey());
        c.setOrderId(appSubmitCellarCommodityEvaluationDbEntity.getOrderId());
        cellarOrderDbService.updateById(c);

        return R.ok();
    }

//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    @RequiresPermissions("cellar:cellarcommodityevaluationdb:update")
//    public R update(@RequestBody CellarCommodityEvaluationDbEntity cellarCommodityEvaluationDb){
//			cellarCommodityEvaluationDbService.updateById(cellarCommodityEvaluationDb);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    @RequiresPermissions("cellar:cellarcommodityevaluationdb:delete")
//    public R delete(@RequestBody Long[] evaluationIds){
//			cellarCommodityEvaluationDbService.removeByIds(Arrays.asList(evaluationIds));
//
//        return R.ok();
//    }

    public static void main(String[] args) {
//        5 4 5 3 4  //平均数4.2
        int[] arr = {5,4,5,3,4,3};
        int num = 0;
        for (int i : arr) {
            num = num + i;
        }
        System.out.println(BigDecimal.valueOf(num).divide(BigDecimal.valueOf(arr.length)));

        System.out.println(BigDecimal.valueOf(4).divide(BigDecimal.valueOf(7.2)));
    }

}
