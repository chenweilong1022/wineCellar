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
 * 会员动态点赞表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-05-14 17:54:33
 */
@Data
@TableName("cellar_member_dynamic_thumb_db")
@ApiModel("会员动态点赞表")
public class CellarMemberDynamicThumbDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员动态点赞id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员动态点赞id")
	private Long memberDynamicThumbId;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
	/**
	 * 会员动态id
	 */
	@ApiModelProperty(required=false,value="会员动态id")
	private Long memberDynamicId;
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
