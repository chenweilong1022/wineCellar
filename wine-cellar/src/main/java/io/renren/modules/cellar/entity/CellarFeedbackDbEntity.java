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

import java.io.Serializable;
import java.util.Date;

/**
 * 意见反馈表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-26 17:15:05
 */
@TableName("cellar_feedback_db")
@ApiModel("意见反馈表")
public class CellarFeedbackDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 意见反馈id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="意见反馈id")
	private Long feedbackId;
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
	 * 反馈内容
	 */
	@ApiModelProperty(required=false,value="反馈内容")
	private String feedbackContent;

	/**
	 * 设置：意见反馈id
	 */
	public void setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
	}
	/**
	 * 获取：意见反馈id
	 */
	public Long getFeedbackId() {
		return feedbackId;
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
	 * 设置：反馈内容
	 */
	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}
	/**
	 * 获取：反馈内容
	 */
	public String getFeedbackContent() {
		return feedbackContent;
	}

	public CellarMemberDbEntity getCellarMemberDbEntity() {
		if (ObjectUtil.isNotNull(this.memberId)) {
			CellarMemberDbService cellarMemberDbService = SpringContextUtils.getBean(CellarMemberDbService.class);
			this.cellarMemberDbEntity = cellarMemberDbService.getById(this.memberId);
		}
		return cellarMemberDbEntity;
	}
}
