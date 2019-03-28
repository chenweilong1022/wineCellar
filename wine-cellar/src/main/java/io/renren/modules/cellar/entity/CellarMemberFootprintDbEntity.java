package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.service.CellarCommodityDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员足迹表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-25 17:29:13
 */
@TableName("cellar_member_footprint_db")
@ApiModel("会员足迹表")
public class CellarMemberFootprintDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员足迹id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员足迹id")
	private Long memberFootprintId;
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
	 * 商品id
	 */
	@ApiModelProperty(required=false,value="商品id")
	private Long commodityId;
	/**
	 * 最后访问时间
	 */
	@ApiModelProperty(required=false,value="最后访问时间")
	private Date lastTime;
	/**
	 * 访问次数
	 */
	@ApiModelProperty(required=false,value="访问次数")
	private Integer visits;
	/**
	 * 访问次数
	 */
	@ApiModelProperty(required=false,value="访问次数")
	@TableField(exist = false)
	private CellarCommodityDbEntity cellarCommodityDbEntity;
	/**
	 * 设置：会员足迹id
	 */
	public void setMemberFootprintId(Long memberFootprintId) {
		this.memberFootprintId = memberFootprintId;
	}
	/**
	 * 获取：会员足迹id
	 */
	public Long getMemberFootprintId() {
		return memberFootprintId;
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
	 * 设置：商品id
	 */
	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}
	/**
	 * 获取：商品id
	 */
	public Long getCommodityId() {
		return commodityId;
	}
	/**
	 * 设置：最后访问时间
	 */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	/**
	 * 获取：最后访问时间
	 */
	public Date getLastTime() {
		return lastTime;
	}

	public Integer getVisits() {
		return visits;
	}

	public void setVisits(Integer visits) {
		this.visits = visits;
	}

	public CellarCommodityDbEntity getCellarCommodityDbEntity() {
		if (ObjectUtil.isNotNull(this.commodityId)) {
			CellarCommodityDbService cellarCommodityDbService = SpringContextUtils.getBean(CellarCommodityDbService.class);
			this.cellarCommodityDbEntity = cellarCommodityDbService.getById(this.commodityId);
		}
		return cellarCommodityDbEntity;
	}

	public void setCellarCommodityDbEntity(CellarCommodityDbEntity cellarCommodityDbEntity) {
		this.cellarCommodityDbEntity = cellarCommodityDbEntity;
	}
}
