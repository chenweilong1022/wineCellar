package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.service.CellarMemberDynamicThumbDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员动态表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-05-13 09:58:25
 */
@Data
@TableName("cellar_member_dynamic_db")
@ApiModel("会员动态表")
public class CellarMemberDynamicDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员动态id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员动态id")
	private Long memberDynamicId;
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
	 * 文字内容
	 */
	@ApiModelProperty(required=false,value="文字内容")
	private String writtenContent;
	/**
	 * 图片内容
	 */
	@ApiModelProperty(required=false,value="图片内容")
	private String imageContent;
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
	 * 是否点赞
	 */
	@ApiModelProperty(required=false,value="是否点赞")
	@TableField(exist = false)
	private int isThumb;
	/**
	 * 当前会员id
	 */
	@ApiModelProperty(required=false,value="当前会员id")
	@TableField(exist = false)
	private Long currentMemberId;


	public int getIsThumb() {
		if (ObjectUtil.isNotNull(this.currentMemberId)) {
			CellarMemberDynamicThumbDbService cellarMemberDynamicThumbDbService = SpringContextUtils.getBean(CellarMemberDynamicThumbDbService.class);
			this.isThumb = cellarMemberDynamicThumbDbService.count(new QueryWrapper<CellarMemberDynamicThumbDbEntity>().lambda()
					.eq(CellarMemberDynamicThumbDbEntity::getMemberId, this.currentMemberId)
					.eq(CellarMemberDynamicThumbDbEntity::getMemberDynamicId, this.memberDynamicId)
			);
		}
		return isThumb;
	}
}
