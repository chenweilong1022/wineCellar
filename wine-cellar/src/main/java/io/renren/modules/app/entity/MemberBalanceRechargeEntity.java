package io.renren.modules.app.entity;

import io.renren.modules.cellar.entity.CellarMemberMessageDbEntity;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 用户余额充值
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-01-16 17:34:20
 */
public class MemberBalanceRechargeEntity {

    /**
     * 充值金额
     */
    @ApiModelProperty(required=false,value="充值金额")
    private BigDecimal balance;
    /**
     * 支付方式
     */
    @ApiModelProperty(required=false,value="支付方式0:微信1:支付宝")
    private Integer methodPayment;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getMethodPayment() {
        return methodPayment;
    }

    public void setMethodPayment(Integer methodPayment) {
        this.methodPayment = methodPayment;
    }
}
