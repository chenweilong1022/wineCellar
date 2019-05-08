package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.constants.Constants;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.app.entity.NativeShareEntity;
import io.renren.modules.app.utils.NativeShareUtil;
import io.renren.modules.cellar.service.CellarCommodityDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 拼团活动表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-01 16:22:22
 */
@TableName("cellar_group_activity_db")
@ApiModel("拼团活动表")
public class CellarGroupActivityDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 拼团活动id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="拼团活动id")
	private Long groupActivityId;
	/**
	 * 商品id
	 */
	@ApiModelProperty(required=false,value="商品id")
	private Long commodityId;
	/**
	 * 审核商品信息
	 */
	@ApiModelProperty(required=false,value="审核商品信息")
	@TableField(exist = false)
	private CellarCommodityDbEntity cellarCommodityDbEntity;
	/**
	 * 拼团开始时间
	 */
	@ApiModelProperty(required=false,value="拼团开始时间")
	private Date groupStartTime;
	/**
	 * 拼团结束时间
	 */
	@ApiModelProperty(required=false,value="拼团结束时间")
	private Date groupEndTime;
	/**
	 * 拼团价
	 */
	@ApiModelProperty(required=false,value="拼团价")
	private BigDecimal groupPrice;
	/**
	 * 拼团总人数
	 */
	@ApiModelProperty(required=false,value="拼团总人数")
	private BigDecimal groupTotalNumber;
	/**
	 * 已拼团人数
	 */
	@ApiModelProperty(required=false,value="已拼团人数")
	private BigDecimal hasGroupNumber;
	/**
	 * 待拼团人数
	 */
	@ApiModelProperty(required=false,value="待拼团人数")
	private BigDecimal stayGroupNumber;
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
	 * 店铺id
	 */
	@ApiModelProperty(required=false,value="店铺id")
	private Long storeId;
	/**
	 * 剩余时间
	 */
	@ApiModelProperty(required=false,value="剩余时间")
	@TableField(exist = false)
	private Long timeRemaining;
	/**
	 * 分享url
	 */
	@ApiModelProperty(required=false,value="分享url")
	@TableField(exist = false)
	private String shareUrl;

	/**
	 * 设置：拼团活动id
	 */
	public void setGroupActivityId(Long groupActivityId) {
		this.groupActivityId = groupActivityId;
	}
	/**
	 * 获取：拼团活动id
	 */
	public Long getGroupActivityId() {
		return groupActivityId;
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
	 * 设置：拼团开始时间
	 */
	public void setGroupStartTime(Date groupStartTime) {
		this.groupStartTime = groupStartTime;
	}
	/**
	 * 获取：拼团开始时间
	 */
	public Date getGroupStartTime() {
		return groupStartTime;
	}
	/**
	 * 设置：拼团结束时间
	 */
	public void setGroupEndTime(Date groupEndTime) {
		this.groupEndTime = groupEndTime;
	}
	/**
	 * 获取：拼团结束时间
	 */
	public Date getGroupEndTime() {
		return groupEndTime;
	}
	/**
	 * 设置：拼团价
	 */
	public void setGroupPrice(BigDecimal groupPrice) {
		this.groupPrice = groupPrice;
	}
	/**
	 * 获取：拼团价
	 */
	public BigDecimal getGroupPrice() {
		return groupPrice;
	}
	/**
	 * 设置：拼团总人数
	 */
	public void setGroupTotalNumber(BigDecimal groupTotalNumber) {
		this.groupTotalNumber = groupTotalNumber;
	}
	/**
	 * 获取：拼团总人数
	 */
	public BigDecimal getGroupTotalNumber() {
		return groupTotalNumber;
	}
	/**
	 * 设置：已拼团人数
	 */
	public void setHasGroupNumber(BigDecimal hasGroupNumber) {
		this.hasGroupNumber = hasGroupNumber;
	}
	/**
	 * 获取：已拼团人数
	 */
	public BigDecimal getHasGroupNumber() {
		return hasGroupNumber;
	}
	/**
	 * 设置：待拼团人数
	 */
	public void setStayGroupNumber(BigDecimal stayGroupNumber) {
		this.stayGroupNumber = stayGroupNumber;
	}
	/**
	 * 获取：待拼团人数
	 */
	public BigDecimal getStayGroupNumber() {
		return stayGroupNumber;
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

	public CellarCommodityDbEntity getCellarCommodityDbEntity() {
		if (flag && ObjectUtil.isNotNull(commodityId)) {
			CellarCommodityDbService cellarCommodityDbService = SpringContextUtils.getBean(CellarCommodityDbService.class);
			this.cellarCommodityDbEntity = cellarCommodityDbService.getById(this.commodityId);
		}
		return cellarCommodityDbEntity;
	}

	public Long getTimeRemaining() {
		if (ObjectUtil.isNotNull(this.groupStartTime) && ObjectUtil.isNotNull(this.groupEndTime)) {
			this.timeRemaining = this.groupEndTime.getTime() - System.currentTimeMillis();
		}
		return timeRemaining;
	}

	public String getShareUrl() {
		if (ObjectUtil.isNotNull(this.storeId) && ObjectUtil.isNotNull(this.groupActivityId)) {
			NativeShareEntity nativeShareEntity = new NativeShareEntity();
			nativeShareEntity.setNativeShare(Constants.NATIVESHARE.FOUR.getKey());
			nativeShareEntity.setStoreId(this.storeId);
			nativeShareEntity.setGroupActivityId(this.groupActivityId);
			this.shareUrl = NativeShareUtil.shareUrl(nativeShareEntity);
		}
		return shareUrl;
	}
}
