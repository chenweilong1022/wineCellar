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

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 砍价活动表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-03 13:47:40
 */
@TableName("cellar_bargaining_activity_db")
@ApiModel("砍价活动表")
public class CellarBargainingActivityDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 砍价活动id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="砍价活动id")
	private Long bargainingActivityId;
	/**
	 * 状态
	 */
	@ApiModelProperty(required=false,value="状态")
	private Integer state;
	/**
	 * 商品信息
	 */
	@ApiModelProperty(required=false,value="商品信息")
	@TableField(exist = false)
	private CellarCommodityDbEntity cellarCommodityDbEntity;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date createTime;
	/**
	 * 商品id
	 */
	@ApiModelProperty(required=false,value="商品id")
	private Long commodityId;
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
	 * 砍价参与人数
	 */
	@ApiModelProperty(required=false,value="砍价参与人数")
	private BigDecimal bargainingParticipation;
	/**
	 * 砍价区间
	 */
	@ApiModelProperty(required=false,value="砍价区间")
	private BigDecimal bargainingInterval;
	/**
	 * 店铺id
	 */
	@ApiModelProperty(required=false,value="店铺id")
	private Long storeId;
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
	 * 设置：砍价初始价格
	 */
	public void setBargainingInitialPrice(BigDecimal bargainingInitialPrice) {
		this.bargainingInitialPrice = bargainingInitialPrice;
	}
	/**
	 * 获取：砍价初始价格
	 */
	public BigDecimal getBargainingInitialPrice() {
		return bargainingInitialPrice;
	}
	/**
	 * 设置：砍价最低价格
	 */
	public void setBargainingLowestPrice(BigDecimal bargainingLowestPrice) {
		this.bargainingLowestPrice = bargainingLowestPrice;
	}
	/**
	 * 获取：砍价最低价格
	 */
	public BigDecimal getBargainingLowestPrice() {
		return bargainingLowestPrice;
	}
	/**
	 * 设置：砍价参与人数
	 */
	public void setBargainingParticipation(BigDecimal bargainingParticipation) {
		this.bargainingParticipation = bargainingParticipation;
	}
	/**
	 * 获取：砍价参与人数
	 */
	public BigDecimal getBargainingParticipation() {
		return bargainingParticipation;
	}
	/**
	 * 设置：砍价区间
	 */
	public void setBargainingInterval(BigDecimal bargainingInterval) {
		this.bargainingInterval = bargainingInterval;
	}
	/**
	 * 获取：砍价区间
	 */
	public BigDecimal getBargainingInterval() {
		return bargainingInterval;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		super.setStoreName(storeId);
		this.storeId = storeId;
	}

	public CellarCommodityDbEntity getCellarCommodityDbEntity() {
		if (flag && ObjectUtil.isNotNull(commodityId)) {
			CellarCommodityDbService cellarCommodityDbService = SpringContextUtils.getBean(CellarCommodityDbService.class);
			this.cellarCommodityDbEntity = cellarCommodityDbService.getById(this.commodityId);
		}
		return cellarCommodityDbEntity;
	}
}
