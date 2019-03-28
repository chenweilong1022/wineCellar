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
 * 产品入驻表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-27 14:36:40
 */
@TableName("cellar_commodity_entry_db")
@ApiModel("产品入驻表")
public class CellarCommodityEntryDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 产品入驻id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="产品入驻id")
	private Long commodityEntryId;
	/**
	 * 产品入驻id
	 */
	@ApiModelProperty(required=false,value="产品入驻id")
	@TableField(exist = false)
	private Long[] commodityEntryIds;
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
	 * 产品图片
	 */
	@ApiModelProperty(required=false,value="产品图片")
	private String commodityPictures;
	/**
	 * 产品图片
	 */
	@ApiModelProperty(required=false,value="产品图片")
	@TableField(exist = false)
	private String[] commodityPicturesList;
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
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
	/**
	 * 提交会员id
	 */
	@ApiModelProperty(required=false,value="提交会员id")
	@TableField(exist = false)
	private CellarMemberDbEntity cellarMemberDbEntity;

	/**
	 * 设置：产品入驻id
	 */
	public void setCommodityEntryId(Long commodityEntryId) {
		this.commodityEntryId = commodityEntryId;
	}
	/**
	 * 获取：产品入驻id
	 */
	public Long getCommodityEntryId() {
		return commodityEntryId;
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
	 * 设置：产品图片
	 */
	public void setCommodityPictures(String commodityPictures) {
		this.commodityPictures = commodityPictures;
	}
	/**
	 * 获取：产品图片
	 */
	public String getCommodityPictures() {
		if (commodityPicturesList!= null) {
			this.commodityPictures = new JSONArray(commodityPicturesList).toString();
		}
		return commodityPictures;
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

	public String[] getCommodityPicturesList() {
		if (StringUtils.isNotBlank(this.commodityPictures)) {
			this.commodityPicturesList = com.alibaba.fastjson.JSONArray.parseArray(commodityPictures).toJavaObject(String[].class);
		}
		return commodityPicturesList;
	}

	public void setCommodityPicturesList(String[] commodityPicturesList) {
		this.commodityPicturesList = commodityPicturesList;
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

	public Long[] getCommodityEntryIds() {
		return commodityEntryIds;
	}

	public void setCommodityEntryIds(Long[] commodityEntryIds) {
		this.commodityEntryIds = commodityEntryIds;
	}
}
