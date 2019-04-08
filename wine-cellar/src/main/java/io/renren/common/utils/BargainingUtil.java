package io.renren.common.utils;

import com.gaols.utils.PingDuoDuoReduceRule;
import com.gaols.utils.SfReduceRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BargainingUtil {

    /**
     * 平多多规则
     * @param price
     * @param count
     * @return
     */
    public static List<BigDecimal> pingDuoDuoReduceRule(BigDecimal price,Integer count) {
        PingDuoDuoReduceRule pingRule = new PingDuoDuoReduceRule(70, 5);
        List<BigDecimal> reduceList = pingRule.getReduceList(price, count);
        return reduceList;
    }

    /**
     * 其他规则
     * @param totalReduce
     * @param totalReduceTimes
     * @return
     */
    public static BigDecimal sfReduceRule(BigDecimal totalReduce, int totalReduceTimes) {
        List<BigDecimal> reduceList = new SfReduceRule().getReduceList(totalReduce, totalReduceTimes);
        return reduceList.get(0);
    }
}
