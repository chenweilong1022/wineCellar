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
 * 会员交友视频分享记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 15:10:37
 */
@Data
@TableName("cellar_member_video_share_db")
@ApiModel("会员交友视频分享记录表")
public class CellarMemberVideoShareDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员交友视频分享id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员交友视频分享id")
	private Long memberVideoShareId;
	/**
	 * 分享人
	 */
	@ApiModelProperty(required=false,value="分享人")
	private Long shareMemberId;
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
	 * 分享类型
	 */
	@ApiModelProperty(required=false,value="分享类型")
	private Integer shareType;

}
