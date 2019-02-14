package io.renren.common.constants;

public class Constants {

    /**
     * 数字状态枚举
     */
    public enum Number {
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
     * 全局级别描述新注册会员为1级
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

}
