package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.sys.entity.AbstractEntity;
import io.renren.modules.sys.service.SysAreaService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员地址表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-26 10:56:54
 */
@TableName("cellar_member_address_db")
@ApiModel("会员地址表")
@Data
public class CellarMemberAddressDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 地址id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="地址id")
	private Long addressId;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
	/**
	 * 联系人
	 */
	@ApiModelProperty(required=false,value="联系人")
	private String contact;
	/**
	 * 联系电话
	 */
	@ApiModelProperty(required=false,value="联系电话")
	private String contactPhone;
	/**
	 * 省id
	 */
	@ApiModelProperty(required=false,value="省id")
	private Long provinceId;
	/**
	 * 省名称
	 */
	@ApiModelProperty(required=false,value="省名称")
	private String provinceName;
	/**
	 * 市id
	 */
	@ApiModelProperty(required=false,value="市id")
	private Long cityId;
	/**
	 * 市名称
	 */
	@ApiModelProperty(required=false,value="市名称")
	private String cityName;
	/**
	 * 县id
	 */
	@ApiModelProperty(required=false,value="县id")
	private Long countyId;
	/**
	 * 县名称
	 */
	@ApiModelProperty(required=false,value="县名称")
	private String countyName;
	/**
	 * 详细地址
	 */
	@ApiModelProperty(required=false,value="详细地址")
	private String detailedAddress;
	/**
	 * 地图地址
	 */
	@ApiModelProperty(required=false,value="地图地址")
	private String mapAddress;
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
	 * 是否默认 1 默认 0 非默认
	 */
	@ApiModelProperty(required=false,value="是否默认 1 默认 0 非默认")
	private Integer isDefault;
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
	 * 设置：地址id
	 */
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	/**
	 * 获取：地址id
	 */
	public Long getAddressId() {
		return addressId;
	}
	/**
	 * 设置：会员id
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：会员id
	 */
	public Long getMemberId() {
		return memberId;
	}
	/**
	 * 设置：联系人
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * 获取：联系人
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * 设置：联系电话
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * 设置：省id
	 */
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取：省id
	 */
	public Long getProvinceId() {
		return provinceId;
	}
	/**
	 * 设置：省名称
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	/**
	 * 获取：省名称
	 */
	public String getProvinceName() {
		if (StringUtils.isBlank(provinceName) && ObjectUtil.isNotNull(provinceId)) {
			SysAreaService sysAreaService = SpringContextUtils.getBean(SysAreaService.class);
			return sysAreaService.getById(provinceId).getAreaName();
		}
		return provinceName;
	}
	/**
	 * 设置：市id
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取：市id
	 */
	public Long getCityId() {
		return cityId;
	}
	/**
	 * 设置：市名称
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * 获取：市名称
	 */
	public String getCityName() {
		if (StringUtils.isBlank(cityName) && ObjectUtil.isNotNull(cityId)) {
			SysAreaService sysAreaService = SpringContextUtils.getBean(SysAreaService.class);
			return sysAreaService.getById(cityId).getAreaName();
		}
		return cityName;
	}
	/**
	 * 设置：县id
	 */
	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}
	/**
	 * 获取：县id
	 */
	public Long getCountyId() {
		return countyId;
	}
	/**
	 * 设置：县名称
	 */
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	/**
	 * 获取：县名称
	 */
	public String getCountyName() {
		if (StringUtils.isBlank(countyName) && ObjectUtil.isNotNull(countyId)) {
			SysAreaService sysAreaService = SpringContextUtils.getBean(SysAreaService.class);
			return sysAreaService.getById(countyId).getAreaName();
		}
		return countyName;
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
		this.state = state;
	}
	/**
	 * 获取：状态
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * 设置：是否默认 1 默认 0 非默认
	 */
	public void setIsDefault(Integer idDefault) {
		this.isDefault = idDefault;
	}
	/**
	 * 获取：是否默认 1 默认 0 非默认
	 */
	public Integer getIsDefault() {
		return isDefault;
	}
}
