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
 * 会员动态评论表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-05-15 13:18:56
 */
@Data
@TableName("cellar_member_dynamic_comment_db")
@ApiModel("会员动态评论表")
public class CellarMemberDynamicCommentDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员动态评论id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员动态评论id")
	private Long memberDynamicCommentId;
	/**
	 * 会员动态id
	 */
	@ApiModelProperty(required=false,value="会员动态id")
	private Long memberDynamicId;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
	/**
	 * 会员名称
	 */
	@ApiModelProperty(required=false,value="会员名称")
	private String nickname;
	/**
	 * 会员头像
	 */
	@ApiModelProperty(required=false,value="会员头像")
	private String headPortrait;
	/**
	 * 评论内容
	 */
	@ApiModelProperty(required=false,value="评论内容")
	private String commentContent;
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

}
