package io.renren.modules.app.form;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 购物车提交订单实体类
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
public class SubmitOrdersByCartEntity {

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
     * 支付密码
     */
    @ApiModelProperty(required=false,value="支付密码")
    private String payPassword;


    private List<SubmitOrdersStoreEntity> submitOrdersStoreEntities;


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

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getMethodPayment() {
        return methodPayment;
    }

    public void setMethodPayment(Integer methodPayment) {
        this.methodPayment = methodPayment;
    }


    public List<SubmitOrdersStoreEntity> getSubmitOrdersStoreEntities() {
        return submitOrdersStoreEntities;
    }

    public void setSubmitOrdersStoreEntities(List<SubmitOrdersStoreEntity> submitOrdersStoreEntities) {
        this.submitOrdersStoreEntities = submitOrdersStoreEntities;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }
}
