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
 * 首页搜索记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-22 16:25:21
 */
@TableName("cellar_home_page_search_record_db")
@ApiModel("首页搜索记录表")
public class CellarHomePageSearchRecordDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 首页搜索记录id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="首页搜索记录id")
	private Long homePageSearchRecordId;
	/**
	 * 搜索关键字
	 */
	@ApiModelProperty(required=false,value="搜索关键字")
	private String searchKeywords;
	/**
	 * 搜索次数
	 */
	@ApiModelProperty(required=false,value="搜索次数")
	private Integer searchNumber;
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
	 * 更新时间
	 */
	@ApiModelProperty(required=false,value="更新时间")
	private Date updateTime;

	/**
	 * 设置：首页搜索记录id
	 */
	public void setHomePageSearchRecordId(Long homePageSearchRecordId) {
		this.homePageSearchRecordId = homePageSearchRecordId;
	}
	/**
	 * 获取：首页搜索记录id
	 */
	public Long getHomePageSearchRecordId() {
		return homePageSearchRecordId;
	}
	/**
	 * 设置：搜索关键字
	 */
	public void setSearchKeywords(String searchKeywords) {
		this.searchKeywords = searchKeywords;
	}
	/**
	 * 获取：搜索关键字
	 */
	public String getSearchKeywords() {
		return searchKeywords;
	}
	/**
	 * 设置：搜索次数
	 */
	public void setSearchNumber(Integer searchNumber) {
		this.searchNumber = searchNumber;
	}
	/**
	 * 获取：搜索次数
	 */
	public Integer getSearchNumber() {
		return searchNumber;
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
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
