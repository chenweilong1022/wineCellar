package io.renren.common.sms.chuanglan.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sms")
public class SmsConfig {

    //字符编码
    private String charset;
    // 请登录zz.253.com 获取创蓝API账号(非登录账号,示例:N1234567)
    private String account;

    // 请登录zz.253.com 获取创蓝API密码(非登录密码)
    private String pswd;
    //短信发送的URL 请登录zz.253.com 获取完整的URL接口信息
    private String smsSingleRequestServerUrl;
    // 设置您要发送的内容：其中“【】”中括号为运营商签名符号，多签名内容前置添加提交
    private String msg;
    // 设置您要发送的内容：其中“【】”中括号为运营商签名符号，多签名内容前置添加提交
    private String msg1;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getSmsSingleRequestServerUrl() {
        return smsSingleRequestServerUrl;
    }

    public void setSmsSingleRequestServerUrl(String smsSingleRequestServerUrl) {
        this.smsSingleRequestServerUrl = smsSingleRequestServerUrl;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }
}
