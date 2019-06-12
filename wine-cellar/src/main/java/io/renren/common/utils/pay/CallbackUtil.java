package io.renren.common.utils.pay;

import com.egzosn.pay.common.bean.PayOutMessage;
import io.renren.common.constants.Constants;
import io.renren.common.validator.Assert;
import io.renren.modules.cellar.entity.CellarConfigDbEntity;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class CallbackUtil {

    public static void callback(
            Integer settlementtype,
            Integer methodpayment,
            HttpServletRequest request,
            StringJoiner outtradeno,
            PayOutMessage payOutMessage
    ) {
        Assert.isNull(settlementtype,"支付异常");
        Assert.isNull(methodpayment,"支付异常");
        /**
         * 微信
         */
        if (methodpayment.equals(Constants.METHODPAYMENT.WECHAT.getKey())) {
            outtradeno.add(WechatPayUtil.payBack(request));
            payOutMessage = WechatPayUtil.paySuccess();
            /**
             * 支付宝
             */
        }else if (methodpayment.equals(Constants.METHODPAYMENT.ALIPAY.getKey())) {
            outtradeno.add(AliUtil.payBack(request));
            payOutMessage = AliUtil.paySuccess();
        }
        Assert.isNull(outtradeno,"订单号回调失败");
    }
}
