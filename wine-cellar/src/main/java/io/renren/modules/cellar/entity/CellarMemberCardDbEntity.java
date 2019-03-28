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
 * 会员储值卡表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-28 13:34:38
 */
@TableName("cellar_member_card_db")
@ApiModel("会员储值卡表")
public class CellarMemberCardDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员储值卡id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员储值卡id")
	private Long memberCardId;
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
	 * 储值卡金额
	 */
	@ApiModelProperty(required=false,value="储值卡金额")
	private BigDecimal cardBalance;

	/**
	 * 设置：会员储值卡id
	 */
	public void setMemberCardId(Long memberCardId) {
		this.memberCardId = memberCardId;
	}
	/**
	 * 获取：会员储值卡id
	 */
	public Long getMemberCardId() {
		return memberCardId;
	}
	/**
	 * 设置：状态
	 */
	public void setState(Integer state) {
		super.setStateStr(state);
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
	 * 设置：储值卡金额
	 */
	public void setCardBalance(BigDecimal cardBalance) {
		this.cardBalance = cardBalance;
	}
	/**
	 * 获取：储值卡金额
	 */
	public BigDecimal getCardBalance() {
		return cardBalance;
	}
}
