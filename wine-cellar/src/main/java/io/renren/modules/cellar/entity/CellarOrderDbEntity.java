package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.service.CellarMemberAddressDbService;
import io.renren.modules.cellar.service.CellarOrderDetailsDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 酒窖订单表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-01 13:16:42
 */
@TableName("cellar_order_db")
@ApiModel("酒窖订单表")
public class CellarOrderDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
	@TableId(type = IdType.NONE)
	@ApiModelProperty(required=false,value="订单id")
	private Long orderId;
	/**
	 * 状态
	 */
	@ApiModelProperty(required=false,value="状态")
	private Integer state;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date createTime;
	/**
	 * 地址id
	 */
	@ApiModelProperty(required=false,value="地址id")
	private Long addressId;
	/**
	 * 送达时间
	 */
	@ApiModelProperty(required=false,value="送达时间")
	private Date deliveryTime;
	/**
	 * 自提人手机号
	 */
	@ApiModelProperty(required=false,value="自提人手机号")
	private String pickUpPhone;
	/**
	 * 支付方式
	 */
	@ApiModelProperty(required=false,value="支付方式")
	private Integer methodPayment;
	/**
	 * 支付方式
	 */
	@ApiModelProperty(required=false,value="支付方式")
	@TableField(exist = false)
	private String methodPaymentStr;
	/**
	 * 支付时间
	 */
	@ApiModelProperty(required=false,value="支付时间")
	private Date paymentTime;
	/**
	 * 订单备注
	 */
	@ApiModelProperty(required=false,value="订单备注")
	private String orderNote;
	/**
	 * 订单总金额
	 */
	@ApiModelProperty(required=false,value="订单总金额")
	private BigDecimal totalOrderAmount;
	/**
	 * 订单状态
	 */
	@ApiModelProperty(required=false,value="订单状态")
	private Integer orderStatus;
	/**
	 * 订单状态
	 */
	@ApiModelProperty(required=false,value="订单状态")
	@TableField(exist = false)
	private String orderStatusStr;
	/**
	 * 订单类型
	 */
	@ApiModelProperty(required=false,value="订单类型")
	private Integer orderType;
	/**
	 * 订单类型
	 */
	@ApiModelProperty(required=false,value="订单类型")
	@TableField(exist = false)
	private String orderTypeStr;
	/**
	 * 店铺满减金额
	 */
	@ApiModelProperty(required=false,value="店铺满减金额")
	private BigDecimal storeFullAmount;
	/**
	 * 配送金额
	 */
	@ApiModelProperty(required=false,value="配送金额")
	private BigDecimal distributionAmount;
	/**
	 * 优惠金额
	 */
	@ApiModelProperty(required=false,value="优惠金额")
	private BigDecimal discountAmount;
	/**
	 * 订单编号
	 */
	@ApiModelProperty(required=false,value="订单编号")
	private String orderNo;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
	/**
	 * 店铺id
	 */
	@ApiModelProperty(required=false,value="店铺id")
	private Long storeId;
	/**
	 * 订单实际金额
	 */
	@ApiModelProperty(required=false,value="订单实际金额")
	private BigDecimal actualOrderAmount;
	/**
	 * 省id
	 */
	@ApiModelProperty(required=false,value="省id")
	private Long provinceId;
	/**
	 * 省名称
	 */
	@ApiModelProperty(required=false,value="省名称")
	private String provinceName;
	/**
	 * 市id
	 */
	@ApiModelProperty(required=false,value="市id")
	private Long cityId;
	/**
	 * 市名称
	 */
	@ApiModelProperty(required=false,value="市名称")
	private String cityName;
	/**
	 * 县id
	 */
	@ApiModelProperty(required=false,value="县id")
	private Long countyId;
	/**
	 * 县名称
	 */
	@ApiModelProperty(required=false,value="县名称")
	private String countyName;
	/**
	 * 详细地址
	 */
	@ApiModelProperty(required=false,value="详细地址")
	private String detailedAddress;
	/**
	 * 订单下商品集合
	 */
	@ApiModelProperty(required=false,value="订单下商品集合")
	@TableField(exist = false)
	private List<CellarOrderDetailsDbEntity> cellarOrderDetailsDbEntities;
	/**
	 * 退货原因
	 */
	@ApiModelProperty(required=false,value="退货原因")
	private String returnReason;
	/**
	 * 会员优惠券id
	 */
	@ApiModelProperty(required=false,value="会员优惠券id")
	private Long memberCouponId;
	/**
	 * 订单积分
	 */
	@ApiModelProperty(required=false,value="订单积分")
	private BigDecimal integral;
	/**
	 * 是否自提
	 */
	@ApiModelProperty(required=false,value="0:否1:是")
	private Integer isHave;
	/**
	 * 秒杀活动id
	 */
	@ApiModelProperty(required=false,value="秒杀活动id")
	private Long killActivityId;
	/**
	 * 拼团活动id
	 */
	@ApiModelProperty(required=false,value="拼团活动id")
	private Long groupActivityId;
	/**
	 * 砍价活动id
	 */
	@ApiModelProperty(required=false,value="砍价活动id")
	private Long bargainingActivityId;
	/**
	 * 会员砍价信息id
	 */
	@ApiModelProperty(required=false,value="会员砍价信息id")
	private Long memberBargainingInformationId;
	/**
	 * 设置：订单id
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单id
	 */
	public String getOrderId() {
		return orderId.toString();
	}
	/**
	 * 设置：状态
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：状态
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：地址id
	 */
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	/**
	 * 获取：地址id
	 */
	public Long getAddressId() {
		if (this.addressId != null && this.provinceId == null) {
			CellarMemberAddressDbService cellarMemberAddressDbService = SpringContextUtils.getBean(CellarMemberAddressDbService.class);
			CellarMemberAddressDbEntity cellarMemberAddressDbEntity = cellarMemberAddressDbService.getById(this.addressId);
			this.provinceId = cellarMemberAddressDbEntity.getProvinceId();
			this.provinceName = cellarMemberAddressDbEntity.getProvinceName();
			this.cityId = cellarMemberAddressDbEntity.getCityId();
			this.cityName = cellarMemberAddressDbEntity.getCityName();
			this.countyId = cellarMemberAddressDbEntity.getCountyId();
			this.countyName = cellarMemberAddressDbEntity.getCountyName();
			this.detailedAddress = cellarMemberAddressDbEntity.getDetailedAddress();
		}
		return addressId;
	}
	/**
	 * 设置：送达时间
	 */
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	/**
	 * 获取：送达时间
	 */
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	/**
	 * 设置：支付方式
	 */
	public void setMethodPayment(Integer methodPayment) {
		this.methodPayment = methodPayment;
	}
	/**
	 * 获取：支付方式
	 */
	public Integer getMethodPayment() {
		return methodPayment;
	}
	/**
	 * 设置：支付时间
	 */
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	/**
	 * 获取：支付时间
	 */
	public Date getPaymentTime() {
		return paymentTime;
	}
	/**
	 * 设置：订单备注
	 */
	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}
	/**
	 * 获取：订单备注
	 */
	public String getOrderNote() {
		return orderNote;
	}
	/**
	 * 设置：订单总金额
	 */
	public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}
	/**
	 * 获取：订单总金额
	 */
	public BigDecimal getTotalOrderAmount() {
		return totalOrderAmount;
	}
	/**
	 * 设置：订单状态
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * 获取：订单状态
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	/**
	 * 设置：订单类型
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	/**
	 * 获取：订单类型
	 */
	public Integer getOrderType() {
		return orderType;
	}
	/**
	 * 设置：店铺满减金额
	 */
	public void setStoreFullAmount(BigDecimal storeFullAmount) {
		this.storeFullAmount = storeFullAmount;
	}
	/**
	 * 获取：店铺满减金额
	 */
	public BigDecimal getStoreFullAmount() {
		return storeFullAmount;
	}
	/**
	 * 设置：配送金额
	 */
	public void setDistributionAmount(BigDecimal distributionAmount) {
		this.distributionAmount = distributionAmount;
	}
	/**
	 * 获取：配送金额
	 */
	public BigDecimal getDistributionAmount() {
		return distributionAmount;
	}
	/**
	 * 设置：优惠金额
	 */
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	/**
	 * 获取：优惠金额
	 */
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	/**
	 * 设置：订单编号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单编号
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：会员id
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：会员id
	 */
	public Long getMemberId() {
		return memberId;
	}
	/**
	 * 设置：店铺id
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
		super.setStoreName(this.storeId);
	}
	/**
	 * 获取：店铺id
	 */
	public Long getStoreId() {
		return storeId;
	}
	/**
	 * 设置：订单实际金额
	 */
	public void setActualOrderAmount(BigDecimal actualOrderAmount) {
		this.actualOrderAmount = actualOrderAmount;
	}
	/**
	 * 获取：订单实际金额
	 */
	public BigDecimal getActualOrderAmount() {
		return actualOrderAmount;
	}

	public String getPickUpPhone() {
		return pickUpPhone;
	}

	public void setPickUpPhone(String pickUpPhone) {
		this.pickUpPhone = pickUpPhone;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	/**
	 * 获取订单下商品信息
	 * @return
	 */
	public List<CellarOrderDetailsDbEntity> getCellarOrderDetailsDbEntities() {
		if (ObjectUtil.isNull(cellarOrderDetailsDbEntities) && ObjectUtil.isNotNull(this.orderId)) {
			CellarOrderDetailsDbService cellarOrderDetailsDbService = SpringContextUtils.getBean(CellarOrderDetailsDbService.class);
			List<CellarOrderDetailsDbEntity> cellarOrderDetailsDbEntities = cellarOrderDetailsDbService.list(new QueryWrapper<CellarOrderDetailsDbEntity>().lambda()
					.eq(CellarOrderDetailsDbEntity::getOrderId, this.orderId)
			);
			this.cellarOrderDetailsDbEntities = cellarOrderDetailsDbEntities;
		}
		return cellarOrderDetailsDbEntities;
	}

	public void setCellarOrderDetailsDbEntities(List<CellarOrderDetailsDbEntity> cellarOrderDetailsDbEntities) {
		this.cellarOrderDetailsDbEntities = cellarOrderDetailsDbEntities;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public String getMethodPaymentStr() {
		if (this.methodPayment != null) {
			this.methodPaymentStr = Constants.METHODPAYMENT.getValueByKey(this.methodPayment);
		}
		return methodPaymentStr;
	}

	public void setMethodPaymentStr(String methodPaymentStr) {
		this.methodPaymentStr = methodPaymentStr;
	}

	public String getOrderStatusStr() {
		if (this.orderStatus != null) {
			this.orderStatusStr = Constants.ORDERSTATUS.getValueByKey(this.orderStatus);
		}
		return orderStatusStr;
	}

	public void setOrderStatusStr(String orderStatusStr) {
		this.orderStatusStr = orderStatusStr;
	}

	public String getOrderTypeStr() {
		if (this.orderType != null) {
			this.orderTypeStr = Constants.CARTTYPE.getValueByKey(this.orderType);
		}
		return orderTypeStr;
	}

	public void setOrderTypeStr(String orderTypeStr) {
		this.orderTypeStr = orderTypeStr;
	}

	public Long getMemberCouponId() {
		return memberCouponId;
	}

	public void setMemberCouponId(Long memberCouponId) {
		this.memberCouponId = memberCouponId;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public Integer getIsHave() {
		return isHave;
	}

	public void setIsHave(Integer isHave) {
		this.isHave = isHave;
	}

	public Long getKillActivityId() {
		return killActivityId;
	}

	public void setKillActivityId(Long killActivityId) {
		this.killActivityId = killActivityId;
	}

	public Long getGroupActivityId() {
		return groupActivityId;
	}

	public void setGroupActivityId(Long groupActivityId) {
		this.groupActivityId = groupActivityId;
	}

	public Long getBargainingActivityId() {
		return bargainingActivityId;
	}

	public void setBargainingActivityId(Long bargainingActivityId) {
		this.bargainingActivityId = bargainingActivityId;
	}

	public Long getMemberBargainingInformationId() {
		return memberBargainingInformationId;
	}

	public void setMemberBargainingInformationId(Long memberBargainingInformationId) {
		this.memberBargainingInformationId = memberBargainingInformationId;
	}
}
