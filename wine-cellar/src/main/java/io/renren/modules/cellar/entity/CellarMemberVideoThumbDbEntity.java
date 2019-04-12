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
 * 会员交友视频点赞记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 13:17:10
 */
@TableName("cellar_member_video_thumb_db")
@ApiModel("会员交友视频点赞记录表")
public class CellarMemberVideoThumbDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员交友视频点赞id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员交友视频点赞id")
	private Long memberVideoThumbId;
	/**
	 * 点赞人
	 */
	@ApiModelProperty(required=false,value="点赞人")
	private Long thumbMemberId;
	/**
	 * 会员交友视频id
	 */
	@ApiModelProperty(required=false,value="会员交友视频id")
	private Long memberVideoId;
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
	 * 设置：会员交友视频点赞id
	 */
	public void setMemberVideoThumbId(Long memberVideoThumbId) {
		this.memberVideoThumbId = memberVideoThumbId;
	}
	/**
	 * 获取：会员交友视频点赞id
	 */
	public Long getMemberVideoThumbId() {
		return memberVideoThumbId;
	}
	/**
	 * 设置：点赞人
	 */
	public void setThumbMemberId(Long thumbMemberId) {
		this.thumbMemberId = thumbMemberId;
	}
	/**
	 * 获取：点赞人
	 */
	public Long getThumbMemberId() {
		return thumbMemberId;
	}
	/**
	 * 设置：会员交友视频id
	 */
	public void setMemberVideoId(Long memberVideoId) {
		this.memberVideoId = memberVideoId;
	}
	/**
	 * 获取：会员交友视频id
	 */
	public Long getMemberVideoId() {
		return memberVideoId;
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
}
