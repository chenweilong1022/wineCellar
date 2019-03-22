package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.constants.Constants;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.service.CellarCategoryActivityDbService;
import io.renren.modules.cellar.service.CellarCommodityDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 分类活动商品审核表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-21 13:43:20
 */
@TableName("cellar_category_activity_commodity_review_db")
@ApiModel("分类活动商品审核表")
public class CellarCategoryActivityCommodityReviewDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类活动商品审核id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="分类活动商品审核id")
	private Long categoryActivityCommodityReviewId;
	/**
	 * 分类活动商品审核id
	 */
	@ApiModelProperty(required=false,value="分类活动商品审核id")
	@TableField(exist = false)
	private Long[] categoryActivityCommodityReviewIds;
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
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date createTime;
	/**
	 * 分类活动id
	 */
	@ApiModelProperty(required=false,value="分类活动id")
	private Long categoryActivityId;
	/**
	 * 分类活动
	 */
	@ApiModelProperty(required=false,value="分类活动")
	@TableField(exist = false)
	private CellarCategoryActivityDbEntity cellarCategoryActivityDbEntity;
	/**
	 * 审核商品信息
	 */
	@ApiModelProperty(required=false,value="审核商品信息")
	@TableField(exist = false)
	private CellarCommodityDbEntity cellarCommodityDbEntity;

	/**
	 * 设置：分类活动商品审核id
	 */
	public void setCategoryActivityCommodityReviewId(Long categoryActivityCommodityReviewId) {
		this.categoryActivityCommodityReviewId = categoryActivityCommodityReviewId;
	}
	/**
	 * 获取：分类活动商品审核id
	 */
	public Long getCategoryActivityCommodityReviewId() {
		return categoryActivityCommodityReviewId;
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
	 * 设置：分类活动id
	 */
	public void setCategoryActivityId(Long categoryActivityId) {
		this.categoryActivityId = categoryActivityId;
	}
	/**
	 * 获取：分类活动id
	 */
	public Long getCategoryActivityId() {
		return categoryActivityId;
	}

	public Long[] getCategoryActivityCommodityReviewIds() {
		return categoryActivityCommodityReviewIds;
	}

	public void setCategoryActivityCommodityReviewIds(Long[] categoryActivityCommodityReviewIds) {
		this.categoryActivityCommodityReviewIds = categoryActivityCommodityReviewIds;
	}

	public String getReviewStatusStr() {
		if (ObjectUtil.isNotNull(reviewStatus)) {
			this.reviewStatusStr = Constants.REVIEWSTATUS.getValueByKey(reviewStatus);
		}
		return reviewStatusStr;
	}

	public CellarCommodityDbEntity getCellarCommodityDbEntity() {
		if (ObjectUtil.isNotNull(commodityId)) {
			CellarCommodityDbService cellarCommodityDbService = SpringContextUtils.getBean(CellarCommodityDbService.class);
			this.cellarCommodityDbEntity = cellarCommodityDbService.getById(this.commodityId);
		}
		return cellarCommodityDbEntity;
	}

	public CellarCategoryActivityDbEntity getCellarCategoryActivityDbEntity() {
		if (ObjectUtil.isNotNull(categoryActivityId)) {
			CellarCategoryActivityDbService cellarCategoryActivityDbService = SpringContextUtils.getBean(CellarCategoryActivityDbService.class);
			this.cellarCategoryActivityDbEntity = cellarCategoryActivityDbService.getById(this.categoryActivityId);
		}
		return cellarCategoryActivityDbEntity;
	}
}
