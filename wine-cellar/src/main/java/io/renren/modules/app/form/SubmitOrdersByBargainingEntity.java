package io.renren.modules.app.form;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀商品直接购买提交订单实体类
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
public class SubmitOrdersByBargainingEntity {

    /**
     * 取货方式
     */
    @ApiModelProperty(required=false,value="订单类型0:普通订单1:自提订单2:预约订单3:预售订单4:拼团5:秒杀6:砍价")
    private Integer pickupWay;
    /**
     * 是否自提
     */
    @ApiModelProperty(required=false,value="0:否1:是")
    private Integer isHave;
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
     * 支付密码
     */
    @ApiModelProperty(required=false,value="支付密码")
    private String payPassword;
//    /**
//     * 商品id
//     */
//    @ApiModelProperty(required=false,value="商品id")
//    private Long[] commodityId;
    /**
     * 会员砍价信息id
     */
    @ApiModelProperty(required=false,value="会员砍价信息id")
    private Long[] memberBargainingInformationId;
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

//    public Long[] getCommodityId() {
//        return commodityId;
//    }
//
//    public void setCommodityId(Long[] commodityId) {
//        this.commodityId = commodityId;
//    }

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

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }


    public Integer getIsHave() {
        return isHave;
    }

    public void setIsHave(Integer isHave) {
        this.isHave = isHave;
    }

    public Long[] getMemberBargainingInformationId() {
        return memberBargainingInformationId;
    }

    public void setMemberBargainingInformationId(Long[] memberBargainingInformationId) {
        this.memberBargainingInformationId = memberBargainingInformationId;
    }
}
