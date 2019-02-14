package io.renren.modules.cellar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.constants.Constants;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 酒窖会员表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-01-16 17:34:20
 */
@TableName("cellar_member_db")
@ApiModel("酒窖会员表")
public class CellarMemberDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
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
	 * 状态中文描述
	 */
	@ApiModelProperty(required=false,value="状态中文描述",hidden = true)
	@TableField(exist = false)
	private String stateStr;
	/**
	 * 等级
	 */
	@ApiModelProperty(required=false,value="等级")
	private Integer level;
	/**
	 * 头像
	 */
	@ApiModelProperty(required=false,value="头像")
	private String headPortrait;
	/**
	 * 昵称
	 */
	@ApiModelProperty(required=false,value="昵称")
	private String nickname;
	/**
	 * 性别
	 */
	@ApiModelProperty(required=false,value="性别")
	private Integer gender;
	/**
	 * 性别中文描述
	 */
	@ApiModelProperty(required=false,value="性别中文描述",hidden = true)
	@TableField(exist = false)
	private String genderStr;
	/**
	 * 生日
	 */
	@ApiModelProperty(required=false,value="生日")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	/**
	 * 手机号
	 */
	@ApiModelProperty(required=false,value="手机号")
	private String mobilePhone;
	/**
	 * 登录密码
	 */
	@ApiModelProperty(required=false,value="登录密码")
	private String password;
	/**
	 * 支付密码
	 */
	@ApiModelProperty(required=false,value="支付密码")
	private String payPassword;
	/**
	 * 登录token
	 */
	@ApiModelProperty(required=false,value="登录token")
	private String loginToken;
	/**
	 * 微信openid
	 */
	@ApiModelProperty(required=false,value="微信openid")
	private String openid;
	/**
	 * 余额
	 */
	@ApiModelProperty(required=false,value="余额")
	private BigDecimal balance;
	/**
	 * 积分
	 */
	@ApiModelProperty(required=false,value="积分")
	private BigDecimal integral;
	/**
	 * 个性签名
	 */
	@ApiModelProperty(required=false,value="个性签名")
	private String individualitySignature;
	/**
	 * 储值卡余额
	 */
	@ApiModelProperty(required=false,value="储值卡余额")
	private BigDecimal cardBalance;

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
	 * 设置：等级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * 获取：等级
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * 设置：头像
	 */
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	/**
	 * 获取：头像
	 */
	public String getHeadPortrait() {
		return headPortrait;
	}
	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：性别
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别
	 */
	public Integer getGender() {
		return gender;
	}
	/**
	 * 设置：生日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：生日
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	/**
	 * 设置：登录密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：登录密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：支付密码
	 */
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	/**
	 * 获取：支付密码
	 */
	public String getPayPassword() {
		return payPassword;
	}
	/**
	 * 设置：登录token
	 */
	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}
	/**
	 * 获取：登录token
	 */
	public String getLoginToken() {
		return loginToken;
	}
	/**
	 * 设置：微信openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取：微信openid
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * 设置：余额
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	/**
	 * 获取：余额
	 */
	public BigDecimal getBalance() {
		return balance;
	}
	/**
	 * 设置：积分
	 */
	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}
	/**
	 * 获取：积分
	 */
	public BigDecimal getIntegral() {
		return integral;
	}
	/**
	 * 设置：个性签名
	 */
	public void setIndividualitySignature(String individualitySignature) {
		this.individualitySignature = individualitySignature;
	}
	/**
	 * 获取：个性签名
	 */
	public String getIndividualitySignature() {
		return individualitySignature;
	}
	/**
	 * 设置：储值卡余额
	 */
	public void setCardBalance(BigDecimal cardBalance) {
		this.cardBalance = cardBalance;
	}
	/**
	 * 获取：储值卡余额
	 */
	public BigDecimal getCardBalance() {
		return cardBalance;
	}

	public String getStateStr() {
		if (state != null) {
			return Constants.STATE.getValueByKey(state);
		}
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public String getGenderStr() {
		if (gender != null) {
			return Constants.GENDER.getValueByKey(gender);
		}
		return "保密";
	}

	public void setGenderStr(String genderStr) {
		this.genderStr = genderStr;
	}
}
