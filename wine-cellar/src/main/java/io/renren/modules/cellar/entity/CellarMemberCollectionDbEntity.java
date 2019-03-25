package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.service.CellarCommodityDbService;
import io.renren.modules.cellar.service.CellarStoreDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员收藏表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-23 13:51:41
 */
@TableName("cellar_member_collection_db")
@ApiModel("会员收藏表")
public class CellarMemberCollectionDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员收藏id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员收藏id")
	private Long memberCollectionId;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
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
	 * 收藏类型
	 */
	@ApiModelProperty(required=false,value="收藏类型")
	private Integer collectionType;
	/**
	 * 商品id
	 */
	@ApiModelProperty(required=false,value="商品id")
	private Long commodityId;
	/**
	 * 商品id
	 */
	@ApiModelProperty(required=false,value="商品id")
	@TableField(exist = false)
	private CellarCommodityDbEntity cellarCommodityDbEntity;
	/**
	 * 店铺id
	 */
	@ApiModelProperty(required=false,value="店铺id")
	private Long storeId;
	/**
	 * 店铺id
	 */
	@ApiModelProperty(required=false,value="店铺id")
	@TableField(exist = false)
	private CellarStoreDbEntity cellarStoreDbEntity;

	/**
	 * 设置：会员收藏id
	 */
	public void setMemberCollectionId(Long memberCollectionId) {
		this.memberCollectionId = memberCollectionId;
	}
	/**
	 * 获取：会员收藏id
	 */
	public Long getMemberCollectionId() {
		return memberCollectionId;
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
	 * 设置：收藏类型
	 */
	public void setCollectionType(Integer collectionType) {
		this.collectionType = collectionType;
	}
	/**
	 * 获取：收藏类型
	 */
	public Integer getCollectionType() {
		return collectionType;
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
	 * 设置：店铺id
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	/**
	 * 获取：店铺id
	 */
	public Long getStoreId() {
		return storeId;
	}

	public CellarCommodityDbEntity getCellarCommodityDbEntity() {
		if (ObjectUtil.isNotNull(commodityId)) {
			CellarCommodityDbService cellarCommodityDbService = SpringContextUtils.getBean(CellarCommodityDbService.class);
			this.cellarCommodityDbEntity = cellarCommodityDbService.getById(commodityId);
		}
		return cellarCommodityDbEntity;
	}

	public CellarStoreDbEntity getCellarStoreDbEntity() {
		if (ObjectUtil.isNotNull(storeId)) {
			CellarStoreDbService cellarStoreDbService = SpringContextUtils.getBean(CellarStoreDbService.class);
			this.cellarStoreDbEntity = cellarStoreDbService.getById(storeId);
		}
		return cellarStoreDbEntity;
	}
}
