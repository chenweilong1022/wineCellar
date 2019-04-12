package io.renren.modules.cellar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员交友视频评论点赞记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 16:09:08
 */
@Data
@TableName("cellar_member_video_comment_thumb_db")
@ApiModel("会员交友视频评论点赞记录表")
public class CellarMemberVideoCommentThumbDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员交友视频评论点赞id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员交友视频评论点赞id")
	private Long memberVideoCommentThumbId;
	/**
	 * 点赞人
	 */
	@ApiModelProperty(required=false,value="点赞人")
	private Long thumbMemberId;
	/**
	 * 会员交友视频评论id
	 */
	@ApiModelProperty(required=false,value="会员交友视频评论id")
	private Long memberVideoCommentId;
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

}
