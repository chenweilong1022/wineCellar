package io.renren.modules.app.controller;

import com.egzosn.pay.common.bean.PayOutMessage;
import io.renren.common.constants.Constants;
import io.renren.common.utils.R;
import io.renren.common.utils.pay.AliUtil;
import io.renren.common.utils.pay.WechatPayUtil;
import io.renren.common.validator.Assert;
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

    /**
     * 手动调用支付
     */
    @RequestMapping("/paySuccessSd")
    public PayOutMessage paySuccessSd(
            String outtradeno
    ){
        cellarOrderDbService.paySuccess(outtradeno);
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
        Assert.isNull(settlementtype,"支付异常");
        Assert.isNull(methodpayment,"支付异常");
        String outtradeno = null;
        PayOutMessage payOutMessage = null;
        /**
         * 微信
         */
        if (methodpayment.equals(Constants.METHODPAYMENT.WECHAT.getKey())) {
            outtradeno = WechatPayUtil.payBack(request);
            payOutMessage = WechatPayUtil.paySuccess();
        /**
         * 支付宝
         */
        }else if (methodpayment.equals(Constants.METHODPAYMENT.ALIPAY.getKey())) {
            outtradeno = AliUtil.payBack(request);
            payOutMessage = AliUtil.paySuccess();
        }
        Assert.isNull(outtradeno,"订单号回调失败");
        /**
         * 购物车结算
         */
        if (settlementtype.equals(Constants.SETTLEMENTTYPE.ONE.getKey())) {
            cellarOrderDbService.paySuccess(outtradeno);
        /**
         * 直接购买
         */
        }else if (settlementtype.equals(Constants.SETTLEMENTTYPE.TWO.getKey())) {
            cellarOrderDbService.paySuccess(outtradeno);
        }
        return payOutMessage;
    }

}
