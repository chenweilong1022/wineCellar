package io.renren.common.utils.pay;

import com.egzosn.pay.ali.api.AliPayConfigStorage;
import com.egzosn.pay.ali.api.AliPayService;
import com.egzosn.pay.ali.bean.AliTransactionType;
import com.egzosn.pay.common.bean.PayOrder;
import com.egzosn.pay.common.bean.PayOutMessage;
import com.egzosn.pay.common.http.HttpConfigStorage;
import com.egzosn.pay.common.http.UriVariables;
import com.sun.javafx.util.Logging;
import io.renren.common.constants.Constants;
import io.renren.common.utils.SpringContextUtils;
import io.renren.config.pay.AliConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class AliUtil {

    protected static Logger logger = LoggerFactory.getLogger(AliUtil.class);

    private static  AliConfig aliConfig = SpringContextUtils.getBean(AliConfig.class);

    private static final AliPayConfigStorage aliPayConfigStorage = new AliPayConfigStorage();
    private static final HttpConfigStorage httpConfigStorage = new HttpConfigStorage();
    private static final AliPayService service;
    private static PayOrder payOrder = new PayOrder();
    static {
        AliPayConfigStorage aliPayConfigStorage = new AliPayConfigStorage();
//        aliPayConfigStorage.setPid(AlipayConfig.appid);
        aliPayConfigStorage.setAppId(aliConfig.getAppId());
        aliPayConfigStorage.setKeyPublic(aliConfig.getKeyPublic());
        aliPayConfigStorage.setKeyPrivate(aliConfig.getKeyPrivate());
        aliPayConfigStorage.setNotifyUrl(aliConfig.getNotifyUrl());
        aliPayConfigStorage.setKeyPrivateCertPwd(aliConfig.getKeyPrivateCertPwd());
//        aliPayConfigStorage.setReturnUrl("同步回调地址");
        aliPayConfigStorage.setSignType("RSA2");
//        aliPayConfigStorage.setSeller("收款账号");
        aliPayConfigStorage.setInputCharset("utf-8");
        //是否为测试账号，沙箱环境
        aliPayConfigStorage.setTest(false);
        //最大连接数
        httpConfigStorage.setMaxTotal(20);
        //默认的每个路由的最大连接数
        httpConfigStorage.setDefaultMaxPerRoute(10);
        service = new AliPayService(aliPayConfigStorage,httpConfigStorage);
    }

    public static String appOrder(String body, String orderId, BigDecimal price, Constants.SETTLEMENTTYPE settlementtype) {
        PayOrder payOrder = new PayOrder();
        payOrder.setSubject(body);
        payOrder.setBody("");
        payOrder.setPrice(price);
        payOrder.setOutTradeNo(orderId);
        payOrder.setTransactionType(AliTransactionType.APP);

        AliPayConfigStorage payConfigStorage = service.getPayConfigStorage();
        payConfigStorage.setNotifyUrl(
                aliConfig.getNotifyUrl().replace("${settlementtype}",settlementtype.getKey().toString())
                        .replace("${methodpayment}", Constants.METHODPAYMENT.ALIPAY.getKey().toString())
        );
        String order = UriVariables.getMapToParameters(service.orderInfo(payOrder));
//        Map<String, Object> order = service.orderInfo(payOrder);
        logger.debug(order);
        return order;
    }

    public static String payBack(HttpServletRequest request) {
        //获取支付方返回的对应参数
        Map<String, Object> params = null;
        try {
            params = service.getParameter2Map(request.getParameterMap(), request.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == params){
            return null;
        }
        //校验
        if (service.verify(params)){
            //这里处理业务逻辑
            return  params.get("out_trade_no").toString();
        }else {
            return  params.get("out_trade_no").toString();
        }
//        return null;
    }

    public static PayOutMessage paySuccess() {
        return service.getPayOutMessage("success", "成功");
    }

    public static void main(String[] args) {
        paySuccess();
//        String order = appOrder("aa", UUID.randomUUID().toString().replace("-", ""), BigDecimal.valueOf(0.01));
//        System.out.println(order);
    }

}
