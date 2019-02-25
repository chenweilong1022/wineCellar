package io.renren.modules.cellar.entity;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.service.CellarCategoryDbService;
import io.renren.modules.cellar.service.CellarStoreDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 酒窖类别表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-23 15:22:27
 */
@TableName("cellar_category_db")
@ApiModel("酒窖类别表")
public class CellarCategoryDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 类别id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="类别id")
	private Long categoryId;
	/**
	 * 类别名称
	 */
	@ApiModelProperty(required=false,value="类别名称")
	private String categoryName;
	/**
	 * 上级类别
	 */
	@ApiModelProperty(required=false,value="上级类别")
	private Long supCategoryId;
	/**
	 * 上级类别
	 */
	@ApiModelProperty(required=false,value="上级类别")
	@TableField(exist = false)
	private Long[] supCategoryIdList;
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
	 * 店铺名称
	 */
	@ApiModelProperty(required=false,value="店铺名称")
	@TableField(exist = false)
	private String storeName;
	/**
	 * 级别
	 */
	@ApiModelProperty(required=false,value="级别")
	private Integer level;
	/**
	 * 级别
	 */
	@ApiModelProperty(required=false,value="路径")
	private String path;
	/**
	 * 级别
	 */
	@ApiModelProperty(required=false,value="下级类别集合")
	@TableField(exist = false)
	private List<CellarCategoryDbEntity> cellarCategoryDbEntities;

	/**
	 * 设置：类别id
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * 获取：类别id
	 */
	public Long getCategoryId() {
		return categoryId;
	}
	/**
	 * 设置：类别名称
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * 获取：类别名称
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * 设置：上级类别
	 */
	public void setSupCategoryId(Long supCategoryId) {
		this.supCategoryId = supCategoryId;
	}
	/**
	 * 获取：上级类别
	 */
	public Long getSupCategoryId() {
		return supCategoryId;
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
	/**
	 * 设置：级别
	 */
	public void setLevel(Integer level) {
		if (this.level == null) {
			this.level = level;
		}
	}
	/**
	 * 获取：级别
	 */
	public Integer getLevel() {
		return level;
	}

	public List<CellarCategoryDbEntity> getCellarCategoryDbEntities() {
		return cellarCategoryDbEntities;
	}

	public void setCellarCategoryDbEntities(List<CellarCategoryDbEntity> cellarCategoryDbEntities) {
		this.cellarCategoryDbEntities = cellarCategoryDbEntities;
	}

	public Long[] getSupCategoryIdList() {
		if (path != null) {
			this.supCategoryIdList = com.alibaba.fastjson.JSONArray.parseArray(path).toJavaObject(Long[].class);
		}
		return supCategoryIdList;
	}

	public void setSupCategoryIdList(Long[] supCategoryIdList) {
		this.supCategoryIdList = supCategoryIdList;
		if (supCategoryIdList != null && supCategoryIdList.length > 0) {
			this.supCategoryId = supCategoryIdList[supCategoryIdList.length - 1];
			this.level = supCategoryIdList.length;
			this.path = new JSONArray(supCategoryIdList).toString();
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		if (path != null) {
			this.path = path;
		}
	}

	public String getStoreName() {
		if (storeId != null) {
			CellarStoreDbService cellarStoreDbService = SpringContextUtils.getBean(CellarStoreDbService.class);
			CellarStoreDbEntity cellarStoreDbEntity = cellarStoreDbService.getById(storeId);
			return cellarStoreDbEntity == null?"":cellarStoreDbEntity.getStoreName();
		}
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
