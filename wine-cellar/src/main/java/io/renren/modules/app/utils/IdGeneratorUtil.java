package io.renren.modules.app.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.Date;

/**
 * id生成工具类
 */
public class IdGeneratorUtil {

    /**
     * 生成订单编号
     * @return
     */
    public static String getOrderNo() {
        String numbers = RandomUtil.randomNumbers(6);
        return DateUtil.format(new Date(),"yyyyMMddHHmmssSSS") + numbers;
    }

    /**
     * 生成订单id
     * @return
     */
    public static Long getOrderId() {
        String numbers = RandomUtil.randomNumbers(2);
        return Long.valueOf((DateUtil.format(new Date(),"yyyyMMddHHmmssSSS") + numbers));
    }
}
