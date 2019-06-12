package io.renren.modules.cellar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员消息
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-19 11:07:06
 */
@TableName("cellar_member_message_db")
@ApiModel("会员消息")
public class CellarMemberMessageDbEntity extends AbstractEntity implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员消息id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员消息id")
	private Long memberMessageId;
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
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
	/**
	 * 消息内容
	 */
	@ApiModelProperty(required=false,value="消息内容")
	private String messageContent;
	/**
	 * 消息标题
	 */
	@ApiModelProperty(required=false,value="消息标题")
	private String messageHead;
	/**
	 * 消息类型
	 */
	@ApiModelProperty(required=false,value="消息类型")
	private Integer messageType;
	/**
	 * 是否已读
	 */
	@ApiModelProperty(required=false,value="是否已读")
	private Integer haveRead;

	/**
	 * 设置：会员消息id
	 */
	public void setMemberMessageId(Long memberMessageId) {
		this.memberMessageId = memberMessageId;
	}
	/**
	 * 获取：会员消息id
	 */
	public Long getMemberMessageId() {
		return memberMessageId;
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
	 * 设置：消息内容
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	/**
	 * 获取：消息内容
	 */
	public String getMessageContent() {
		return messageContent;
	}
	/**
	 * 设置：消息标题
	 */
	public void setMessageHead(String messageHead) {
		this.messageHead = messageHead;
	}
	/**
	 * 获取：消息标题
	 */
	public String getMessageHead() {
		return messageHead;
	}
	/**
	 * 设置：消息类型
	 */
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	/**
	 * 获取：消息类型
	 */
	public Integer getMessageType() {
		return messageType;
	}
	/**
	 * 设置：是否已读
	 */
	public void setHaveRead(Integer haveRead) {
		this.haveRead = haveRead;
	}
	/**
	 * 获取：是否已读
	 */
	public Integer getHaveRead() {
		return haveRead;
	}

	@Override
	public CellarMemberMessageDbEntity clone() {
		try {
			return (CellarMemberMessageDbEntity) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
