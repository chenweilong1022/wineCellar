package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.service.CellarMemberVideoCommentThumbDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员交友视频评论记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 14:11:13
 */
@Data
@TableName("cellar_member_video_comment_db")
@ApiModel("会员交友视频评论记录表")
public class CellarMemberVideoCommentDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员交友视频评论id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员交友视频评论id")
	private Long memberVideoCommentId;
	/**
	 * 评论人
	 */
	@ApiModelProperty(required=false,value="评论人")
	private Long commentMemberId;
	/**
	 * 评论内容
	 */
	@ApiModelProperty(required=false,value="评论内容")
	private String commentContent;
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
	 * 点赞数量
	 */
	@ApiModelProperty(required=false,value="点赞数量")
	private Integer commentThumbNumber;

	/**
	 * 点赞会员id
	 */
	@ApiModelProperty(required=false,value="点赞会员id")
	@TableField(exist = false)
	private Long thumbMemberId;
	/**
	 * 是否点赞 0未点赞1已点赞
	 */
	@ApiModelProperty(required=false,value="是否点赞 0未点赞1已点赞")
	@TableField(exist = false)
	private Integer isThumb;

	public Integer getIsThumb() {
		if (ObjectUtil.isNotNull(this.memberVideoId) && ObjectUtil.isNotNull(this.thumbMemberId) && ObjectUtil.isNotNull(this.memberVideoCommentId)){
			CellarMemberVideoCommentThumbDbService cellarMemberVideoCommentThumbDbService = SpringContextUtils.getBean(CellarMemberVideoCommentThumbDbService.class);
			/**
			 * 查询用户给改评论是否已经点赞
			 */
			this.isThumb = cellarMemberVideoCommentThumbDbService.count(new QueryWrapper<CellarMemberVideoCommentThumbDbEntity>().lambda()
					.eq(CellarMemberVideoCommentThumbDbEntity::getState, Constants.STATE.zero.getKey())
					.eq(CellarMemberVideoCommentThumbDbEntity::getMemberVideoId, this.memberVideoId)
					.eq(CellarMemberVideoCommentThumbDbEntity::getMemberVideoCommentId, this.memberVideoCommentId)
					.eq(CellarMemberVideoCommentThumbDbEntity::getThumbMemberId, this.thumbMemberId)
			);
		}
		return isThumb;
	}
}
