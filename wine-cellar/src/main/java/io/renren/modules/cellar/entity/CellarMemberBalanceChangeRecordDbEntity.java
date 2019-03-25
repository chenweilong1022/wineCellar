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
 * 会员余额变动记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-25 11:07:23
 */
@TableName("cellar_member_balance_change_record_db")
@ApiModel("会员余额变动记录表")
public class CellarMemberBalanceChangeRecordDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员余额变动记录id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员余额变动记录id")
	private Long memberBalanceChangeRecordId;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
	/**
	 * 变动金额
	 */
	@ApiModelProperty(required=false,value="变动金额")
	private BigDecimal changeBalance;
	/**
	 * 变动前金额
	 */
	@ApiModelProperty(required=false,value="变动前金额")
	private BigDecimal beforeBalance;
	/**
	 * 变动后金额
	 */
	@ApiModelProperty(required=false,value="变动后金额")
	private BigDecimal afterBalance;
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
	@ApiModelProperty(required=false,value="支付方式0:微信1:支付宝")
	private Integer methodPayment;
	/**
	 * 支付时间
	 */
	@ApiModelProperty(required=false,value="支付时间")
	private Date paymentTime;
	/**
	 * 记录状态
	 */
	@ApiModelProperty(required=false,value="记录状态")
	private Integer recordStatus;

	/**
	 * 设置：会员余额变动记录id
	 */
	public void setMemberBalanceChangeRecordId(Long memberBalanceChangeRecordId) {
		this.memberBalanceChangeRecordId = memberBalanceChangeRecordId;
	}
	/**
	 * 获取：会员余额变动记录id
	 */
	public Long getMemberBalanceChangeRecordId() {
		return memberBalanceChangeRecordId;
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
	 * 设置：变动金额
	 */
	public void setChangeBalance(BigDecimal changeBalance) {
		this.changeBalance = changeBalance;
	}
	/**
	 * 获取：变动金额
	 */
	public BigDecimal getChangeBalance() {
		return changeBalance;
	}
	/**
	 * 设置：变动前金额
	 */
	public void setBeforeBalance(BigDecimal beforeBalance) {
		this.beforeBalance = beforeBalance;
	}
	/**
	 * 获取：变动前金额
	 */
	public BigDecimal getBeforeBalance() {
		return beforeBalance;
	}
	/**
	 * 设置：变动后金额
	 */
	public void setAfterBalance(BigDecimal afterBalance) {
		this.afterBalance = afterBalance;
	}
	/**
	 * 获取：变动后金额
	 */
	public BigDecimal getAfterBalance() {
		return afterBalance;
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

	public Integer getMethodPayment() {
		return methodPayment;
	}

	public void setMethodPayment(Integer methodPayment) {
		this.methodPayment = methodPayment;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}
}
