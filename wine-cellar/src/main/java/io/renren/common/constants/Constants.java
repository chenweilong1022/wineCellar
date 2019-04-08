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
        THREE(3,"预售"),
        FOUR(4,"拼团"),
        FIVE(5,"秒杀"),
        SIX(6,"砍价");
//        seven(7,"");

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
        ALIPAY(1,"支付宝"),
        BALANCE(2,"余额"),
        CARDBALANCE(3,"储值卡余额");
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
        FUNINE(-9,"已退款"),//售后不通过
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


        ONE(1,"购物车结算"),
        TWO(2,"直接购买结算"),
        THREE(3,"余额充值"),
        FOUR(4,"储值卡充值"),
        FIVE(5,"秒杀结算"),
        SIX(6,"拼团结算");



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

    /**
     * 会员消息类型
     */
    public enum MESSAGETYPE {

        SYSTEM(1,"系统消息"),
        ORDER(2,"订单消息");


        private Integer key;
        private String value;

        private MESSAGETYPE(Integer key,String value) {
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
            for (MESSAGETYPE value : MESSAGETYPE.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

    /**
     * 是否已读
     */
    public enum HAVEREAD {

        UNREAD(1,"未读"),
        READ(2,"已读");


        private Integer key;
        private String value;

        private HAVEREAD(Integer key,String value) {
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
            for (HAVEREAD value : HAVEREAD.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

    /**
     * 是否精选
     */
    public enum HAVEHANDPICK {

        NO(1,"否"),
        YES(2,"是"),
        REVIEW(3,"审核中");


        private Integer key;
        private String value;

        private HAVEHANDPICK(Integer key,String value) {
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
            for (HAVEHANDPICK value : HAVEHANDPICK.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

    /**
     * 审核状态
     */
    public enum REVIEWSTATUS {

        fuone(-1,"审核不通过"),
        zero(0,"待审核"),
        one(1,"审核通过");

        private Integer key;
        private String value;

        private REVIEWSTATUS(Integer key,String value) {
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
            for (REVIEWSTATUS value : REVIEWSTATUS.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }
    /**
     * 是否分类活动商品
     */
    public enum HAVECATEGORYACTIVITY {

        NO(1,"否"),
        YES(2,"是"),
        REVIEW(3,"审核中");

        private Integer key;
        private String value;

        private HAVECATEGORYACTIVITY(Integer key,String value) {
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
            for (HAVECATEGORYACTIVITY value : HAVECATEGORYACTIVITY.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

    /**
     * 是否分类活动商品
     */
    public enum COMMODITYSORT {

        ONE(1,"按销量"),
        TWO(2,"按时间"),
        THREE(3,"按价格升序"),
        FOUR(4,"按价格倒序"),
        FIVE(5,"好评");

        private Integer key;
        private String value;

        private COMMODITYSORT(Integer key,String value) {
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
            for (COMMODITYSORT value : COMMODITYSORT.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

    /**
     * 店铺排序
     */
    public enum STORESORT {

        ONE(1,"按距离"),
        TWO(2,"按好评");

        private Integer key;
        private String value;

        private STORESORT(Integer key,String value) {
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
            for (STORESORT value : STORESORT.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }
    /**
     * 收藏类型
     */
    public enum COLLECTIONTYPE {

        ONE(1,"商品"),
        TWO(2,"店铺");

        private Integer key;
        private String value;

        private COLLECTIONTYPE(Integer key,String value) {
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
            for (COLLECTIONTYPE value : COLLECTIONTYPE.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }
    /**
     * 变动类型
     * 余额/积分变动类型
     */
    public enum CHANGETYPE {

        ONE(1,"充值"),
        TWO(2,"消费"),
        THREE(3,"退款");

        private Integer key;
        private String value;

        private CHANGETYPE(Integer key,String value) {
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
            for (CHANGETYPE value : CHANGETYPE.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }
    /**
     * 记录状态
     * 余额充值记录状态
     */
    public enum RECORDSTATUS {

        ONE(1,"用户创建充值订单"),
        TWO(2,"已经充值");

        private Integer key;
        private String value;

        private RECORDSTATUS(Integer key,String value) {
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
            for (RECORDSTATUS value : RECORDSTATUS.values()) {
                if (value.getKey() == key) {
                    return value.value;
                }
            }
            return "";
        }
    }

}
