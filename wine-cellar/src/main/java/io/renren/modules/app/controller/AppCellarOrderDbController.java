package io.renren.modules.app.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.pay.AliUtil;
import io.renren.common.utils.pay.WechatPayUtil;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.form.SubmitOrdersByCartEntity;
import io.renren.modules.app.form.SubmitOrdersByDirectlyEntity;
import io.renren.modules.app.form.SubmitOrdersStoreEntity;
import io.renren.modules.app.utils.IdGeneratorUtil;
import io.renren.modules.cellar.entity.*;
import io.renren.modules.cellar.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
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
    @Autowired
    private CellarCommodityDbService cellarCommodityDbService;
    @Autowired
    private CellarMemberCouponDbService cellarMemberCouponDbService;
    @Autowired
    private CellarConfigDbService cellarConfigDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "订单列表",notes = "订单列表",response = CellarOrderDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="orderId",value="订单id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="state",value="状态",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="methodPayment",value="支付方式",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="orderStatus",value="订单状态",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="orderType",value="订单类型",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="orderNo",value="订单编号",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="key",value="搜索条件",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="page",value="当前页数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="limit",value="每页个数",dataType="String",required=false,paramType="query"),

    })
    public R list(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarOrderDbEntity cellarOrderDb
    ){
        /**
         * 设置用户id
         */
        cellarOrderDb.setMemberId(cellarMemberDbEntity.getMemberId());
        PageUtils page = cellarOrderDbService.queryPage(cellarOrderDb);

        return R.data(page);
    }


    /**
     * 订单详情
     */
    @GetMapping("/info")
    @ApiOperation(value = "订单详情",notes = "订单详情",response = CellarOrderDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="orderId",value="订单id",dataType="String",required=false,paramType="query"),
    })
    public R info(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarOrderDbEntity cellarOrderDb
    ){
        CellarOrderDbEntity cellarOrderDbEntity = cellarOrderDbService.getOne(new QueryWrapper<CellarOrderDbEntity>().lambda()
                .eq(CellarOrderDbEntity::getOrderId, cellarOrderDb.getOrderId())
                .eq(CellarOrderDbEntity::getMemberId, cellarOrderDb.getMemberId())
        );
        return R.data(cellarOrderDbEntity);
    }


    /**
     * 确认收货
     */
    @PostMapping("/receipt")
    @ApiOperation(value = "确认收货",notes = "确认收货",response = CellarOrderDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="orderIds",value="订单id",dataType="String",required=false,paramType="query"),
    })
    public R receipt(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore Long[] orderIds
    ){
        /**
         * 修改发货状态
         */
        CellarOrderDbEntity cellarOrderDbEntity = new CellarOrderDbEntity();
        cellarOrderDbEntity.setOrderStatus(Constants.ORDERSTATUS.TWO.getKey());
        cellarOrderDbService.update(cellarOrderDbEntity,new QueryWrapper<CellarOrderDbEntity>().lambda()
                .in(CellarOrderDbEntity::getOrderId,orderIds)
                .eq(CellarOrderDbEntity::getMemberId,cellarMemberDbEntity.getMemberId())
        );
        return R.ok();
    }

    /**
     * 申请售后
     */
    @PostMapping("/applyAfterSales")
    @ApiOperation(value = "申请售后",notes = "申请售后",response = CellarOrderDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="orderId",value="订单id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="returnReason",value="退货原因",dataType="String",required=false,paramType="query"),
    })
    public R applyAfterSales(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarOrderDbEntity cellarOrderDbEntity
    ){

        cellarOrderDbEntity.setOrderStatus(Constants.ORDERSTATUS.FUTWO.getKey());
        cellarOrderDbService.update(cellarOrderDbEntity,new QueryWrapper<CellarOrderDbEntity>().lambda()
                .in(CellarOrderDbEntity::getOrderId,cellarOrderDbEntity.getOrderId())
                .eq(CellarOrderDbEntity::getMemberId,cellarMemberDbEntity.getMemberId())
        );
        return R.ok();
    }

    /**
     * 商品直接购买
     */
    @PostMapping("/generateOrderByDirectly")
    @ApiOperation(value = "商品直接购买",notes = "商品直接购买")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
    })
    public R generateOrderByDirectly(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            SubmitOrdersByDirectlyEntity submitOrdersByDirectlyEntity
    ){

        /**
         * 酒窖系统配置
         */
        CellarConfigDbEntity cellarConfigDbEntity = cellarConfigDbService.getById(Constants.Number.one.getKey());
        Assert.isNull(submitOrdersByDirectlyEntity,"提交订单为空");
        Assert.isNull(submitOrdersByDirectlyEntity.getMethodPayment(),"支付方式不能为空");
        BigDecimal payOrderAmount = BigDecimal.ZERO;//订单支付金额
        String orderNo = IdGeneratorUtil.getOrderNo();//订单编号 用来记录一批订单

        /**
         * 查询店铺信息
         */
        CellarStoreDbEntity cellarStoreDbEntity = cellarStoreDbService.getById(submitOrdersByDirectlyEntity.getStoreId());
        Assert.isNull(cellarStoreDbEntity,"店铺id不能为空");

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
        cellarOrderDbEntity.setAddressId(submitOrdersByDirectlyEntity.getAddressId());//地址id
        cellarOrderDbEntity.setDeliveryTime(submitOrdersByDirectlyEntity.getDeliveryTime());//送达时间
        cellarOrderDbEntity.setMethodPayment(submitOrdersByDirectlyEntity.getMethodPayment());//支付方式
        cellarOrderDbEntity.setOrderNote(submitOrdersByDirectlyEntity.getOrderNote());//订单备注
        cellarOrderDbEntity.setOrderNo(orderNo);//订单编号
        cellarOrderDbEntity.setStoreId(submitOrdersByDirectlyEntity.getStoreId());//店铺id
        cellarOrderDbEntity.setMemberId(cellarMemberDbEntity.getMemberId());//会员id
        cellarOrderDbEntity.setPickUpPhone(submitOrdersByDirectlyEntity.getPickUpPhone());//自提人手机号
        cellarOrderDbEntity.setOrderType(submitOrdersByDirectlyEntity.getPickupWay());//订单类型
        cellarOrderDbEntity.setOrderStatus(Constants.ORDERSTATUS.FUONE.getKey());//订单待支付


        Long[] commodityIds = submitOrdersByDirectlyEntity.getCommodityId();
        BigDecimal[] numbers = submitOrdersByDirectlyEntity.getNumber();

        for (int i = 0; i < commodityIds.length; i++) {

            CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(commodityIds[i]);

            /**
             * 订单明细表设置数据
             */
            CellarOrderDetailsDbEntity cellarOrderDetailsDbEntity = new CellarOrderDetailsDbEntity();
            cellarOrderDetailsDbEntity.setOrderId(orderId);
            cellarOrderDetailsDbEntity.setState(Constants.STATE.zero.getKey());//状态
            cellarOrderDetailsDbEntity.setCreatetime(new Date());//创建时间
            cellarOrderDetailsDbEntity.setStoreId(submitOrdersByDirectlyEntity.getStoreId());//店铺id
            cellarOrderDetailsDbEntity.setCommodityId(cellarCommodityDbEntity.getCommodityId());//商品id
            cellarOrderDetailsDbEntity.setNumber(numbers[i]);//数量
            cellarOrderDetailsDbEntity.setAmountGoods(cellarCommodityDbEntity.getPresentPrice());//商品价格
            cellarOrderDetailsDbEntity.setTotalAmountGoods(cellarOrderDetailsDbEntity.getAmountGoods().multiply(cellarOrderDetailsDbEntity.getNumber()));//商品总价格
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
        if (totalOrderAmount.compareTo(cellarStoreDbEntity.getFull()) >= Constants.Number.zero.getKey()) {
            actualOrderAmount = totalOrderAmount.subtract(cellarStoreDbEntity.getReductionOf());
            cellarOrderDbEntity.setStoreFullAmount(cellarStoreDbEntity.getReductionOf());//订单满减金额
        }

        /**
         * 查询会员优惠券
         * 判断是否存在
         */
        Long memberCouponId = submitOrdersByDirectlyEntity.getMemberCouponId();
        CellarMemberCouponDbEntity cellarMemberCouponDbEntity = cellarMemberCouponDbService.getById(memberCouponId);
        /**
         * 优惠券存在
         */
        if (ObjectUtil.isNotNull(memberCouponId) && ObjectUtil.isNotNull(cellarMemberCouponDbEntity)) {
            //获取优惠券信息
            CellarCouponDbEntity cellarCouponDbEntity = cellarMemberCouponDbEntity.getCellarCouponDbEntity();
            //判断优惠券存在
            Assert.isNull(cellarCouponDbEntity,"该优惠券已经下架了");
            /**
             * 判断是否满足使用条件
             */
            if (totalOrderAmount.compareTo(cellarCouponDbEntity.getFull()) >= Constants.Number.zero.getKey()) {
                actualOrderAmount = actualOrderAmount.subtract(cellarCouponDbEntity.getReductionOf());
                cellarOrderDbEntity.setDiscountAmount(cellarCouponDbEntity.getReductionOf());
                cellarOrderDbEntity.setMemberCouponId(memberCouponId);
            }else {
                return R.error("优惠券不满足使用条件");
            }
        }

        /**
         * 判断系统配置是否为空
         * 不为空则计算运费
         */
        if (ObjectUtil.isNotNull(cellarConfigDbEntity)) {
            /**
             * 获取运费
             */
            BigDecimal distributionAmount = cellarConfigDbEntity.getFreight() == null ? BigDecimal.ZERO : cellarConfigDbEntity.getFreight();
            actualOrderAmount = actualOrderAmount.add(distributionAmount);
            cellarOrderDbEntity.setDistributionAmount(distributionAmount);
        }

        cellarOrderDbEntity.setTotalOrderAmount(totalOrderAmount);//订单总金额
        cellarOrderDbEntity.setActualOrderAmount(actualOrderAmount);//订单实际金额
        payOrderAmount = payOrderAmount.add(actualOrderAmount);
        /**
         * 保存订单
         */
        cellarOrderDbService.save(cellarOrderDbEntity);
        payOrderAmount = BigDecimal.valueOf(0.01);
        /**
         * 微信
         */
        if (submitOrdersByDirectlyEntity.getMethodPayment().equals(Constants.METHODPAYMENT.WECHAT.getKey())) {
            Map map = WechatPayUtil.appOrder("同城酒窖", orderNo, payOrderAmount, Constants.SETTLEMENTTYPE.TWO);
            return R.data(map);
            /**
             * 支付宝
             */
        }else if (submitOrdersByDirectlyEntity.getMethodPayment().equals(Constants.METHODPAYMENT.ALIPAY.getKey())) {
            String order = AliUtil.appOrder("同城酒窖", orderNo, payOrderAmount, Constants.SETTLEMENTTYPE.TWO);
            return R.data(order);
        }

        return R.ok();
    }


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

        Assert.isNull(submitOrdersByCartEntity,"提交订单为空");
        Assert.isNull(submitOrdersByCartEntity.getMethodPayment(),"支付方式不能为空");

        /**
         * 酒窖系统配置
         */
        CellarConfigDbEntity cellarConfigDbEntity = cellarConfigDbService.getById(Constants.Number.one.getKey());
        BigDecimal payOrderAmount = BigDecimal.ZERO;//订单支付金额
        String orderNo = IdGeneratorUtil.getOrderNo();//订单编号 用来记录一批订单
        List<SubmitOrdersStoreEntity> submitOrdersStoreEntities = submitOrdersByCartEntity.getSubmitOrdersStoreEntities();
        Assert.isNull(submitOrdersStoreEntities,"购物车id不能为空");
        /**
         * 根据店铺拆分
         */
        for (SubmitOrdersStoreEntity submitOrdersStoreEntity : submitOrdersStoreEntities) {

            /**
             * 查询店铺信息
             */
            CellarStoreDbEntity cellarStoreDbEntity = cellarStoreDbService.getById(submitOrdersStoreEntity.getStoreId());
            Assert.isNull(cellarStoreDbEntity,"店铺id不能为空");
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
                Assert.isNull(cellarCartDbEntity,"购物车id不存在");

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
            if (totalOrderAmount.compareTo(cellarStoreDbEntity.getFull()) >= Constants.Number.zero.getKey()) {
                actualOrderAmount = totalOrderAmount.subtract(cellarStoreDbEntity.getReductionOf());
                cellarOrderDbEntity.setStoreFullAmount(cellarStoreDbEntity.getReductionOf());//订单满减金额
            }

            /**
             * 查询会员优惠券
             * 判断是否存在
             */
            Long memberCouponId = submitOrdersStoreEntity.getMemberCouponId();
            CellarMemberCouponDbEntity cellarMemberCouponDbEntity = cellarMemberCouponDbService.getById(memberCouponId);
            /**
             * 优惠券存在
             */
            if (ObjectUtil.isNotNull(memberCouponId) && ObjectUtil.isNotNull(cellarMemberCouponDbEntity)) {
                //获取优惠券信息
                CellarCouponDbEntity cellarCouponDbEntity = cellarMemberCouponDbEntity.getCellarCouponDbEntity();
                //判断优惠券存在
                Assert.isNull(cellarCouponDbEntity,"该优惠已经下架了");
                /**
                 * 判断是否满足使用条件
                 */
                if (totalOrderAmount.compareTo(cellarCouponDbEntity.getFull()) >= Constants.Number.zero.getKey()) {
                    actualOrderAmount = actualOrderAmount.subtract(cellarCouponDbEntity.getReductionOf());
                    cellarOrderDbEntity.setDiscountAmount(cellarCouponDbEntity.getReductionOf());
                    cellarOrderDbEntity.setMemberCouponId(memberCouponId);
                }else {
                    return R.error("优惠券不满足使用条件");
                }
            }


            /**
             * 判断系统配置是否为空
             * 不为空则计算运费
             */
            if (ObjectUtil.isNotNull(cellarConfigDbEntity)) {
                /**
                 * 获取运费
                 */
                BigDecimal distributionAmount = cellarConfigDbEntity.getFreight() == null ? BigDecimal.ZERO : cellarConfigDbEntity.getFreight();
                actualOrderAmount = actualOrderAmount.add(distributionAmount);
                cellarOrderDbEntity.setDistributionAmount(distributionAmount);
            }

            cellarOrderDbEntity.setTotalOrderAmount(totalOrderAmount);//订单总金额
            cellarOrderDbEntity.setActualOrderAmount(actualOrderAmount);//订单实际金额
            payOrderAmount = payOrderAmount.add(actualOrderAmount);
            /**
             * 保存订单
             */
            cellarOrderDbService.save(cellarOrderDbEntity);
        }

        payOrderAmount = BigDecimal.valueOf(0.01);
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

}
