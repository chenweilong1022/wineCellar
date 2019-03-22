package io.renren.modules.cellar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 分类活动表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-21 10:06:01
 */
@TableName("cellar_category_activity_db")
@ApiModel("分类活动表")
public class CellarCategoryActivityDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类活动id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="分类活动id")
	private Long categoryActivityId;
	/**
	 * 活动标题
	 */
	@ApiModelProperty(required=false,value="活动标题")
	private String activityTitle;
	/**
	 * 活动描述
	 */
	@ApiModelProperty(required=false,value="活动描述")
	private String activityDescription;
	/**
	 * 活动图片
	 */
	@ApiModelProperty(required=false,value="活动图片")
	private String activityPhotos;
	/**
	 * 活动背景图
	 */
	@ApiModelProperty(required=false,value="活动背景图")
	private String activityBackgroundMap;
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
	/**
	 * 设置：活动标题
	 */
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	/**
	 * 获取：活动标题
	 */
	public String getActivityTitle() {
		return activityTitle;
	}
	/**
	 * 设置：活动描述
	 */
	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}
	/**
	 * 获取：活动描述
	 */
	public String getActivityDescription() {
		return activityDescription;
	}
	/**
	 * 设置：活动图片
	 */
	public void setActivityPhotos(String activityPhotos) {
		this.activityPhotos = activityPhotos;
	}
	/**
	 * 获取：活动图片
	 */
	public String getActivityPhotos() {
		return activityPhotos;
	}
	/**
	 * 设置：活动背景图
	 */
	public void setActivityBackgroundMap(String activityBackgroundMap) {
		this.activityBackgroundMap = activityBackgroundMap;
	}
	/**
	 * 获取：活动背景图
	 */
	public String getActivityBackgroundMap() {
		return activityBackgroundMap;
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
