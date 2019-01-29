package io.renren.modules.cellar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证码表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-01-29 14:59:19
 */
@TableName("cellar_member_captcha")
@ApiModel("验证码表")
public class CellarMemberCaptchaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 随机数
	 */
	@TableId(type = IdType.NONE)
	@ApiModelProperty(required=false,value="随机数")
	private String uuid;
	/**
	 * 验证码
	 */
	@ApiModelProperty(required=false,value="验证码")
	private String code;
	/**
	 * 过期时间
	 */
	@ApiModelProperty(required=false,value="过期时间")
	private Date expireTime;
	/**
	 * 手机号
	 */
	@ApiModelProperty(required=false,value="手机号")
	private String memberMobile;
	/**
	 * 密码
	 */
	@ApiModelProperty(required=false,value="密码")
	@TableField(exist = false)
	private String password;

	/**
	 * 设置：随机数
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * 获取：随机数
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * 设置：验证码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：验证码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：过期时间
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	/**
	 * 获取：过期时间
	 */
	public Date getExpireTime() {
		return expireTime;
	}
	/**
	 * 设置：手机号
	 */
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMemberMobile() {
		return memberMobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
