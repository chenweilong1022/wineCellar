package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.service.CellarCommodityDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 酒窖订单明细表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-01 13:16:42
 */
@TableName("cellar_order_details_db")
@ApiModel("酒窖订单明细表")
@Data
public class CellarOrderDetailsDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单明细id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="订单明细id")
	private Long orderDetailsId;
	/**
	 * 订单id
	 */
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
	private Date createtime;
	/**
	 * 店铺id
	 */
	@ApiModelProperty(required=false,value="店铺id")
	private Long storeId;
	/**
	 * 商品id
	 */
	@ApiModelProperty(required=false,value="商品id")
	private Long commodityId;
	/**
	 * 商品数量
	 */
	@ApiModelProperty(required=false,value="商品数量")
	private BigDecimal number;
	/**
	 * 商品金额
	 */
	@ApiModelProperty(required=false,value="商品金额")
	private BigDecimal amountGoods;
	/**
	 * 商品总金额
	 */
	@ApiModelProperty(required=false,value="商品总金额")
	private BigDecimal totalAmountGoods;
	/**
	 * 商品金额
	 */
	@ApiModelProperty(required=false,value="商品金额")
	private BigDecimal integral;
	/**
	 * 商品总金额
	 */
	@ApiModelProperty(required=false,value="商品总金额")
	private BigDecimal totalIntegral;

	/**
	 * 商品名称
	 */
	@ApiModelProperty(required=false,value="商品名称")
	private String commodityName;
	/**
	 * 商品图片
	 */
	@ApiModelProperty(required=false,value="商品图片")
	private String picture;
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
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
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
	 * 预售时间
	 */
	@ApiModelProperty(required=false,value="预售时间")
	@TableField(exist = false)
	private Date presellTime;

	/**
	 * 设置：订单明细id
	 */
	public void setOrderDetailsId(Long orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}
	/**
	 * 获取：订单明细id
	 */
	public Long getOrderDetailsId() {
		return orderDetailsId;
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
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：店铺id
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	/**
	 * 获取：店铺id
	 */
	public Long getStoreId() {
		return storeId;
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

		if (ObjectUtil.isNotNull(this.commodityId)) {
			CellarCommodityDbService cellarCommodityDbService = SpringContextUtils.getBean(CellarCommodityDbService.class);
			CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(commodityId);
			this.picture = cellarCommodityDbEntity.getPicture();
			this.commodityName = cellarCommodityDbEntity.getCommodityName();
			this.presellTime = cellarCommodityDbEntity.getPresellTime();
		}
		return commodityId;
	}
	/**
	 * 设置：商品数量
	 */
	public void setNumber(BigDecimal number) {
		this.number = number;
	}
	/**
	 * 获取：商品数量
	 */
	public BigDecimal getNumber() {
		return number;
	}
	/**
	 * 设置：商品金额
	 */
	public void setAmountGoods(BigDecimal amountGoods) {
		this.amountGoods = amountGoods;
	}
	/**
	 * 获取：商品金额
	 */
	public BigDecimal getAmountGoods() {
		return amountGoods;
	}
	/**
	 * 设置：商品总金额
	 */
	public void setTotalAmountGoods(BigDecimal totalAmountGoods) {
		this.totalAmountGoods = totalAmountGoods;
	}
	/**
	 * 获取：商品总金额
	 */
	public BigDecimal getTotalAmountGoods() {
		return totalAmountGoods;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public BigDecimal getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(BigDecimal totalIntegral) {
		this.totalIntegral = totalIntegral;
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

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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
