package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.service.CellarMemberDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员砍价记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-03 16:45:35
 */
@TableName("cellar_member_bargaining_record_db")
@ApiModel("会员砍价记录表")
public class CellarMemberBargainingRecordDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员砍价记录id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员砍价记录id")
	private Long memberBargainingRecordId;
	/**
	 * 会员砍价信息id
	 */
	@ApiModelProperty(required=false,value="会员砍价信息id")
	private Long memberBargainingInformationId;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	@TableField(exist = false)
	private CellarMemberDbEntity cellarMemberDbEntity;
	/**
	 * 砍价金额
	 */
	@ApiModelProperty(required=false,value="砍价金额")
	private BigDecimal cutPrice;
	/**
	 * 砍价之前金额
	 */
	@ApiModelProperty(required=false,value="砍价之前金额")
	private BigDecimal beforeCutPrice;
	/**
	 * 砍价之后金额
	 */
	@ApiModelProperty(required=false,value="砍价之后金额")
	private BigDecimal afterCutPrice;
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
	 * 设置：会员砍价记录id
	 */
	public void setMemberBargainingRecordId(Long memberBargainingRecordId) {
		this.memberBargainingRecordId = memberBargainingRecordId;
	}
	/**
	 * 获取：会员砍价记录id
	 */
	public Long getMemberBargainingRecordId() {
		return memberBargainingRecordId;
	}
	/**
	 * 设置：会员砍价信息id
	 */
	public void setMemberBargainingInformationId(Long memberBargainingInformationId) {
		this.memberBargainingInformationId = memberBargainingInformationId;
	}
	/**
	 * 获取：会员砍价信息id
	 */
	public Long getMemberBargainingInformationId() {
		return memberBargainingInformationId;
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
	 * 设置：砍价金额
	 */
	public void setCutPrice(BigDecimal cutPrice) {
		this.cutPrice = cutPrice;
	}
	/**
	 * 获取：砍价金额
	 */
	public BigDecimal getCutPrice() {
		return cutPrice;
	}
	/**
	 * 设置：砍价之前金额
	 */
	public void setBeforeCutPrice(BigDecimal beforeCutPrice) {
		this.beforeCutPrice = beforeCutPrice;
	}
	/**
	 * 获取：砍价之前金额
	 */
	public BigDecimal getBeforeCutPrice() {
		return beforeCutPrice;
	}
	/**
	 * 设置：砍价之后金额
	 */
	public void setAfterCutPrice(BigDecimal afterCutPrice) {
		this.afterCutPrice = afterCutPrice;
	}
	/**
	 * 获取：砍价之后金额
	 */
	public BigDecimal getAfterCutPrice() {
		return afterCutPrice;
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

	public CellarMemberDbEntity getCellarMemberDbEntity() {
		if (ObjectUtil.isNotNull(this.memberId)) {
			CellarMemberDbService cellarMemberDbService = SpringContextUtils.getBean(CellarMemberDbService.class);
			this.cellarMemberDbEntity = cellarMemberDbService.getById(this.memberId);
		}
		return cellarMemberDbEntity;
	}
}
