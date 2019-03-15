package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.service.CellarCouponDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员优惠券表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-13 14:22:14
 */
@TableName("cellar_member_coupon_db")
@ApiModel("会员优惠券表")
public class CellarMemberCouponDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员优惠券id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="会员优惠券id")
	private Long memberCouponId;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
	/**
	 * 优惠券id
	 */
	@ApiModelProperty(required=false,value="优惠券id")
	private Long couponId;
	/**
	 * 店铺id
	 */
	@ApiModelProperty(required=false,value="店铺id")
	private Long storeId;
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
	 * 使用状态
	 */
	@ApiModelProperty(required=false,value="使用状态")
	private Integer usingState;
	/**
	 * 使用时间
	 */
	@ApiModelProperty(required=false,value="使用时间")
	private Date useTime;
	/**
	 * 优惠券信息
	 */
	@ApiModelProperty(required=false,value="优惠券信息")
	@TableField(exist = false)
	private CellarCouponDbEntity cellarCouponDbEntity;

	/**
	 * 设置：会员优惠券id
	 */
	public void setMemberCouponId(Long memberCouponId) {
		this.memberCouponId = memberCouponId;
	}
	/**
	 * 获取：会员优惠券id
	 */
	public Long getMemberCouponId() {
		return memberCouponId;
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
	 * 设置：优惠券id
	 */
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	/**
	 * 获取：优惠券id
	 */
	public Long getCouponId() {
		return couponId;
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
	 * 设置：使用状态
	 */
	public void setUsingState(Integer usingState) {
		this.usingState = usingState;
	}
	/**
	 * 获取：使用状态
	 */
	public Integer getUsingState() {
		return usingState;
	}
	/**
	 * 设置：使用时间
	 */
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	/**
	 * 获取：使用时间
	 */
	public Date getUseTime() {
		return useTime;
	}

	public CellarCouponDbEntity getCellarCouponDbEntity() {
		if (ObjectUtil.isNotNull(couponId)) {
			CellarCouponDbService cellarCouponDbService = SpringContextUtils.getBean(CellarCouponDbService.class);
			this.cellarCouponDbEntity = cellarCouponDbService.getById(couponId);
		}
		return cellarCouponDbEntity;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}


}
