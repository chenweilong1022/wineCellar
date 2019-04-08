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
import io.renren.modules.app.form.SubmitOrdersByBargainingEntity;
import io.renren.modules.app.form.SubmitOrdersByGroupEntity;
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
 * 会员砍价信息表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-03 15:22:05
 */
@RestController
@RequestMapping("app/cellarmemberbargaininginformationdb")
@Api(value="APP会员砍价信息表",tags="APP会员砍价信息表")
public class AppCellarMemberBargainingInformationDbController {

    @Autowired
    private CellarMemberBargainingInformationDbService cellarMemberBargainingInformationDbService;
    @Autowired
    private CellarBargainingActivityDbService cellarBargainingActivityDbService;
    @Autowired
    private CellarGroupActivityDbService cellarGroupActivityDbService;
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
     * 信息
     */
    @GetMapping("/info")
    @ApiOperation(value = "APP会员砍价信息详情",notes = "APP会员砍价信息详情",response = CellarMemberBargainingInformationDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="bargainingActivityId",value="砍价活动id",dataType="String",required=false,paramType="query"),
    })
    public R info(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberBargainingInformationDbEntity cellarMemberBargainingInformationDb
    ){
        CellarMemberBargainingInformationDbEntity cellarMemberBargainingInformationDbEntity = cellarMemberBargainingInformationDbService.getOne(new QueryWrapper<CellarMemberBargainingInformationDbEntity>().lambda()
                .eq(CellarMemberBargainingInformationDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(CellarMemberBargainingInformationDbEntity::getMemberId, cellarMemberDbEntity.getMemberId())
                .eq(CellarMemberBargainingInformationDbEntity::getBargainingActivityId, cellarMemberBargainingInformationDb.getBargainingActivityId())
        );
        return R.data(cellarMemberBargainingInformationDbEntity);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "APP会员砍价信息保存,此接口用来发起砍价",notes = "APP会员砍价信息保存,此接口用来发起砍价",response = CellarMemberBargainingInformationDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="bargainingActivityId",value="砍价活动id",dataType="String",required=false,paramType="query"),
    })
    public R save(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberBargainingInformationDbEntity cellarMemberBargainingInformationDb
    ){

        /**
         * 校验
         */
        Assert.isNull(cellarMemberBargainingInformationDb,"砍价活动id不能为空");
        Long bargainingActivityId = cellarMemberBargainingInformationDb.getBargainingActivityId();
        Assert.isNull(cellarMemberBargainingInformationDb,"砍价活动id不能为空");
        CellarBargainingActivityDbEntity cellarBargainingActivityDbEntity = cellarBargainingActivityDbService.getById(bargainingActivityId);
        Assert.isNull(cellarBargainingActivityDbEntity,"砍价活动不存在");
        /**
         * 获取砍价活动商品信息
         */
        CellarCommodityDbEntity cellarCommodityDbEntity = cellarBargainingActivityDbEntity.getCellarCommodityDbEntity();
        /**
         * 会员参加砍价
         */
        cellarMemberBargainingInformationDb.setCommodityId(cellarCommodityDbEntity.getCommodityId());
        cellarMemberBargainingInformationDb.setBargainingInitialPrice(cellarBargainingActivityDbEntity.getBargainingInitialPrice());
        cellarMemberBargainingInformationDb.setBargainingLowestPrice(cellarBargainingActivityDbEntity.getBargainingLowestPrice());
        cellarMemberBargainingInformationDb.setBargainingParticipation(cellarBargainingActivityDbEntity.getBargainingParticipation());
        cellarMemberBargainingInformationDb.setCreateTime(new Date());
        cellarMemberBargainingInformationDb.setState(Constants.STATE.zero.getKey());
        cellarMemberBargainingInformationDb.setMemberId(cellarMemberDbEntity.getMemberId());
        cellarMemberBargainingInformationDb.setCuttingDownNumber(BigDecimal.ZERO);
        cellarMemberBargainingInformationDb.setFinalPrice(cellarBargainingActivityDbEntity.getBargainingInitialPrice());
        /**
         * 判断是否已经参与过了
         */
        int count = cellarMemberBargainingInformationDbService.count(new QueryWrapper<CellarMemberBargainingInformationDbEntity>().lambda()
                .eq(CellarMemberBargainingInformationDbEntity::getMemberId, cellarMemberDbEntity.getMemberId())
                .eq(CellarMemberBargainingInformationDbEntity::getBargainingActivityId, bargainingActivityId)
                .eq(CellarMemberBargainingInformationDbEntity::getState, Constants.STATE.zero.getKey())
        );
        if (count == Constants.Number.zero.getKey()) {
            cellarMemberBargainingInformationDbService.save(cellarMemberBargainingInformationDb);
        }
        return R.ok();
    }

    /**
     * 砍价商品生成订单
     */
    @PostMapping("/generateOrdersByBargaining")
    @ApiOperation(value = "砍价商品生成订单",notes = "砍价商品生成订单",response = CellarGroupActivityDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
    })
    public R generateOrdersByBargaining(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            SubmitOrdersByBargainingEntity submitOrdersByBargainingEntity
    ){
        /**
         * 酒窖系统配置
         */
        CellarConfigDbEntity cellarConfigDbEntity = cellarConfigDbService.getById(Constants.Number.one.getKey());
        Assert.isNull(submitOrdersByBargainingEntity,"提交订单为空");
        Assert.isNull(submitOrdersByBargainingEntity.getMethodPayment(),"支付方式不能为空");
        BigDecimal payOrderAmount = BigDecimal.ZERO;//订单支付金额
        String orderNo = IdGeneratorUtil.getOrderNo();//订单编号 用来记录一批订单
        /**
         * 查询店铺信息
         */
        CellarStoreDbEntity cellarStoreDbEntity = cellarStoreDbService.getById(submitOrdersByBargainingEntity.getStoreId());
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
        cellarOrderDbEntity.setAddressId(submitOrdersByBargainingEntity.getAddressId());//地址id
        cellarOrderDbEntity.setDeliveryTime(submitOrdersByBargainingEntity.getDeliveryTime());//送达时间
        cellarOrderDbEntity.setMethodPayment(submitOrdersByBargainingEntity.getMethodPayment());//支付方式
        cellarOrderDbEntity.setOrderNote(submitOrdersByBargainingEntity.getOrderNote());//订单备注
        cellarOrderDbEntity.setOrderNo(orderNo);//订单编号
        cellarOrderDbEntity.setIsHave(submitOrdersByBargainingEntity.getIsHave());
        cellarOrderDbEntity.setStoreId(submitOrdersByBargainingEntity.getStoreId());//店铺id
        cellarOrderDbEntity.setMemberId(cellarMemberDbEntity.getMemberId());//会员id
        cellarOrderDbEntity.setPickUpPhone(submitOrdersByBargainingEntity.getPickUpPhone());//自提人手机号
        cellarOrderDbEntity.setOrderType(submitOrdersByBargainingEntity.getPickupWay());//订单类型
        cellarOrderDbEntity.setOrderStatus(Constants.ORDERSTATUS.FUONE.getKey());//订单待支付
        /**
         * 砍价商品信息
         * 数量信息
         */
        BigDecimal[] numbers = submitOrdersByBargainingEntity.getNumber();
        Long[] memberBargainingInformationId = submitOrdersByBargainingEntity.getMemberBargainingInformationId();
        for (int i = 0; i < memberBargainingInformationId.length; i++) {

            CellarMemberBargainingInformationDbEntity cellarMemberBargainingInformationDbEntity = cellarMemberBargainingInformationDbService.getById(memberBargainingInformationId[i]);
            Assert.isNull(cellarMemberBargainingInformationDbEntity,"砍价活动不存在");
            /**
             * 设置商品砍价活动id
             */
            cellarOrderDbEntity.setBargainingActivityId(cellarMemberBargainingInformationDbEntity.getBargainingActivityId());
            cellarOrderDbEntity.setMemberBargainingInformationId(cellarMemberBargainingInformationDbEntity.getMemberBargainingInformationId());
            /**
             * 判断用户是否已经参与砍价活动了
             *
             */
            CellarOrderDbEntity cellarOrderDbServiceOne = cellarOrderDbService.getOne(
                    new QueryWrapper<CellarOrderDbEntity>().lambda()
                            .eq(CellarOrderDbEntity::getMemberId, cellarMemberDbEntity.getMemberId())
                            .eq(CellarOrderDbEntity::getBargainingActivityId, cellarMemberBargainingInformationDbEntity.getBargainingActivityId())
                            .eq(CellarOrderDbEntity::getOrderType, Constants.CARTTYPE.SIX.getKey())
                            .notIn(CellarOrderDbEntity::getOrderStatus, Constants.ORDERSTATUS.FUONE.getKey())
            );
            Assert.isTrue(ObjectUtil.isNotNull(cellarOrderDbServiceOne),"您已经参过砍价了");
            /**
             *查询商品信息
             */
            Long commodityId = cellarMemberBargainingInformationDbEntity.getCommodityId();
            CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(commodityId);
            /**
             * 订单明细表设置数据
             */
            CellarOrderDetailsDbEntity cellarOrderDetailsDbEntity = new CellarOrderDetailsDbEntity();
            cellarOrderDetailsDbEntity.setOrderId(orderId);
            cellarOrderDetailsDbEntity.setState(Constants.STATE.zero.getKey());//状态
            cellarOrderDetailsDbEntity.setCreatetime(new Date());//创建时间
            cellarOrderDetailsDbEntity.setStoreId(submitOrdersByBargainingEntity.getStoreId());//店铺id
            cellarOrderDetailsDbEntity.setCommodityId(cellarCommodityDbEntity.getCommodityId());//商品id
            cellarOrderDetailsDbEntity.setNumber(numbers[i]);//数量

            cellarOrderDetailsDbEntity.setMemberBargainingInformationId(cellarMemberBargainingInformationDbEntity.getMemberBargainingInformationId());
            cellarOrderDetailsDbEntity.setBargainingActivityId(cellarMemberBargainingInformationDbEntity.getBargainingActivityId());
            cellarOrderDetailsDbEntity.setAmountGoods(cellarMemberBargainingInformationDbEntity.getFinalPrice());//商品价格
            cellarOrderDetailsDbEntity.setMemberId(cellarMemberDbEntity.getMemberId());
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
        if (submitOrdersByBargainingEntity.getMethodPayment().equals(Constants.METHODPAYMENT.WECHAT.getKey())) {
            Map map = WechatPayUtil.appOrder("同城酒窖", orderNo, payOrderAmount, Constants.SETTLEMENTTYPE.SIX);
            return R.data(map);
            /**
             * 支付宝
             */
        }else if (submitOrdersByBargainingEntity.getMethodPayment().equals(Constants.METHODPAYMENT.ALIPAY.getKey())) {
            String order = AliUtil.appOrder("同城酒窖", orderNo, payOrderAmount, Constants.SETTLEMENTTYPE.SIX);
            return R.data(order);
        }else if (submitOrdersByBargainingEntity.getMethodPayment().equals(Constants.METHODPAYMENT.BALANCE.getKey())) {
            cellarMemberBalanceChangeRecordDbService.balancePay(cellarMemberDbEntity,payOrderAmount,orderNo,submitOrdersByBargainingEntity.getPayPassword());
        }else if (submitOrdersByBargainingEntity.getMethodPayment().equals(Constants.METHODPAYMENT.CARDBALANCE.getKey())) {
            cellarMemberCardBalanceChangeRecordDbService.cardBalancePay(cellarMemberDbEntity,payOrderAmount,orderNo,submitOrdersByBargainingEntity.getPayPassword());
        }
        return R.ok();
    }

}
