package io.renren.common.constants;

import java.util.List;

public class Constants {

    /**
     * 数字状态枚举
     */
    public enum Number {
        fuone(-1,""),
        zero(0,""),
        one(1,""),
        two(2,""),
        three(3,""),
        four(4,""),
        five(5,""),
        six(6,""),
        seven(7,""),
        eight(8,""),
        nine(9,""),
        ten(10,""),
        eleven(11,""),
        twelve(12,""),
        thirteen(13,""),
        fourteen(14,""),
        fifteen(15,""),
        sixteen(16,""),
        seventeen(17,""),
        eighteen(18,""),
        nineteen(19,""),
        twenty(20,"");

        private Integer key;
        private String value;

        private Number(Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public static Number[] List() {
            return Number.values();
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * 全局状态描述
     */
    public enum STATE {
        funine(-9,"删除"),
        fuone(-1,"禁用"),
        zero(0,"正常");

        private Integer key;
        private String value;

        private STATE(Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static String getValueByKey(Integer key) {
            for (STATE value : STATE.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

    /**
     * 全局级别描述新注册会员为1级
     */
    public enum LEVEL {

        one(1,"1级别");

        private Integer key;
        private String value;

        private LEVEL(Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * 全局级别性别
     */
    public enum GENDER {

        male(1,"男"),
        female(2,"女");

        private Integer key;
        private String value;

        private GENDER(Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static String getValueByKey(Integer key) {
            for (GENDER value : GENDER.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

    /**
     * 全局级别是否支持自提
     */
    public enum SUPPORTTHEIROWN {

        male(0,"是"),
        female(1,"否");

        private Integer key;
        private String value;

        private SUPPORTTHEIROWN(Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static String getValueByKey(Integer key) {
            for (SUPPORTTHEIROWN value : SUPPORTTHEIROWN.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

    /**
     * 全局级别是否默认
     */
    public enum ISDEFAULT {

        NO(0,"非默认"),
        YES(1,"默认");

        private Integer key;
        private String value;

        private ISDEFAULT(Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static String getValueByKey(Integer key) {
            for (ISDEFAULT value : ISDEFAULT.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

    /**
     * 全局级别 商品类别
     */
    public enum CARTTYPE {

        ZERO(0,"普通"),
        ONE(1,"自提"),
        TWO(2,"预约"),
        THREE(3,"预售");

        private Integer key;
        private String value;

        private CARTTYPE(Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static String getValueByKey(Integer key) {
            for (CARTTYPE value : CARTTYPE.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

    /**
     * 全局级别支付方式
     */
    public enum METHODPAYMENT {

        WECHAT(0,"微信"),
        ALIPAY(1,"支付宝");

        private Integer key;
        private String value;

        private METHODPAYMENT(Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static String getValueByKey(Integer key) {
            for (METHODPAYMENT value : METHODPAYMENT.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

    /**
     * 全局级别订单状态
     */
    public enum ORDERSTATUS {

        FUFOUR(-4,"售后不通过"),//售后不通过
        FUTHREE(-3,"售后通过"),//售后通过
        FUTWO(-2,"订单进入售后"),//订单进入售后
        FUONE(-1,"待支付"),//订单刚生成用户还没有支付
        ZERO(0,"已支付"),//订单已经支付,等待发货
        ONE(1,"已发货"),//订单已经发货等待用户签收
        TWO(2,"已完成"),//用户签收订单完成
        THREE(3,"已评价");//已评价



        private Integer key;
        private String value;

        private ORDERSTATUS(Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static String getValueByKey(Integer key) {
            for (ORDERSTATUS value : ORDERSTATUS.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

    /**
     * 全局级别 结算类型
     */
    public enum SETTLEMENTTYPE {


        ONE(1,"购物车结算"),//订单已经发货等待用户签收
        TWO(2,"直接购买结算"),//用户签收订单完成
        THREE(3,"已评价");



        private Integer key;
        private String value;

        private SETTLEMENTTYPE(Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static String getValueByKey(Integer key) {
            for (SETTLEMENTTYPE value : SETTLEMENTTYPE.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

    /**
     * 全局级别 优惠券类型
     */
    public enum COUPONTYPE {


        ONE(1,"新人优惠券"),
        TWO(2,"满减优惠券");



        private Integer key;
        private String value;

        private COUPONTYPE(Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static String getValueByKey(Integer key) {
            for (COUPONTYPE value : COUPONTYPE.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

    /**
     * 全局级别 优惠券使用状态
     */
    public enum USINGSTATE {


        ONE(0,"未使用"),
        TWO(1,"已使用"),
        THREE(2,"已过期");



        private Integer key;
        private String value;

        private USINGSTATE(Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static String getValueByKey(Integer key) {
            for (USINGSTATE value : USINGSTATE.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }
    /**
     * 全局级别 优惠券领取状态
     */
    public enum RECEIVESTATE {


        ONE(1,"未领取"),
        TWO(2,"已领取");



        private Integer key;
        private String value;

        private RECEIVESTATE(Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static String getValueByKey(Integer key) {
            for (RECEIVESTATE value : RECEIVESTATE.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

    /**
     * 轮播图类型 点击跳转的路径
     */
    public enum SHUFFLINGTYPE {

        ONE(1,"h5"),
        TWO(2,"店铺"),
        three(3,"商品");


        private Integer key;
        private String value;

        private SHUFFLINGTYPE(Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static String getValueByKey(Integer key) {
            for (SHUFFLINGTYPE value : SHUFFLINGTYPE.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

}
