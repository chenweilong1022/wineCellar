package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.constants.Constants;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.service.CellarMemberDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 酒窖加盟表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-27 13:25:34
 */
@TableName("cellar_franchise_db")
@ApiModel("酒窖加盟表")
public class CellarFranchiseDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 酒窖加盟id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="酒窖加盟id")
	private Long franchiseId;
	/**
	 * 酒窖加盟id
	 */
	@ApiModelProperty(required=false,value="酒窖加盟id")
	@TableField(exist = false)
	private Long[] franchiseIds;
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
	 * 公司名称
	 */
	@ApiModelProperty(required=false,value="公司名称")
	private String companyName;
	/**
	 * 产品品牌
	 */
	@ApiModelProperty(required=false,value="产品品牌")
	private String commodityBrand;
	/**
	 * 联系人
	 */
	@ApiModelProperty(required=false,value="联系人")
	private String contact;
	/**
	 * 联系号码
	 */
	@ApiModelProperty(required=false,value="联系号码")
	private String contactMobilePhone;
	/**
	 * 店铺门面照片
	 */
	@ApiModelProperty(required=false,value="店铺门面照片")
	private String shopFrontPhoto;
	/**
	 * 店铺面积照片
	 */
	@ApiModelProperty(required=false,value="店铺面积照片")
	private String shopSizePhoto;
	/**
	 * 店铺装修照片
	 */
	@ApiModelProperty(required=false,value="店铺装修照片")
	private String shopDecorationPhotos;
	/**
	 * 店铺门面照片
	 */
	@ApiModelProperty(required=false,value="店铺门面照片")
	@TableField(exist = false)
	private String[] shopFrontPhotoList;
	/**
	 * 店铺面积照片
	 */
	@ApiModelProperty(required=false,value="店铺面积照片")
	@TableField(exist = false)
	private String[] shopSizePhotoList;
	/**
	 * 店铺装修照片
	 */
	@ApiModelProperty(required=false,value="店铺装修照片")
	@TableField(exist = false)
	private String[] shopDecorationPhotosList;
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
	 * 提交会员id
	 */
	@ApiModelProperty(required=false,value="提交会员id")
	private Long memberId;
	/**
	 * 提交会员id
	 */
	@ApiModelProperty(required=false,value="提交会员id")
	@TableField(exist = false)
	private CellarMemberDbEntity cellarMemberDbEntity;

	/**
	 * 设置：酒窖加盟id
	 */
	public void setFranchiseId(Long franchiseId) {
		this.franchiseId = franchiseId;
	}
	/**
	 * 获取：酒窖加盟id
	 */
	public Long getFranchiseId() {
		return franchiseId;
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
	 * 设置：公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 获取：公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置：产品品牌
	 */
	public void setCommodityBrand(String commodityBrand) {
		this.commodityBrand = commodityBrand;
	}
	/**
	 * 获取：产品品牌
	 */
	public String getCommodityBrand() {
		return commodityBrand;
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
	 * 设置：联系号码
	 */
	public void setContactMobilePhone(String contactMobilePhone) {
		this.contactMobilePhone = contactMobilePhone;
	}
	/**
	 * 获取：联系号码
	 */
	public String getContactMobilePhone() {
		return contactMobilePhone;
	}
	/**
	 * 设置：店铺门面照片
	 */
	public void setShopFrontPhoto(String shopFrontPhoto) {
		this.shopFrontPhoto = shopFrontPhoto;
	}
	/**
	 * 获取：店铺门面照片
	 */
	public String getShopFrontPhoto() {
		if (shopFrontPhotoList!= null) {
			this.shopFrontPhoto = new JSONArray(shopFrontPhotoList).toString();
		}
		return shopFrontPhoto;
	}
	/**
	 * 设置：店铺面积照片
	 */
	public void setShopSizePhoto(String shopSizePhoto) {
		this.shopSizePhoto = shopSizePhoto;
	}
	/**
	 * 获取：店铺面积照片
	 */
	public String getShopSizePhoto() {
		if (shopSizePhotoList!= null) {
			this.shopSizePhoto = new JSONArray(shopSizePhotoList).toString();
		}
		return shopSizePhoto;
	}
	/**
	 * 设置：店铺装修照片
	 */
	public void setShopDecorationPhotos(String shopDecorationPhotos) {
		this.shopDecorationPhotos = shopDecorationPhotos;
	}
	/**
	 * 获取：店铺装修照片
	 */
	public String getShopDecorationPhotos() {
		if (shopDecorationPhotosList!= null) {
			this.shopDecorationPhotos = new JSONArray(shopDecorationPhotosList).toString();
		}
		return shopDecorationPhotos;
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
	 * 设置：提交会员id
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：提交会员id
	 */
	public Long getMemberId() {
		return memberId;
	}

	public String[] getShopFrontPhotoList() {
		if (StringUtils.isNotBlank(this.shopFrontPhoto)) {
			this.shopFrontPhotoList = com.alibaba.fastjson.JSONArray.parseArray(shopFrontPhoto).toJavaObject(String[].class);
		}
		return shopFrontPhotoList;
	}

	public void setShopFrontPhotoList(String[] shopFrontPhotoList) {
		this.shopFrontPhotoList = shopFrontPhotoList;
	}

	public String[] getShopSizePhotoList() {
		if (StringUtils.isNotBlank(this.shopSizePhoto)) {
			this.shopSizePhotoList = com.alibaba.fastjson.JSONArray.parseArray(shopSizePhoto).toJavaObject(String[].class);
		}
		return shopSizePhotoList;
	}

	public void setShopSizePhotoList(String[] shopSizePhotoList) {
		this.shopSizePhotoList = shopSizePhotoList;
	}

	public String[] getShopDecorationPhotosList() {
		if (StringUtils.isNotBlank(this.shopDecorationPhotos)) {
			this.shopDecorationPhotosList = com.alibaba.fastjson.JSONArray.parseArray(shopDecorationPhotos).toJavaObject(String[].class);
		}
		return shopDecorationPhotosList;
	}

	public void setShopDecorationPhotosList(String[] shopDecorationPhotosList) {
		this.shopDecorationPhotosList = shopDecorationPhotosList;
	}

	public String getReviewStatusStr() {
		if (ObjectUtil.isNotNull(this.reviewStatus)) {
			this.reviewStatusStr = Constants.REVIEWSTATUS.getValueByKey(this.reviewStatus);
		}
		return reviewStatusStr;
	}

	public CellarMemberDbEntity getCellarMemberDbEntity() {
		if (ObjectUtil.isNotNull(this.memberId)) {
			CellarMemberDbService cellarMemberDbService = SpringContextUtils.getBean(CellarMemberDbService.class);
			this.cellarMemberDbEntity = cellarMemberDbService.getById(this.memberId);
		}
		return cellarMemberDbEntity;
	}

	public Long[] getFranchiseIds() {
		return franchiseIds;
	}

	public void setFranchiseIds(Long[] franchiseIds) {
		this.franchiseIds = franchiseIds;
	}
}
