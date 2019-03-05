package io.renren.modules.app.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.pay.AliUtil;
import io.renren.common.utils.pay.WechatPayUtil;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.form.SubmitOrdersByCartEntity;
import io.renren.modules.app.form.SubmitOrdersStoreEntity;
import io.renren.modules.app.utils.IdGeneratorUtil;
import io.renren.modules.cellar.entity.*;
import io.renren.modules.cellar.service.CellarCartDbService;
import io.renren.modules.cellar.service.CellarOrderDbService;
import io.renren.modules.cellar.service.CellarOrderDetailsDbService;
import io.renren.modules.cellar.service.CellarStoreDbService;
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
import java.util.List;
import java.util.Map;


/**
 * APP酒窖订单表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-01 13:16:42
 */
@RestController
@RequestMapping("app/cellarorderdb")
@Api(value="APP酒窖订单表",tags="APP酒窖订单表")
public class AppCellarOrderDbController {
    @Autowired
    private CellarOrderDbService cellarOrderDbService;
    @Autowired
    private CellarOrderDetailsDbService cellarOrderDetailsDbService;
    @Autowired
    private CellarCartDbService cellarCartDbService;
    @Autowired
    private CellarStoreDbService cellarStoreDbService;

//    /**
//     * 列表
//     */
//    @RequestMapping("/list")
//    @RequiresPermissions("cellar:cellarorderdb:list")
//    public R list(CellarOrderDbEntity cellarOrderDb){
//        PageUtils page = cellarOrderDbService.queryPage(cellarOrderDb);
//
//        return R.ok().put("page", page);
//    }


    /**
     * 购物车生成订单
     */
    @PostMapping("/generateOrderByCart")
    @ApiOperation(value = "购物车生成订单",notes = "购物车生成订单",response = CellarMemberAddressDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
    })
    public R generateOrderByCart(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @RequestBody SubmitOrdersByCartEntity submitOrdersByCartEntity
    ){

        BigDecimal payOrderAmount = BigDecimal.ZERO;//订单支付金额
        String orderNo = IdGeneratorUtil.getOrderNo();//订单编号 用来记录一批订单
        List<SubmitOrdersStoreEntity> submitOrdersStoreEntities = submitOrdersByCartEntity.getSubmitOrdersStoreEntities();

        /**
         * 根据店铺拆分
         */
        for (SubmitOrdersStoreEntity submitOrdersStoreEntity : submitOrdersStoreEntities) {

            /**
             * 查询店铺信息
             */
            CellarStoreDbEntity cellarStoreDbEntity = cellarStoreDbService.getById(submitOrdersStoreEntity.getStoreId());

            Long orderId = IdGeneratorUtil.getOrderId();//订单id
            BigDecimal totalOrderAmount = BigDecimal.ZERO;//订单总金额
            BigDecimal actualOrderAmount = BigDecimal.ZERO;//订单实际金额
            /**
             * 设置订单数据
             */
            CellarOrderDbEntity cellarOrderDbEntity = new CellarOrderDbEntity();
            cellarOrderDbEntity.setOrderId(orderId);
            cellarOrderDbEntity.setState(Constants.STATE.zero.getKey());//状态
            cellarOrderDbEntity.setCreateTime(new Date());//创建时间
            cellarOrderDbEntity.setAddressId(submitOrdersByCartEntity.getAddressId());//地址id
            cellarOrderDbEntity.setDeliveryTime(submitOrdersByCartEntity.getDeliveryTime());//送达时间
            cellarOrderDbEntity.setMethodPayment(submitOrdersByCartEntity.getMethodPayment());//支付方式
            cellarOrderDbEntity.setOrderNote(submitOrdersStoreEntity.getOrderNote());//订单备注
            cellarOrderDbEntity.setOrderNo(orderNo);//订单编号
            cellarOrderDbEntity.setStoreId(submitOrdersStoreEntity.getStoreId());//店铺id
            cellarOrderDbEntity.setMemberId(cellarMemberDbEntity.getMemberId());//会员id
            cellarOrderDbEntity.setPickUpPhone(submitOrdersByCartEntity.getPickUpPhone());//自提人手机号
            cellarOrderDbEntity.setOrderType(submitOrdersByCartEntity.getPickupWay());//订单类型
            cellarOrderDbEntity.setOrderStatus(Constants.ORDERSTATUS.FUONE.getKey());//订单待支付
            List<Long> cartIds = submitOrdersStoreEntity.getCartIds();
            /**
             * 店铺下购物车生成订单
             */
            for (Long cartId : cartIds) {

                /**
                 * 查询购物车
                 */
                CellarCartDbEntity cellarCartDbEntity = cellarCartDbService.getById(cartId);

                /**
                 * 订单明细表设置数据
                 */
                CellarOrderDetailsDbEntity cellarOrderDetailsDbEntity = new CellarOrderDetailsDbEntity();
                cellarOrderDetailsDbEntity.setOrderId(orderId);
                cellarOrderDetailsDbEntity.setState(Constants.STATE.zero.getKey());//状态
                cellarOrderDetailsDbEntity.setCreatetime(new Date());//创建时间
                cellarOrderDetailsDbEntity.setStoreId(submitOrdersStoreEntity.getStoreId());//店铺id
                cellarOrderDetailsDbEntity.setCommodityId(cellarCartDbEntity.getCommodityId());//商品id
                cellarOrderDetailsDbEntity.setNumber(cellarCartDbEntity.getNumber());//数量
                cellarOrderDetailsDbEntity.setAmountGoods(cellarCartDbEntity.getPrices());//商品价格
                cellarOrderDetailsDbEntity.setTotalAmountGoods(cellarCartDbEntity.getPrices().multiply(cellarCartDbEntity.getNumber()));//商品总价格
                /**
                 * 订单明细保存
                 */
                cellarOrderDetailsDbService.save(cellarOrderDetailsDbEntity);
                totalOrderAmount = totalOrderAmount.add(cellarOrderDetailsDbEntity.getTotalAmountGoods());
            }

            actualOrderAmount = totalOrderAmount;
            /**
             * 判断是否满足店铺满减优惠
             */
            if (totalOrderAmount.compareTo(cellarStoreDbEntity.getFull()) > Constants.Number.zero.getKey()) {
                actualOrderAmount = totalOrderAmount.subtract(cellarStoreDbEntity.getReductionOf());
                cellarOrderDbEntity.setStoreFullAmount(cellarStoreDbEntity.getReductionOf());//订单满减金额
            }
            cellarOrderDbEntity.setTotalOrderAmount(totalOrderAmount);//订单总金额
            cellarOrderDbEntity.setActualOrderAmount(actualOrderAmount);//订单实际金额
            payOrderAmount = payOrderAmount.add(actualOrderAmount);
            /**
             * 保存订单
             */
            cellarOrderDbService.save(cellarOrderDbEntity);
        }

        /**
         * 微信
         */
        if (submitOrdersByCartEntity.getMethodPayment().equals(Constants.METHODPAYMENT.WECHAT.getKey())) {
            Map map = WechatPayUtil.appOrder("同城酒窖", orderNo, payOrderAmount, Constants.SETTLEMENTTYPE.ONE);
            return R.data(map);
        /**
         * 支付宝
         */
        }else if (submitOrdersByCartEntity.getMethodPayment().equals(Constants.METHODPAYMENT.ALIPAY.getKey())) {
            String order = AliUtil.appOrder("同城酒窖", orderNo, payOrderAmount, Constants.SETTLEMENTTYPE.ONE);
            return R.data(order);
        }
        return R.ok();
    }


//
//
//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{orderId}")
//    @RequiresPermissions("cellar:cellarorderdb:info")
//    public R info(@PathVariable("orderId") Long orderId){
//			CellarOrderDbEntity cellarOrderDb = cellarOrderDbService.getById(orderId);
//
//        return R.ok().put("cellarOrderDb", cellarOrderDb);
//    }
//
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    @RequiresPermissions("cellar:cellarorderdb:save")
//    public R save(@RequestBody CellarOrderDbEntity cellarOrderDb){
//			cellarOrderDbService.save(cellarOrderDb);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    @RequiresPermissions("cellar:cellarorderdb:update")
//    public R update(@RequestBody CellarOrderDbEntity cellarOrderDb){
//			cellarOrderDbService.updateById(cellarOrderDb);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    @RequiresPermissions("cellar:cellarorderdb:delete")
//    public R delete(@RequestBody Long[] orderIds){
//			cellarOrderDbService.removeByIds(Arrays.asList(orderIds));
//
//        return R.ok();
//    }

}
