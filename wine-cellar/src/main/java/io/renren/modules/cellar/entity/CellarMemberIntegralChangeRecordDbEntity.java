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
 * 会员积分变动记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-26 13:59:37
 */
@TableName("cellar_member_integral_change_record_db")
@ApiModel("会员积分变动记录表")
public class CellarMemberIntegralChangeRecordDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员积分变动记录id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员积分变动记录id")
	private Long memberIntegralChangeRecordId;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
	/**
	 * 变动积分
	 */
	@ApiModelProperty(required=false,value="变动积分")
	private BigDecimal changeIntegral;
	/**
	 * 变动前积分
	 */
	@ApiModelProperty(required=false,value="变动前积分")
	private BigDecimal beforeChange;
	/**
	 * 变动后积分
	 */
	@ApiModelProperty(required=false,value="变动后积分")
	private BigDecimal afterIntegral;
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
	 * 设置：会员积分变动记录id
	 */
	public void setMemberIntegralChangeRecordId(Long memberIntegralChangeRecordId) {
		this.memberIntegralChangeRecordId = memberIntegralChangeRecordId;
	}
	/**
	 * 获取：会员积分变动记录id
	 */
	public Long getMemberIntegralChangeRecordId() {
		return memberIntegralChangeRecordId;
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
	 * 设置：变动积分
	 */
	public void setChangeIntegral(BigDecimal changeIntegral) {
		this.changeIntegral = changeIntegral;
	}
	/**
	 * 获取：变动积分
	 */
	public BigDecimal getChangeIntegral() {
		return changeIntegral;
	}
	/**
	 * 设置：变动前积分
	 */
	public void setBeforeChange(BigDecimal beforeChange) {
		this.beforeChange = beforeChange;
	}
	/**
	 * 获取：变动前积分
	 */
	public BigDecimal getBeforeChange() {
		return beforeChange;
	}
	/**
	 * 设置：变动后积分
	 */
	public void setAfterIntegral(BigDecimal afterIntegral) {
		this.afterIntegral = afterIntegral;
	}
	/**
	 * 获取：变动后积分
	 */
	public BigDecimal getAfterIntegral() {
		return afterIntegral;
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
}
