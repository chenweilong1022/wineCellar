package io.renren.modules.app.form;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 直接购买提交订单实体类
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
public class SubmitOrdersByDirectlyEntity {

    /**
     * 取货方式
     */
    @ApiModelProperty(required=false,value="订单类型0:普通订单1:自提订单2:预约订单3:预售订单")
    private Integer pickupWay;
    /**
     * 地址id
     */
    @ApiModelProperty(required=false,value="地址id")
    private Long addressId;
    /**
     * 自提人手机号
     */
    @ApiModelProperty(required=false,value="自提人手机号")
    private String pickUpPhone;
    /**
     * 送达时间
     */
    @ApiModelProperty(required=false,value="送达时间")
    private Date deliveryTime;
    /**
     * 支付方式
     */
    @ApiModelProperty(required=false,value="支付方式0:微信1:支付宝")
    private Integer methodPayment;
    /**
     * 会员优惠券id
     */
    @ApiModelProperty(required=false,value="会员优惠券id")
    private Long memberCouponId;

    /**
     * 门店id
     */
    @ApiModelProperty(required=false,value="门店id")
    private Long storeId;
    /**
     * 订单备注
     */
    @ApiModelProperty(required=false,value="订单备注")
    private String orderNote;
    /**
     * 商品id
     */
    @ApiModelProperty(required=false,value="商品id")
    private Long[] commodityId;
    /**
     * 数量
     */
    @ApiModelProperty(required=false,value="数量")
    private BigDecimal[] number;

    public Integer getPickupWay() {
        return pickupWay;
    }

    public void setPickupWay(Integer pickupWay) {
        this.pickupWay = pickupWay;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getPickUpPhone() {
        return pickUpPhone;
    }

    public void setPickUpPhone(String pickUpPhone) {
        this.pickUpPhone = pickUpPhone;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        if (StringUtils.isNotBlank(deliveryTime)) {
            this.deliveryTime = DateUtil.parse(deliveryTime);
            return;
        }
        this.deliveryTime = null;
    }

    public Integer getMethodPayment() {
        return methodPayment;
    }

    public void setMethodPayment(Integer methodPayment) {
        this.methodPayment = methodPayment;
    }


    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public Long[] getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long[] commodityId) {
        this.commodityId = commodityId;
    }

    public BigDecimal[] getNumber() {
        return number;
    }

    public void setNumber(BigDecimal[] number) {
        this.number = number;
    }

    public Long getMemberCouponId() {
        return memberCouponId;
    }

    public void setMemberCouponId(Long memberCouponId) {
        this.memberCouponId = memberCouponId;
    }
}
