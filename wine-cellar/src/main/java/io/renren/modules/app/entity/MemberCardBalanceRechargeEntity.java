package io.renren.modules.app.entity;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.entity.CellarMemberCardDbEntity;
import io.renren.modules.cellar.service.CellarMemberCardDbService;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 用户余额充值
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-01-16 17:34:20
 */
public class MemberCardBalanceRechargeEntity {

    /**
     * 会员储值卡id
     */
    @ApiModelProperty(required=false,value="会员储值卡id")
    private Long memberCardId;
    /**
     * 会员储值卡
     */
    @ApiModelProperty(required=false,value="会员储值卡")
    private CellarMemberCardDbEntity cellarMemberCardDbEntity;
    /**
     * 支付方式
     */
    @ApiModelProperty(required=false,value="支付方式0:微信1:支付宝")
    private Integer methodPayment;

    public Integer getMethodPayment() {
        return methodPayment;
    }

    public void setMethodPayment(Integer methodPayment) {
        this.methodPayment = methodPayment;
    }

    public Long getMemberCardId() {
        return memberCardId;
    }

    public void setMemberCardId(Long memberCardId) {
        this.memberCardId = memberCardId;
    }

    public CellarMemberCardDbEntity getCellarMemberCardDbEntity() {
        if (ObjectUtil.isNotNull(this.memberCardId)) {
            CellarMemberCardDbService cellarMemberCardDbService = SpringContextUtils.getBean(CellarMemberCardDbService.class);
            this.cellarMemberCardDbEntity = cellarMemberCardDbService.getById(this.memberCardId);
        }
        return cellarMemberCardDbEntity;
    }
}
