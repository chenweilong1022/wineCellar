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
 * 会员砍价信息表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-03 15:22:05
 */
@TableName("cellar_member_bargaining_information_db")
@ApiModel("会员砍价信息表")
public class CellarMemberBargainingInformationDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员砍价信息id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员砍价信息id")
	private Long memberBargainingInformationId;
	/**
	 * 砍价活动id
	 */
	@ApiModelProperty(required=false,value="砍价活动id")
	private Long bargainingActivityId;
	/**
	 * 商品id
	 */
	@ApiModelProperty(required=false,value="商品id")
	private Long commodityId;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
	/**
	 * 被砍次数
	 */
	@ApiModelProperty(required=false,value="被砍次数")
	private BigDecimal cuttingDownNumber;
	/**
	 * 砍价参与人数
	 */
	@ApiModelProperty(required=false,value="砍价参与人数")
	private BigDecimal bargainingParticipation;
	/**
	 * 砍价初始价格
	 */
	@ApiModelProperty(required=false,value="砍价初始价格")
	private BigDecimal bargainingInitialPrice;
	/**
	 * 砍价最低价格
	 */
	@ApiModelProperty(required=false,value="砍价最低价格")
	private BigDecimal bargainingLowestPrice;
	/**
	 * 最终价格
	 */
	@ApiModelProperty(required=false,value="最终价格")
	private BigDecimal finalPrice;
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
	 * 设置：会员砍价信息id
	 */
	public void setMemberBargainingInformationId(Long memberBargainingInformationId) {
		this.memberBargainingInformationId = memberBargainingInformationId;
	}
	/**
	 * 获取：会员砍价信息id
	 */
	public Long getMemberBargainingInformationId() {
		return memberBargainingInformationId;
	}
	/**
	 * 设置：砍价活动id
	 */
	public void setBargainingActivityId(Long bargainingActivityId) {
		this.bargainingActivityId = bargainingActivityId;
	}
	/**
	 * 获取：砍价活动id
	 */
	public Long getBargainingActivityId() {
		return bargainingActivityId;
	}
	/**
	 * 设置：商品id
	 */
	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}
	/**
	 * 获取：商品id
	 */
	public Long getCommodityId() {
		return commodityId;
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
	 * 设置：被砍次数
	 */
	public void setCuttingDownNumber(BigDecimal cuttingDownNumber) {
		this.cuttingDownNumber = cuttingDownNumber;
	}
	/**
	 * 获取：被砍次数
	 */
	public BigDecimal getCuttingDownNumber() {
		return cuttingDownNumber;
	}
	/**
	 * 设置：最终价格
	 */
	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}
	/**
	 * 获取：最终价格
	 */
	public BigDecimal getFinalPrice() {
		return finalPrice;
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

	public BigDecimal getBargainingParticipation() {
		return bargainingParticipation;
	}

	public void setBargainingParticipation(BigDecimal bargainingParticipation) {
		this.bargainingParticipation = bargainingParticipation;
	}

	public BigDecimal getBargainingInitialPrice() {
		return bargainingInitialPrice;
	}

	public void setBargainingInitialPrice(BigDecimal bargainingInitialPrice) {
		this.bargainingInitialPrice = bargainingInitialPrice;
	}

	public BigDecimal getBargainingLowestPrice() {
		return bargainingLowestPrice;
	}

	public void setBargainingLowestPrice(BigDecimal bargainingLowestPrice) {
		this.bargainingLowestPrice = bargainingLowestPrice;
	}
}
