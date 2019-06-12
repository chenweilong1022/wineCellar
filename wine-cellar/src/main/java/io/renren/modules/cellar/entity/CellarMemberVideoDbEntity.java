package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.SpringContextUtils;
import io.renren.common.utils.vod.VodUtil;
import io.renren.modules.cellar.service.CellarMemberVideoThumbDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员交友视频表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 10:54:07
 */
@Data
@TableName("cellar_member_video_db")
@ApiModel("会员交友视频表")
public class CellarMemberVideoDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员交友视频id
	 */
	@TableId(type = IdType.AUTO)
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
	 * 标题
	 */
	@ApiModelProperty(required=false,value="标题")
	private String title;
	/**
	 * 简介
	 */
	@ApiModelProperty(required=false,value="简介")
	private String introduction;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
	/**
	 * 会员头像
	 */
	@ApiModelProperty(required=false,value="会员头像")
	private String headPortrait;
	/**
	 * 点赞数量
	 */
	@ApiModelProperty(required=false,value="点赞数量")
	private Integer thumbNumber;
	/**
	 * 评论数量
	 */
	@ApiModelProperty(required=false,value="评论数量")
	private Integer commentNumber;
	/**
	 * 分享数量
	 */
	@ApiModelProperty(required=false,value="分享数量")
	private Integer shareNumber;
	/**
	 * 阿里云视频id
	 */
	@ApiModelProperty(required=false,value="阿里云视频id")
	private String videoId;
	/**
	 * 封面图
	 */
	@ApiModelProperty(required=false,value="封面图")
	private String cover;
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
		if (ObjectUtil.isNotNull(this.thumbMemberId) && ObjectUtil.isNotNull(this.memberVideoId)) {
			CellarMemberVideoThumbDbService cellarMemberVideoThumbDbService = SpringContextUtils.getBean(CellarMemberVideoThumbDbService.class);
			this.isThumb = cellarMemberVideoThumbDbService.count(new QueryWrapper<CellarMemberVideoThumbDbEntity>().lambda()
					.eq(CellarMemberVideoThumbDbEntity::getMemberVideoId, this.memberVideoId)
					.eq(CellarMemberVideoThumbDbEntity::getThumbMemberId, this.thumbMemberId)
					.eq(CellarMemberVideoThumbDbEntity::getState, Constants.STATE.zero.getKey())
			);
		}
		return isThumb;
	}

	public String getCover() {
		if (ObjectUtil.isNotNull(this.videoId)) {
			GetVideoPlayAuthResponse.VideoMeta videoMeta = null;
			try {
				videoMeta = VodUtil.getVideoMeta(this.videoId);
			} catch (ClientException e) {
				e.printStackTrace();
			}
			this.cover = videoMeta.getCoverURL();
		}
		return cover;
	}
}
