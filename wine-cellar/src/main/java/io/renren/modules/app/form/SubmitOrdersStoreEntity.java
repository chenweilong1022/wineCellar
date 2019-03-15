package io.renren.modules.app.form;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 购物车提交订单门店表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
public class SubmitOrdersStoreEntity {

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
     * 门店下购物车id
     */
    @ApiModelProperty(required=false,value="门店下购物车id")
    private List<Long> cartIds;
    /**
     * 优惠券id
     */
    @ApiModelProperty(required=false,value="优惠券id")
    private Long memberCouponId;

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

    public List<Long> getCartIds() {
        return cartIds;
    }

    public void setCartIds(List<Long> cartIds) {
        this.cartIds = cartIds;
    }

    public Long getMemberCouponId() {
        return memberCouponId;
    }

    public void setMemberCouponId(Long memberCouponId) {
        this.memberCouponId = memberCouponId;
    }
}
