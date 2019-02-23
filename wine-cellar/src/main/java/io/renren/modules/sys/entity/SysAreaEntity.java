package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 地域信息表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 15:12:55
 */
@TableName("sys_area")
@ApiModel("地域信息表")
public class SysAreaEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="主键")
	private Integer id;
	/**
	 * 父级节点
	 */
	@ApiModelProperty(required=false,value="父级节点")
	private Integer pid;
	/**
	 * 地域路由
	 */
	@ApiModelProperty(required=false,value="地域路由")
	private String treePath;
	/**
	 * 地域编码
	 */
	@ApiModelProperty(required=false,value="地域编码")
	private String areaCode;
	/**
	 * 地域名称
	 */
	@ApiModelProperty(required=false,value="地域名称")
	private String areaName;
	/**
	 * 地区名称拼音
	 */
	@ApiModelProperty(required=false,value="地区名称拼音")
	private String areaPinyin;
	/**
	 * 银行地域支付编码
	 */
	@ApiModelProperty(required=false,value="银行地域支付编码")
	private String bankCode;
	/**
	 * 地区邮编
	 */
	@ApiModelProperty(required=false,value="地区邮编")
	private String zipcode;
	/**
	 * 电话区号
	 */
	@ApiModelProperty(required=false,value="电话区号")
	private String telCode;
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
	 * 下级集合
	 */
	@ApiModelProperty(required=false,value="下级集合",hidden = true)
	@TableField(exist = false)
	private List<SysAreaEntity> sysAreaEntities;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：父级节点
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 获取：父级节点
	 */
	public Integer getPid() {
		return pid;
	}
	/**
	 * 设置：地域路由
	 */
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	/**
	 * 获取：地域路由
	 */
	public String getTreePath() {
		return treePath;
	}
	/**
	 * 设置：地域编码
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * 获取：地域编码
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * 设置：地域名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * 获取：地域名称
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * 设置：地区名称拼音
	 */
	public void setAreaPinyin(String areaPinyin) {
		this.areaPinyin = areaPinyin;
	}
	/**
	 * 获取：地区名称拼音
	 */
	public String getAreaPinyin() {
		return areaPinyin;
	}
	/**
	 * 设置：银行地域支付编码
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	/**
	 * 获取：银行地域支付编码
	 */
	public String getBankCode() {
		return bankCode;
	}
	/**
	 * 设置：地区邮编
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * 获取：地区邮编
	 */
	public String getZipcode() {
		return zipcode;
	}
	/**
	 * 设置：电话区号
	 */
	public void setTelCode(String telCode) {
		this.telCode = telCode;
	}
	/**
	 * 获取：电话区号
	 */
	public String getTelCode() {
		return telCode;
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

	public List<SysAreaEntity> getSysAreaEntities() {
		return sysAreaEntities;
	}

	public void setSysAreaEntities(List<SysAreaEntity> sysAreaEntities) {
		this.sysAreaEntities = sysAreaEntities;
	}
}
