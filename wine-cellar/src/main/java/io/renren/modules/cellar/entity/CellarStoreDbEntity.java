package io.renren.modules.cellar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.common.constants.Constants;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.sys.entity.AbstractEntity;
import io.renren.modules.sys.entity.SysAreaEntity;
import io.renren.modules.sys.service.SysAreaService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

/**
 * 酒窖店铺表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
@TableName("cellar_store_db")
@ApiModel("酒窖店铺表")
public class CellarStoreDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 店铺id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="店铺id")
	private Long storeId;
	/**
	 * 店铺名称
	 */
	@ApiModelProperty(required=false,value="店铺名称")
	private String storeName;
	/**
	 * 省
	 */
	@ApiModelProperty(required=false,value="省")
	private Long provinceId;
	/**
	 * 市
	 */
	@ApiModelProperty(required=false,value="市")
	private Long cityId;
	/**
	 * 区
	 */
	@ApiModelProperty(required=false,value="区")
	private Long areaId;
	/**
	 * 省
	 */
	@ApiModelProperty(required=false,value="省")
	@TableField(exist = false)
	private String provinceName;
	/**
	 * 市
	 */
	@ApiModelProperty(required=false,value="市")
	@TableField(exist = false)
	private String cityName;
	/**
	 * 区
	 */
	@ApiModelProperty(required=false,value="区")
	@TableField(exist = false)
	private String areaName;
	/**
	 * 详细地址
	 */
	@ApiModelProperty(required=false,value="详细地址")
	private String detailedAddress;
	/**
	 * 店铺折扣
	 */
	@ApiModelProperty(required=false,value="店铺折扣")
	private BigDecimal storeDiscounts;
	/**
	 * 支持自取
	 */
	@ApiModelProperty(required=false,value="支持自取")
	private Integer supportTheirOwn;
	/**
	 * 支持自取
	 */
	@ApiModelProperty(required=false,value="支持自取")
	@TableField(exist = false)
	private String supportTheirOwnStr;
	/**
	 * 满
	 */
	@ApiModelProperty(required=false,value="满")
	private BigDecimal full;
	/**
	 * 减
	 */
	@ApiModelProperty(required=false,value="减")
	private BigDecimal reductionOf;
	/**
	 * 店铺背景图
	 */
	@ApiModelProperty(required=false,value="店铺背景图")
	private String storeBackgroundMap;
	/**
	 * 店铺头像图
	 */
	@ApiModelProperty(required=false,value="店铺头像图")
	private String storeHeadPicture;
	/**
	 * 经度
	 */
	@ApiModelProperty(required=false,value="经度")
	private String longitude;
	/**
	 * 纬度
	 */
	@ApiModelProperty(required=false,value="纬度")
	private String latitude;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date createTime;
	/**
	 * 状态
	 */
	@ApiModelProperty(required=false,value="状态")
	private Integer state;
	/**
	 * 距离
	 */
	@ApiModelProperty(required=false,value="距离")
	@TableField(exist = false)
	private BigDecimal distance;
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
	 * 设置：店铺名称
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	/**
	 * 获取：店铺名称
	 */
	public String getStoreName() {
		return storeName;
	}
	/**
	 * 设置：省
	 */
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取：省
	 */
	public Long getProvinceId() {
		return provinceId;
	}
	/**
	 * 设置：市
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取：市
	 */
	public Long getCityId() {
		return cityId;
	}
	/**
	 * 设置：区
	 */
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	/**
	 * 获取：区
	 */
	public Long getAreaId() {
		return areaId;
	}
	/**
	 * 设置：详细地址
	 */
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	/**
	 * 获取：详细地址
	 */
	public String getDetailedAddress() {
		return detailedAddress;
	}
	/**
	 * 设置：店铺折扣
	 */
	public void setStoreDiscounts(BigDecimal storeDiscounts) {
		this.storeDiscounts = storeDiscounts;
	}
	/**
	 * 获取：店铺折扣
	 */
	public BigDecimal getStoreDiscounts() {
		return storeDiscounts;
	}
	/**
	 * 设置：支持自取
	 */
	public void setSupportTheirOwn(Integer supportTheirOwn) {
		this.supportTheirOwn = supportTheirOwn;
	}
	/**
	 * 获取：支持自取
	 */
	public Integer getSupportTheirOwn() {
		return supportTheirOwn;
	}
	/**
	 * 设置：满
	 */
	public void setFull(BigDecimal full) {
		this.full = full;
	}
	/**
	 * 获取：满
	 */
	public BigDecimal getFull() {
		return full;
	}
	/**
	 * 设置：减
	 */
	public void setReductionOf(BigDecimal reductionOf) {
		this.reductionOf = reductionOf;
	}
	/**
	 * 获取：减
	 */
	public BigDecimal getReductionOf() {
		return reductionOf;
	}
	/**
	 * 设置：店铺背景图
	 */
	public void setStoreBackgroundMap(String storeBackgroundMap) {
		this.storeBackgroundMap = storeBackgroundMap;
	}
	/**
	 * 获取：店铺背景图
	 */
	public String getStoreBackgroundMap() {
		return storeBackgroundMap;
	}
	/**
	 * 设置：店铺头像图
	 */
	public void setStoreHeadPicture(String storeHeadPicture) {
		this.storeHeadPicture = storeHeadPicture;
	}
	/**
	 * 获取：店铺头像图
	 */
	public String getStoreHeadPicture() {
		return storeHeadPicture;
	}
	/**
	 * 设置：经度
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取：经度
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * 设置：纬度
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * 获取：纬度
	 */
	public String getLatitude() {
		return latitude;
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

	public String getProvinceName() {
		if (provinceId != null) {
			SysAreaService sysAreaService = SpringContextUtils.getBean(SysAreaService.class);

			SysAreaEntity sysAreaEntity = sysAreaService.getOne(new QueryWrapper<SysAreaEntity>().lambda().
					eq(SysAreaEntity::getId, provinceId));

			return sysAreaEntity == null?null:sysAreaEntity.getAreaName();
		}
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		if (cityId != null) {
			SysAreaService sysAreaService = SpringContextUtils.getBean(SysAreaService.class);

			SysAreaEntity sysAreaEntity = sysAreaService.getOne(new QueryWrapper<SysAreaEntity>().lambda().
					eq(SysAreaEntity::getId, cityId));

			return sysAreaEntity == null?null:sysAreaEntity.getAreaName();
		}
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		if (areaId != null) {
			SysAreaService sysAreaService = SpringContextUtils.getBean(SysAreaService.class);

			SysAreaEntity sysAreaEntity = sysAreaService.getOne(new QueryWrapper<SysAreaEntity>().lambda().
					eq(SysAreaEntity::getId, areaId));

			return sysAreaEntity == null?null:sysAreaEntity.getAreaName();
		}
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getSupportTheirOwnStr() {
		if (supportTheirOwn != null) {
			return Constants.SUPPORTTHEIROWN.getValueByKey(supportTheirOwn);
		}
		return supportTheirOwnStr;
	}

	public void setSupportTheirOwnStr(String supportTheirOwnStr) {
		this.supportTheirOwnStr = supportTheirOwnStr;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}
}
