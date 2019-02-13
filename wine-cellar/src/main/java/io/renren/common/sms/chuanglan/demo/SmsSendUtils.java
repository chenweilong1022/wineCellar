package io.renren.common.sms.chuanglan.demo;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSON;
import io.renren.common.sms.chuanglan.config.SmsConfig;
import io.renren.common.sms.chuanglan.sms.request.SmsSendRequest;
import io.renren.common.sms.chuanglan.sms.response.SmsSendResponse;
import io.renren.common.sms.chuanglan.sms.util.ChuangLanSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author tianyh
 * @Description:普通短信发送
 */
@Component
public class SmsSendUtils {

    @Autowired
    private SmsConfig smsConfig;

    /**
     * 发送短信
     * @param phone
     * @param code
     */
    public void send(String phone,String code) {
        String codeMsg = smsConfig.getMsg().replace("${code}", code);
        //状态报告
        String report= "true";

        SmsSendRequest smsSingleRequest = new SmsSendRequest(smsConfig.getAccount(), smsConfig.getPswd(), codeMsg, phone,report);

        String requestJson = JSON.toJSONString(smsSingleRequest);

        System.out.println("before request string is: " + requestJson);

        String response = ChuangLanSmsUtil.sendSmsByPost(smsConfig.getSmsSingleRequestServerUrl(), requestJson);

        System.out.println("response after request result is :" + response);

        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);

        System.out.println("response  toString is :" + smsSingleResponse);


    }

}
