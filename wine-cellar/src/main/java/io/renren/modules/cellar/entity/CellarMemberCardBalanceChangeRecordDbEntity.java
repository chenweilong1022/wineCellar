package io.renren.modules.cellar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员储值卡余额变动记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-28 14:31:48
 */
@TableName("cellar_member_card_balance_change_record_db")
@ApiModel("会员储值卡余额变动记录表")
public class CellarMemberCardBalanceChangeRecordDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员储值卡余额变动记录id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员储值卡余额变动记录id")
	private Long memberCardBalanceChangeRecordId;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
	/**
	 * 储值卡变动金额
	 */
	@ApiModelProperty(required=false,value="储值卡变动金额")
	private BigDecimal changeCardBalance;
	/**
	 * 储值卡变动前金额
	 */
	@ApiModelProperty(required=false,value="储值卡变动前金额")
	private BigDecimal beforeCardBalance;
	/**
	 * 储值卡变动后金额
	 */
	@ApiModelProperty(required=false,value="储值卡变动后金额")
	private BigDecimal afterCardBalance;
	/**
	 * 变动类型
	 */
	@ApiModelProperty(required=false,value="变动类型")
	private Integer changeType;
	/**
	 * 变动描述
	 */
	@ApiModelProperty(required=false,value="变动描述")
	private String changeDesc;
	/**
	 * 订单id
	 */
	@ApiModelProperty(required=false,value="订单id")
	private Long orderId;
	/**
	 * 订单编号
	 */
	@ApiModelProperty(required=false,value="订单编号")
	private String orderNo;
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
	 * 支付方式
	 */
	@ApiModelProperty(required=false,value="支付方式")
	private Integer methodPayment;
	/**
	 * 支付时间
	 */
	@ApiModelProperty(required=false,value="支付时间")
	private Date paymentTime;
	/**
	 * 支付状态
	 */
	@ApiModelProperty(required=false,value="支付状态")
	private Integer recordStatus;

	/**
	 * 设置：会员储值卡余额变动记录id
	 */
	public void setMemberCardBalanceChangeRecordId(Long memberCardBalanceChangeRecordId) {
		this.memberCardBalanceChangeRecordId = memberCardBalanceChangeRecordId;
	}
	/**
	 * 获取：会员储值卡余额变动记录id
	 */
	public Long getMemberCardBalanceChangeRecordId() {
		return memberCardBalanceChangeRecordId;
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
	 * 设置：储值卡变动金额
	 */
	public void setChangeCardBalance(BigDecimal changeCardBalance) {
		this.changeCardBalance = changeCardBalance;
	}
	/**
	 * 获取：储值卡变动金额
	 */
	public BigDecimal getChangeCardBalance() {
		return changeCardBalance;
	}
	/**
	 * 设置：储值卡变动前金额
	 */
	public void setBeforeCardBalance(BigDecimal beforeCardBalance) {
		this.beforeCardBalance = beforeCardBalance;
	}
	/**
	 * 获取：储值卡变动前金额
	 */
	public BigDecimal getBeforeCardBalance() {
		return beforeCardBalance;
	}
	/**
	 * 设置：储值卡变动后金额
	 */
	public void setAfterCardBalance(BigDecimal afterCardBalance) {
		this.afterCardBalance = afterCardBalance;
	}
	/**
	 * 获取：储值卡变动后金额
	 */
	public BigDecimal getAfterCardBalance() {
		return afterCardBalance;
	}
	/**
	 * 设置：变动类型
	 */
	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}
	/**
	 * 获取：变动类型
	 */
	public Integer getChangeType() {
		return changeType;
	}
	/**
	 * 设置：变动描述
	 */
	public void setChangeDesc(String changeDesc) {
		this.changeDesc = changeDesc;
	}
	/**
	 * 获取：变动描述
	 */
	public String getChangeDesc() {
		return changeDesc;
	}
	/**
	 * 设置：订单id
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单id
	 */
	public Long getOrderId() {
		return orderId;
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
	 * 设置：支付状态
	 */
	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}
	/**
	 * 获取：支付状态
	 */
	public Integer getRecordStatus() {
		return recordStatus;
	}
}
