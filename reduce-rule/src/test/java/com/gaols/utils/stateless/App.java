package com.gaols.utils.stateless;

import com.gaols.utils.PingDuoDuoReduceRule;

import java.math.BigDecimal;
import java.util.List;

public class App {
    public static void main(String[] args) {
        PingDuoDuoReduceRule pingRule = new PingDuoDuoReduceRule(70, 5);
        List<BigDecimal> reduceList = pingRule.getReduceList(new BigDecimal("50"), 10);
        System.out.println(reduceList);
        BigDecimal total = BigDecimal.ZERO;
        for (BigDecimal bigDecimal : reduceList) {
            total = total.add(bigDecimal);
        }
        System.out.println(total);
    }
}
