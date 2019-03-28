package io.renren.modules.app.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.egzosn.pay.common.bean.PayOutMessage;
import io.renren.common.annotation.MemberMessage;
import io.renren.common.constants.Constants;
import io.renren.common.utils.R;
import io.renren.common.utils.pay.AliUtil;
import io.renren.common.utils.pay.CallbackUtil;
import io.renren.common.utils.pay.WechatPayUtil;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.form.SubmitOrdersByCartEntity;
import io.renren.modules.app.form.SubmitOrdersStoreEntity;
import io.renren.modules.app.utils.IdGeneratorUtil;
import io.renren.modules.cellar.entity.*;
import io.renren.modules.cellar.service.*;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


/**
 * APP酒窖支付回调
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-01 13:16:42
 */
@RestController
@RequestMapping("app/cellarcallback")
@Api(value="APP酒窖支付回调",tags="APP酒窖支付回调")
public class AppCellarCallbackController extends AbstractController {
    @Autowired
    private CellarOrderDbService cellarOrderDbService;
    @Autowired
    private CellarMemberDbService cellarMemberDbService;

    /**
     * 手动调用支付
     */
    @RequestMapping("/paySuccessSd")
    public PayOutMessage paySuccessSd(
            String outtradeno
    ){
//        cellarOrderDbService.paySuccess(outtradeno);
//        /**
//         * 用户余额充值
//         */
//        cellarMemberDbService.rechargeBalanceSuccess(outtradeno.toString());
        cellarMemberDbService.rechargeCardBalanceSuccess(outtradeno.toString());
        return null;
    }

    /**
     * 支付回调统一类
     */
    @RequestMapping("/paySuccess{settlementtype}/{methodpayment}")
    public PayOutMessage paySuccess(
            @PathVariable("settlementtype") Integer settlementtype,
            @PathVariable("methodpayment") Integer methodpayment,
            HttpServletRequest request
    ){
        StringJoiner outtradeno = new StringJoiner("");
        PayOutMessage payOutMessage = WechatPayUtil.paySuccess();
        CallbackUtil.callback(settlementtype,methodpayment,request,outtradeno,payOutMessage);
        if (settlementtype.equals(Constants.SETTLEMENTTYPE.ONE.getKey())) {
            /**根据支付号查询订单列表
             *
             */
            List<CellarOrderDbEntity> cellarOrderDbEntities = cellarOrderDbService.list(new QueryWrapper<CellarOrderDbEntity>().lambda()
                    .eq(CellarOrderDbEntity::getOrderNo, outtradeno)
                    .eq(CellarOrderDbEntity::getOrderStatus,Constants.ORDERSTATUS.FUONE.getKey())
            );
            /**
             * 判断
             */
            if (ObjectUtil.isNull(cellarOrderDbEntities) && cellarOrderDbEntities.size() == 0) {
                return null;
            }
            /**
             * 购物车结算
             */
            cellarOrderDbService.paySuccess(outtradeno.toString());
        }else if (settlementtype.equals(Constants.SETTLEMENTTYPE.TWO.getKey())) {
            /**
             * 根据支付号查询订单列表
             */
            List<CellarOrderDbEntity> cellarOrderDbEntities = cellarOrderDbService.list(new QueryWrapper<CellarOrderDbEntity>().lambda()
                    .eq(CellarOrderDbEntity::getOrderNo, outtradeno)
                    .eq(CellarOrderDbEntity::getOrderStatus,Constants.ORDERSTATUS.FUONE.getKey())
            );
            /**
             * 判断
             */
            if (ObjectUtil.isNull(cellarOrderDbEntities) && cellarOrderDbEntities.size() == 0) {
                return null;
            }
            /**
             * 直接购买
             */
            cellarOrderDbService.paySuccess(outtradeno.toString());
        }else if (settlementtype.equals(Constants.SETTLEMENTTYPE.THREE.getKey())) {
            /**
             * 用户余额充值
             */
            cellarMemberDbService.rechargeBalanceSuccess(outtradeno.toString());
        }else if (settlementtype.equals(Constants.SETTLEMENTTYPE.FOUR.getKey())) {
            /**
             * 用户储值卡充值
             */
            cellarMemberDbService.rechargeCardBalanceSuccess(outtradeno.toString());
        }
        return payOutMessage;
    }

}
