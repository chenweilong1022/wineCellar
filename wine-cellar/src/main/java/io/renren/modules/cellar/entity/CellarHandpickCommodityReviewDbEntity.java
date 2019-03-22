package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.constants.Constants;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.service.CellarCommodityDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 精选商品审核表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-20 10:56:44
 */
@TableName("cellar_handpick_commodity_review_db")
@ApiModel("精选商品审核表")
public class CellarHandpickCommodityReviewDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 精选商品id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="精选商品id")
	private Long handpickCommodityReviewId;
	/**
	 * 精选商品id
	 */
	@ApiModelProperty(required=false,value="精选商品id")
	@TableField(exist = false)
	private Long[] handpickCommodityReviewIds;
	/**
	 * 商品id
	 */
	@ApiModelProperty(required=false,value="商品id")
	private Long commodityId;
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
	 * 审核状态
	 */
	@ApiModelProperty(required=false,value="审核状态")
	private Integer reviewStatus;

	/**
	 * 审核状态
	 */
	@ApiModelProperty(required=false,value="审核状态")
	@TableField(exist = false)
	private String reviewStatusStr;
	/**
	 * 审核商品信息
	 */
	@ApiModelProperty(required=false,value="审核商品信息")
	@TableField(exist = false)
	private CellarCommodityDbEntity cellarCommodityDbEntity;

	/**
	 * 设置：精选商品id
	 */
	public void setHandpickCommodityReviewId(Long handpickCommodityReviewId) {
		this.handpickCommodityReviewId = handpickCommodityReviewId;
	}
	/**
	 * 获取：精选商品id
	 */
	public Long getHandpickCommodityReviewId() {
		return handpickCommodityReviewId;
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
	 * 设置：审核状态
	 */
	public void setReviewStatus(Integer reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	/**
	 * 获取：审核状态
	 */
	public Integer getReviewStatus() {
		return reviewStatus;
	}

	public String getReviewStatusStr() {
		if (ObjectUtil.isNotNull(reviewStatus)) {
			this.reviewStatusStr = Constants.REVIEWSTATUS.getValueByKey(reviewStatus);
		}
		return reviewStatusStr;
	}

	public Long[] getHandpickCommodityReviewIds() {
		return handpickCommodityReviewIds;
	}

	public void setHandpickCommodityReviewIds(Long[] handpickCommodityReviewIds) {
		this.handpickCommodityReviewIds = handpickCommodityReviewIds;
	}

	public CellarCommodityDbEntity getCellarCommodityDbEntity() {
		if (ObjectUtil.isNotNull(commodityId)) {
			CellarCommodityDbService cellarCommodityDbService = SpringContextUtils.getBean(CellarCommodityDbService.class);
			this.cellarCommodityDbEntity = cellarCommodityDbService.getById(this.commodityId);
		}
		return cellarCommodityDbEntity;
	}

	public void setCellarCommodityDbEntity(CellarCommodityDbEntity cellarCommodityDbEntity) {
		this.cellarCommodityDbEntity = cellarCommodityDbEntity;
	}
}
