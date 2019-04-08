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
import io.renren.modules.app.form.SubmitOrdersByKillEntity;
import io.renren.modules.app.utils.IdGeneratorUtil;
import io.renren.modules.cellar.entity.*;
import io.renren.modules.cellar.service.*;
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
import java.util.Map;


/**
 * APP秒杀活动表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-29 10:13:13
 */
@RestController
@RequestMapping("app/cellarkillactivitydb")
@Api(value="APP秒杀活动表",tags="APP秒杀活动表")
public class AppCellarKillActivityDbController {
    @Autowired
    private CellarKillActivityDbService cellarKillActivityDbService;
    @Autowired
    private CellarConfigDbService cellarConfigDbService;
    @Autowired
    private CellarStoreDbService cellarStoreDbService;
    @Autowired
    private CellarCommodityDbService cellarCommodityDbService;
    @Autowired
    private CellarOrderDetailsDbService cellarOrderDetailsDbService;
    @Autowired
    private CellarOrderDbService cellarOrderDbService;
    @Autowired
    private CellarMemberCouponDbService cellarMemberCouponDbService;
    @Autowired
    private CellarMemberBalanceChangeRecordDbService cellarMemberBalanceChangeRecordDbService;
    @Autowired
    private CellarMemberCardBalanceChangeRecordDbService cellarMemberCardBalanceChangeRecordDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "APP秒杀活动列表",notes = "APP秒杀活动列表",response = CellarKillActivityDbEntity.class)
//    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="key",value="搜索条件",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="page",value="当前页数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="limit",value="每页个数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore CellarKillActivityDbEntity cellarKillActivityDb
    ){
        Assert.isNull(cellarKillActivityDb.getStoreId(),"店铺id不能为空");
        PageUtils page = cellarKillActivityDbService.queryPage(cellarKillActivityDb);

        return R.data(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info")
    @ApiOperation(value = "APP秒杀活动详情",notes = "APP秒杀活动详情",response = CellarKillActivityDbEntity.class)
//    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="killActivityId",value="秒杀活动id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
    })
    public R info(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarKillActivityDbEntity cellarKillActivityDb
    ){

        /**
         * 校验前端数据
         */
        Assert.isNull(cellarKillActivityDb.getStoreId(),"店铺id不能为空");
        Assert.isNull(cellarKillActivityDb.getKillActivityId(),"商品id不能为空");

        CellarKillActivityDbEntity cellarKillActivityDbEntity = cellarKillActivityDbService.getOne(new QueryWrapper<CellarKillActivityDbEntity>().lambda()
                .eq(CellarKillActivityDbEntity::getStoreId, cellarKillActivityDb.getStoreId())
                .eq(CellarKillActivityDbEntity::getKillActivityId, cellarKillActivityDb.getKillActivityId())
                .eq(CellarKillActivityDbEntity::getState, Constants.STATE.zero.getKey())
        );

        return R.data(cellarKillActivityDbEntity);
    }

    /**
     * 信息
     */
    @PostMapping("/generateOrdersByKill")
    @ApiOperation(value = "秒杀商品生成订单",notes = "秒杀商品生成订单",response = CellarKillActivityDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
    })
    public R generateOrdersByKill(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            SubmitOrdersByKillEntity submitOrdersByKillEntity
    ){
        /**
         * 酒窖系统配置
         */
        CellarConfigDbEntity cellarConfigDbEntity = cellarConfigDbService.getById(Constants.Number.one.getKey());
        Assert.isNull(submitOrdersByKillEntity,"提交订单为空");
        Assert.isNull(submitOrdersByKillEntity.getMethodPayment(),"支付方式不能为空");
        BigDecimal payOrderAmount = BigDecimal.ZERO;//订单支付金额
        String orderNo = IdGeneratorUtil.getOrderNo();//订单编号 用来记录一批订单
        /**
         * 查询店铺信息
         */
        CellarStoreDbEntity cellarStoreDbEntity = cellarStoreDbService.getById(submitOrdersByKillEntity.getStoreId());
        Assert.isNull(cellarStoreDbEntity,"店铺id不能为空");

        Long orderId = IdGeneratorUtil.getOrderId();//订单id
        BigDecimal totalOrderAmount = BigDecimal.ZERO;//订单总金额
        BigDecimal totalOrderIntegral = BigDecimal.ZERO;//订单总积分
        BigDecimal actualOrderAmount = BigDecimal.ZERO;//订单实际金额
        /**
         * 设置订单数据
         */
        CellarOrderDbEntity cellarOrderDbEntity = new CellarOrderDbEntity();
        cellarOrderDbEntity.setOrderId(orderId);
        cellarOrderDbEntity.setState(Constants.STATE.zero.getKey());//状态
        cellarOrderDbEntity.setCreateTime(new Date());//创建时间
        cellarOrderDbEntity.setAddressId(submitOrdersByKillEntity.getAddressId());//地址id
        cellarOrderDbEntity.setDeliveryTime(submitOrdersByKillEntity.getDeliveryTime());//送达时间
        cellarOrderDbEntity.setMethodPayment(submitOrdersByKillEntity.getMethodPayment());//支付方式
        cellarOrderDbEntity.setOrderNote(submitOrdersByKillEntity.getOrderNote());//订单备注
        cellarOrderDbEntity.setOrderNo(orderNo);//订单编号
        cellarOrderDbEntity.setIsHave(submitOrdersByKillEntity.getIsHave());
        cellarOrderDbEntity.setStoreId(submitOrdersByKillEntity.getStoreId());//店铺id
        cellarOrderDbEntity.setMemberId(cellarMemberDbEntity.getMemberId());//会员id
        cellarOrderDbEntity.setPickUpPhone(submitOrdersByKillEntity.getPickUpPhone());//自提人手机号
        cellarOrderDbEntity.setOrderType(submitOrdersByKillEntity.getPickupWay());//订单类型
        cellarOrderDbEntity.setOrderStatus(Constants.ORDERSTATUS.FUONE.getKey());//订单待支付
        /**
         * 秒杀商品信息
         * 数量信息
         */
        Long[] killActivityId = submitOrdersByKillEntity.getKillActivityId();
        BigDecimal[] numbers = submitOrdersByKillEntity.getNumber();

        for (int i = 0; i < killActivityId.length; i++) {
            /**
             * 判断秒杀
             */
            CellarKillActivityDbEntity cellarKillActivityDbEntity = cellarKillActivityDbService.getById(killActivityId[i]);
            Assert.isNull(cellarKillActivityDbEntity,"秒杀活动不存在");
            /**
             * 订单设置秒杀活动
             */
            cellarOrderDbEntity.setKillActivityId(cellarKillActivityDbEntity.getKillActivityId());
            CellarOrderDbEntity cellarOrderDbServiceOne = cellarOrderDbService.getOne(new QueryWrapper<CellarOrderDbEntity>().lambda()
                    .eq(CellarOrderDbEntity::getMemberId, cellarMemberDbEntity.getMemberId())
                    .eq(CellarOrderDbEntity::getKillActivityId, cellarKillActivityDbEntity.getKillActivityId())
                    .eq(CellarOrderDbEntity::getOrderType, Constants.CARTTYPE.FIVE.getKey())
                    .notIn(CellarOrderDbEntity::getOrderStatus, Constants.ORDERSTATUS.FUONE.getKey())
            );
            Assert.isTrue(ObjectUtil.isNotNull(cellarOrderDbServiceOne),"您已经参过秒杀了");
            /**
             * 秒杀剩余数量
             */
            BigDecimal killNumber = cellarKillActivityDbEntity.getKillNumber();
            Assert.isTrue(killNumber.compareTo(BigDecimal.ZERO) == 0,"数量不足");
            /**
             * 秒杀时间判断
             */
            long killStartTime = cellarKillActivityDbEntity.getKillStartTime().getTime();
            Assert.isTrue(killStartTime > System.currentTimeMillis(),"秒杀还未开始");
            long killEndTime = cellarKillActivityDbEntity.getKillEndTime().getTime();
            Assert.isTrue(killEndTime < System.currentTimeMillis(),"秒杀已经结束");
            /**
             *查询商品信息
             */
            Long commodityId = cellarKillActivityDbEntity.getCommodityId();
            CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(commodityId);
            /**
             * 订单明细表设置数据
             */
            CellarOrderDetailsDbEntity cellarOrderDetailsDbEntity = new CellarOrderDetailsDbEntity();
            cellarOrderDetailsDbEntity.setOrderId(orderId);
            cellarOrderDetailsDbEntity.setState(Constants.STATE.zero.getKey());//状态
            cellarOrderDetailsDbEntity.setCreatetime(new Date());//创建时间
            cellarOrderDetailsDbEntity.setStoreId(submitOrdersByKillEntity.getStoreId());//店铺id
            cellarOrderDetailsDbEntity.setCommodityId(cellarCommodityDbEntity.getCommodityId());//商品id
            cellarOrderDetailsDbEntity.setNumber(numbers[i]);//数量
            cellarOrderDetailsDbEntity.setKillActivityId(cellarKillActivityDbEntity.getKillActivityId());
            cellarOrderDetailsDbEntity.setAmountGoods(cellarKillActivityDbEntity.getKillPrice());//商品价格
            cellarOrderDetailsDbEntity.setMemberId(cellarMemberDbEntity.getMemberId());
//            cellarOrderDetailsDbEntity.setAmountGoods(cellarCommodityDbEntity.getPresentPrice());//商品价格
            cellarOrderDetailsDbEntity.setTotalAmountGoods(cellarOrderDetailsDbEntity.getAmountGoods().multiply(cellarOrderDetailsDbEntity.getNumber()));//商品总价格
            cellarOrderDetailsDbEntity.setIntegral(cellarCommodityDbEntity.getIntegral());//积分
            cellarOrderDetailsDbEntity.setTotalIntegral(cellarOrderDetailsDbEntity.getIntegral().multiply(cellarOrderDetailsDbEntity.getNumber()));
            /**
             * 订单明细保存
             */
            cellarOrderDetailsDbService.save(cellarOrderDetailsDbEntity);
            totalOrderAmount = totalOrderAmount.add(cellarOrderDetailsDbEntity.getTotalAmountGoods());
            totalOrderIntegral = totalOrderIntegral.add(cellarOrderDetailsDbEntity.getTotalIntegral());
        }
        actualOrderAmount = totalOrderAmount;
//        /**
//         * 判断是否满足店铺满减优惠
//         */
//        if (totalOrderAmount.compareTo(cellarStoreDbEntity.getFull()) >= Constants.Number.zero.getKey()) {
//            actualOrderAmount = totalOrderAmount.subtract(cellarStoreDbEntity.getReductionOf());
//            cellarOrderDbEntity.setStoreFullAmount(cellarStoreDbEntity.getReductionOf());//订单满减金额
//        }
//        /**
//         * 查询会员优惠券
//         * 判断是否存在
//         */
//        Long memberCouponId = submitOrdersByKillEntity.getMemberCouponId();
//        CellarMemberCouponDbEntity cellarMemberCouponDbEntity = cellarMemberCouponDbService.getById(memberCouponId);
//        /**
//         * 优惠券存在
//         */
//        if (ObjectUtil.isNotNull(memberCouponId) && ObjectUtil.isNotNull(cellarMemberCouponDbEntity)) {
//            //获取优惠券信息
//            CellarCouponDbEntity cellarCouponDbEntity = cellarMemberCouponDbEntity.getCellarCouponDbEntity();
//            //判断优惠券存在
//            Assert.isNull(cellarCouponDbEntity,"该优惠券已经下架了");
//            /**
//             * 判断是否满足使用条件
//             */
//            if (totalOrderAmount.compareTo(cellarCouponDbEntity.getFull()) >= Constants.Number.zero.getKey()) {
//                actualOrderAmount = actualOrderAmount.subtract(cellarCouponDbEntity.getReductionOf());
//                cellarOrderDbEntity.setDiscountAmount(cellarCouponDbEntity.getReductionOf());
//                cellarOrderDbEntity.setMemberCouponId(memberCouponId);
//            }else {
//                return R.error("优惠券不满足使用条件");
//            }
//        }
//        /**
//         * 判断系统配置是否为空
//         * 不为空则计算运费
//         */
//        if (ObjectUtil.isNotNull(cellarConfigDbEntity)) {
//            /**
//             * 获取运费
//             */
//            BigDecimal distributionAmount = cellarConfigDbEntity.getFreight() == null ? BigDecimal.ZERO : cellarConfigDbEntity.getFreight();
//            actualOrderAmount = actualOrderAmount.add(distributionAmount);
//            cellarOrderDbEntity.setDistributionAmount(distributionAmount);
//        }

        cellarOrderDbEntity.setTotalOrderAmount(totalOrderAmount);//订单总金额
        cellarOrderDbEntity.setActualOrderAmount(actualOrderAmount);//订单实际金额
        cellarOrderDbEntity.setIntegral(totalOrderIntegral);//订单总积分
        payOrderAmount = payOrderAmount.add(actualOrderAmount);
        /**
         * 保存订单
         */
        cellarOrderDbService.save(cellarOrderDbEntity);
        payOrderAmount = BigDecimal.valueOf(0.01);
        /**
         * 微信
         */
        if (submitOrdersByKillEntity.getMethodPayment().equals(Constants.METHODPAYMENT.WECHAT.getKey())) {
            Map map = WechatPayUtil.appOrder("同城酒窖", orderNo, payOrderAmount, Constants.SETTLEMENTTYPE.FIVE);
            return R.data(map);
            /**
             * 支付宝
             */
        }else if (submitOrdersByKillEntity.getMethodPayment().equals(Constants.METHODPAYMENT.ALIPAY.getKey())) {
            String order = AliUtil.appOrder("同城酒窖", orderNo, payOrderAmount, Constants.SETTLEMENTTYPE.FIVE);
            return R.data(order);
        }else if (submitOrdersByKillEntity.getMethodPayment().equals(Constants.METHODPAYMENT.BALANCE.getKey())) {
            cellarMemberBalanceChangeRecordDbService.balancePay(cellarMemberDbEntity,payOrderAmount,orderNo,submitOrdersByKillEntity.getPayPassword());
        }else if (submitOrdersByKillEntity.getMethodPayment().equals(Constants.METHODPAYMENT.CARDBALANCE.getKey())) {
            cellarMemberCardBalanceChangeRecordDbService.cardBalancePay(cellarMemberDbEntity,payOrderAmount,orderNo,submitOrdersByKillEntity.getPayPassword());
        }
        return R.ok();
    }

}
