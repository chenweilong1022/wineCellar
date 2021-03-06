package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.app.entity.NativeShareEntity;
import io.renren.modules.app.utils.NativeShareUtil;
import io.renren.modules.cellar.service.CellarMemberCollectionDbService;
import io.renren.modules.cellar.service.CellarStoreDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 酒窖商品表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
@TableName("cellar_commodity_db")
@ApiModel("酒窖商品表")
@Data
public class CellarCommodityDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="商品id")
	private Long commodityId;
	/**
	 * 商品名称
	 */
	@ApiModelProperty(required=false,value="商品名称")
	private String commodityName;
	/**
	 * 原价
	 */
	@ApiModelProperty(required=false,value="原价")
	private BigDecimal originalPrice;
	/**
	 * 现价
	 */
	@ApiModelProperty(required=false,value="现价")
	private BigDecimal presentPrice;
	/**
	 * 积分
	 */
	@ApiModelProperty(required=false,value="积分")
	private BigDecimal integral;
	/**
	 * 积分价格|酒币价格
	 */
	@ApiModelProperty(required=false,value="积分价格|酒币价格")
	private BigDecimal integralPrice;
	/**
	 * 图文详情
	 */
	@ApiModelProperty(required=false,value="图文详情")
	private byte[] graphicDetails;
	/**
	 * 图文详情
	 */
	@ApiModelProperty(required=false,value="图文详情")
	@TableField(exist = false)
	private String graphicDetailsStr;
	/**
	 * 图片
	 */
	@ApiModelProperty(required=false,value="图片")
	private String picture;
	/**
	 * 标签
	 */
	@ApiModelProperty(required=false,value="标签")
	private String label;
	/**
	 * 标签
	 */
	@ApiModelProperty(required=false,value="标签")
	@TableField(exist = false)
	private String[] labelList;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date creationTime;
	/**
	 * 状态
	 */
	@ApiModelProperty(required=false,value="状态")
	private Integer state;
	/**
	 * 月销量
	 */
	@ApiModelProperty(required=false,value="月销量")
	private BigDecimal monthSales;
	/**
	 * 总销量
	 */
	@ApiModelProperty(required=false,value="总销量")
	private BigDecimal totalSales;
	/**
	 * 库存量
	 */
	@ApiModelProperty(required=false,value="库存量")
	private BigDecimal inventory;
	/**
	 * 好评数
	 */
	@ApiModelProperty(required=false,value="好评数")
	private BigDecimal highPraise;
	/**
	 * 平均星数
	 */
	@ApiModelProperty(required=false,value="评价星数")
	private BigDecimal evaluationStarNumbers;
	/**
	 * 评价数量
	 */
	@ApiModelProperty(required=false,value="评价数量")
	private BigDecimal evaluationNumbers;
	/**
	 * 商品轮播图
	 */
	@ApiModelProperty(required=false,value="商品轮播图")
	private String commodityRotationChart;
	/**
	 * 商品轮播图数组
	 */
	@ApiModelProperty(required=false,value="商品轮播图数组")
	@TableField(exist = false)
	private String[] commodityRotationChartList;
	/**
	 * 商品规格
	 */
	@ApiModelProperty(required=false,value="商品规格")
	private String productSpecifications;
	/**
	 * 店铺id
	 */
	@ApiModelProperty(required=false,value="店铺id")
	private Long storeId;
	/**
	 * 分类id
	 */
	@ApiModelProperty(required=false,value="分类id")
	private Long categoryId;
	/**
	 * 分类id
	 */
	@ApiModelProperty(required=false,value="分类路径")
	private String categoryPath;
	/**
	 * 分类id
	 */
	@ApiModelProperty(required=false,value="分类路径")
	@TableField(exist = false)
	private Long[] categoryPathList;
	/**
	 * 是否精选
	 */
	@ApiModelProperty(required=false,value="是否精选")
	private Integer haveHandpick;
	/**
	 * 是否精选
	 */
	@ApiModelProperty(required=false,value="是否精选")
	@TableField(exist = false)
	private String haveHandpickStr;
	/**
	 * 分类活动id
	 */
	@ApiModelProperty(required=false,value="分类活动id")
	private Long categoryActivityId;
	/**
	 * 是否分类活动商品
	 */
	@ApiModelProperty(required=false,value="是否分类活动商品")
	private Integer haveCategoryActivity;
	/**
	 * 是否分类活动商品
	 */
	@ApiModelProperty(required=false,value="是否分类活动商品")
	@TableField(exist = false)
	private String haveCategoryActivityStr;
	/**
	 * 商品排序
	 */
	@ApiModelProperty(required=false,value="商品排序")
	@TableField(exist = false)
	private Integer sort;
	/**
	 * 是否收藏
	 */
	@ApiModelProperty(required=false,value="是否收藏")
	@TableField(exist = false)
	private Integer haveCollection;
	/**
	 * 用户id
	 */
	@ApiModelProperty(required=false,value="用户id")
	@TableField(exist = false)
	private Long memberId;
	/**
	 * 预售时间
	 */
	@TableField(strategy = FieldStrategy.IGNORED)
	@ApiModelProperty(required=false,value="预售时间")
	private Date presellTime;
	/**
	 * 分享url
	 */
	@ApiModelProperty(required=false,value="分享url")
	@TableField(exist = false)
	private String shareUrl;

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
	 * 设置：商品名称
	 */
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	/**
	 * 获取：商品名称
	 */
	public String getCommodityName() {
		return commodityName;
	}
	/**
	 * 设置：原价
	 */
	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
	/**
	 * 获取：原价
	 */
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}
	/**
	 * 设置：现价
	 */
	public void setPresentPrice(BigDecimal presentPrice) {
		this.presentPrice = presentPrice;
	}
	/**
	 * 获取：现价
	 */
	public BigDecimal getPresentPrice() {
		return presentPrice;
	}
	/**
	 * 设置：图文详情
	 */
	public void setGraphicDetails(byte[] graphicDetails) {
		this.graphicDetails = graphicDetails;
		if (graphicDetails != null) {
			this.graphicDetailsStr = new String(graphicDetails);
		}
	}
	/**
	 * 获取：图文详情
	 */
	public byte[] getGraphicDetails() {
		return graphicDetails;
	}
	/**
	 * 设置：图片
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	/**
	 * 获取：图片
	 */
	public String getPicture() {
		return picture;
	}
	/**
	 * 设置：标签
	 */
	public void setLabel(String label) {
		this.label = label;
		if (label != null) {
			this.labelList = com.alibaba.fastjson.JSONArray.parseArray(label).toJavaObject(String[].class);
		}
	}
	/**
	 * 获取：标签
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreationTime() {
		return creationTime;
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
	 * 设置：月销量
	 */
	public void setMonthSales(BigDecimal monthSales) {
		this.monthSales = monthSales;
	}
	/**
	 * 获取：月销量
	 */
	public BigDecimal getMonthSales() {
		return monthSales;
	}
	/**
	 * 设置：总销量
	 */
	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}
	/**
	 * 获取：总销量
	 */
	public BigDecimal getTotalSales() {
		return totalSales;
	}
	/**
	 * 设置：库存量
	 */
	public void setInventory(BigDecimal inventory) {
		this.inventory = inventory;
	}
	/**
	 * 获取：库存量
	 */
	public BigDecimal getInventory() {
		return inventory;
	}
	/**
	 * 设置：好评数
	 */
	public void setHighPraise(BigDecimal highPraise) {
		this.highPraise = highPraise;
	}
	/**
	 * 获取：好评数
	 */
	public BigDecimal getHighPraise() {
		return highPraise;
	}
	/**
	 * 设置：商品轮播图
	 */
	public void setCommodityRotationChart(String commodityRotationChart) {
		this.commodityRotationChart = commodityRotationChart;
	}
	/**
	 * 获取：商品轮播图
	 */
	public String getCommodityRotationChart() {
		return commodityRotationChart;
	}
	/**
	 * 设置：商品规格
	 */
	public void setProductSpecifications(String productSpecifications) {
		this.productSpecifications = productSpecifications;
	}
	/**
	 * 获取：商品规格
	 */
	public String getProductSpecifications() {
		return productSpecifications;
	}
	/**
	 * 设置：店铺id
	 */
	public void setStoreId(Long storeId) {
		super.setStoreName(storeId);
		this.storeId = storeId;
	}
	/**
	 * 获取：店铺id
	 */
	public Long getStoreId() {
		return storeId;
	}
	/**
	 * 设置：分类id
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * 获取：分类id
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	public String getGraphicDetailsStr() {
		return graphicDetailsStr;
	}

	public void setGraphicDetailsStr(String graphicDetailsStr) {
		this.graphicDetailsStr = graphicDetailsStr;
		if (graphicDetailsStr != null) {
			this.graphicDetails = graphicDetailsStr.getBytes();
		}
	}

	public String[] getLabelList() {
		return labelList;
	}

	public void setLabelList(String[] labelList) {
		this.labelList = labelList;
		if (labelList != null) {
			this.label = new JSONArray(labelList).toString();
		}
	}

	public String getCategoryPath() {
		return categoryPath;
	}

	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}

	public Long[] getCategoryPathList() {
		if (this.categoryPath != null) {
			this.categoryPathList = com.alibaba.fastjson.JSONArray.parseArray(categoryPath).toJavaObject(Long[].class);
		}
		return categoryPathList;
	}

	public void setCategoryPathList(Long[] categoryPathList) {
		this.categoryPathList = categoryPathList;
		if (categoryPathList != null && categoryPathList.length > 0) {
			this.categoryId = categoryPathList[categoryPathList.length - 1];
			this.categoryPath = new JSONArray(categoryPathList).toString();
		}
	}

	public String[] getCommodityRotationChartList() {
			if (StringUtils.isNotBlank(commodityRotationChart) && JSONUtil.isJson(commodityRotationChart)) {
				this.commodityRotationChartList = com.alibaba.fastjson.JSONArray.parseArray(commodityRotationChart).toJavaObject(String[].class);
			}


		return commodityRotationChartList;
	}

	public void setCommodityRotationChartList(String[] commodityRotationChartList) {
		this.commodityRotationChartList = commodityRotationChartList;
		if (commodityRotationChartList!= null) {
			this.commodityRotationChart = new JSONArray(commodityRotationChartList).toString();
		}
	}

	public BigDecimal getEvaluationStarNumbers() {
		return evaluationStarNumbers;
	}

	public void setEvaluationStarNumbers(BigDecimal evaluationStarNumbers) {
		this.evaluationStarNumbers = evaluationStarNumbers;
	}

	public BigDecimal getEvaluationNumbers() {
		return evaluationNumbers;
	}

	public void setEvaluationNumbers(BigDecimal evaluationNumbers) {
		this.evaluationNumbers = evaluationNumbers;
	}

	public Integer getHaveHandpick() {
		return haveHandpick;
	}

	public void setHaveHandpick(Integer haveHandpick) {
		this.haveHandpick = haveHandpick;
	}

	public Long getCategoryActivityId() {
		return categoryActivityId;
	}

	public void setCategoryActivityId(Long categoryActivityId) {
		this.categoryActivityId = categoryActivityId;
	}

	public Integer getHaveCategoryActivity() {
		return haveCategoryActivity;
	}

	public void setHaveCategoryActivity(Integer haveCategoryActivity) {
		this.haveCategoryActivity = haveCategoryActivity;
	}

	public String getHaveHandpickStr() {
		if (ObjectUtil.isNotNull(haveHandpick)) {
			this.haveHandpickStr = Constants.HAVEHANDPICK.getValueByKey(this.haveHandpick);
		}
		return haveHandpickStr;
	}

	public String getHaveCategoryActivityStr() {
		if (ObjectUtil.isNotNull(haveCategoryActivity)) {
			this.haveCategoryActivityStr = Constants.HAVEHANDPICK.getValueByKey(this.haveCategoryActivity);
		}
		return haveCategoryActivityStr;
	}

	public Integer getSort() {
		if (ObjectUtil.isNull(this.sort)) {
			this.sort = Constants.COMMODITYSORT.ONE.getKey();
		}
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getHaveCollection() {
		this.haveCollection = Constants.Number.zero.getKey();
		if (ObjectUtil.isNotNull(this.commodityId)) {
			CellarMemberCollectionDbService cellarMemberCollectionDbService = SpringContextUtils.getBean(CellarMemberCollectionDbService.class);
			this.haveCollection = cellarMemberCollectionDbService.count(new QueryWrapper<CellarMemberCollectionDbEntity>().lambda()
					.eq(CellarMemberCollectionDbEntity::getState, Constants.STATE.zero.getKey())
					.eq(CellarMemberCollectionDbEntity::getCommodityId, this.commodityId)
					.eq(CellarMemberCollectionDbEntity::getMemberId, this.memberId)
					.eq(CellarMemberCollectionDbEntity::getCollectionType, Constants.COLLECTIONTYPE.ONE.getKey())
			);
		}
		return haveCollection;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public Date getPresellTime() {
		return presellTime;
	}

	public void setPresellTime(Date presellTime) {
		this.presellTime = presellTime;
	}

	public String getShareUrl() {
		if (ObjectUtil.isNotNull(this.storeId) && ObjectUtil.isNotNull(this.commodityId)) {
			NativeShareEntity nativeShareEntity = new NativeShareEntity();
			nativeShareEntity.setNativeShare(Constants.NATIVESHARE.FIVE.getKey());
			nativeShareEntity.setStoreId(this.storeId);
			nativeShareEntity.setCommodityId(this.commodityId);
			this.shareUrl = NativeShareUtil.shareUrl(nativeShareEntity);
		}
		return shareUrl;
	}
}
