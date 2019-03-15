package io.renren.modules.cellar.entity;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.service.CellarMemberCouponDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 优惠券表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-12 17:59:13
 */
@TableName("cellar_coupon_db")
@ApiModel("优惠券表")
public class CellarCouponDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 优惠券id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="优惠券id")
	private Long couponId;
	/**
	 * 店铺id
	 */
	@ApiModelProperty(required=false,value="店铺id")
	private Long storeId;
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
	 * 优惠券数量
	 */
	@ApiModelProperty(required=false,value="优惠券数量")
	private Integer couponNumbers;
	/**
	 * 优惠券类型
	 */
	@ApiModelProperty(required=false,value="优惠券类型")
	private Integer couponType;
	/**
	 * 优惠券类型
	 */
	@ApiModelProperty(required=false,value="优惠券类型")
	@TableField(exist = false)
	private String couponTypeStr;
	/**
	 * 有效开始时间
	 */
	@ApiModelProperty(required=false,value="有效开始时间")
	private Date effectiveStartTime;
	/**
	 * 有效开始时间
	 */
	@ApiModelProperty(required=false,value="有效开始时间")
	@TableField(exist = false)
	private Long effectiveStartTimeLong;
	/**
	 * 有效结束时间
	 */
	@ApiModelProperty(required=false,value="有效结束时间")
	private Date effectiveEndTime;
	/**
	 * 有效结束时间
	 */
	@ApiModelProperty(required=false,value="有效结束时间")
	@TableField(exist = false)
	private Long effectiveEndTimeLong;
	/**
	 * 满
	 */
	@ApiModelProperty(required=false,value="满")
	private BigDecimal full;
	/**
	 * 减
	 */
	@ApiModelProperty(required=false,value="减")
	private BigDecimal reductionOf;
	/**
	 *领取状态
	 */
	@ApiModelProperty(required=false,value="1 未领取 2 已领取")
	@TableField(exist = false)
	private Integer receiveState;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	@TableField(exist = false)
	private Long memberId;
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
	 * 设置：店铺id
	 */
	public void setStoreId(Long storeId) {
		super.setStoreName(storeId);
		this.storeId = storeId;
	}
	/**
	 * 获取：店铺id
	 */
	public Long getStoreId() {
		return storeId;
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
	 * 设置：状态
	 */
	public void setState(Integer state) {
		super.setStateStr(state);
		this.state = state;
	}
	/**
	 * 获取：状态
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * 设置：优惠券数量
	 */
	public void setCouponNumbers(Integer couponNumbers) {
		this.couponNumbers = couponNumbers;
	}
	/**
	 * 获取：优惠券数量
	 */
	public Integer getCouponNumbers() {
		return couponNumbers;
	}
	/**
	 * 设置：优惠券类型
	 */
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	/**
	 * 获取：优惠券类型
	 */
	public Integer getCouponType() {
		return couponType;
	}
	/**
	 * 设置：有效开始时间
	 */
	public void setEffectiveStartTime(Date effectiveStartTime) {
		this.effectiveStartTime = effectiveStartTime;
	}
	/**
	 * 获取：有效开始时间
	 */
	public Date getEffectiveStartTime() {
		return effectiveStartTime;
	}
	/**
	 * 设置：有效结束时间
	 */
	public void setEffectiveEndTime(Date effectiveEndTime) {
		this.effectiveEndTime = effectiveEndTime;
	}
	/**
	 * 获取：有效结束时间
	 */
	public Date getEffectiveEndTime() {
		return effectiveEndTime;
	}
	/**
	 * 设置：满
	 */
	public void setFull(BigDecimal full) {
		this.full = full;
	}
	/**
	 * 获取：满
	 */
	public BigDecimal getFull() {
		return full;
	}
	/**
	 * 设置：减
	 */
	public void setReductionOf(BigDecimal reductionOf) {
		this.reductionOf = reductionOf;
	}
	/**
	 * 获取：减
	 */
	public BigDecimal getReductionOf() {
		return reductionOf;
	}

	public String getCouponTypeStr() {
		if (this.couponType != null) {
			return Constants.COUPONTYPE.getValueByKey(couponType);
		}
		return couponTypeStr;
	}

	public void setCouponTypeStr(String couponTypeStr) {
		this.couponTypeStr = couponTypeStr;
	}

	public Long getEffectiveStartTimeLong() {
		if (ObjectUtil.isNotNull(effectiveStartTime)) {
			this.effectiveStartTimeLong = effectiveStartTime.getTime();
		}
		return effectiveStartTimeLong;
	}

	public void setEffectiveStartTimeLong(Long effectiveStartTimeLong) {
		this.effectiveStartTimeLong = effectiveStartTimeLong;
	}

	public Long getEffectiveEndTimeLong() {
		if (ObjectUtil.isNotNull(effectiveEndTime)) {
			this.effectiveEndTimeLong = effectiveEndTime.getTime();
		}
		return effectiveEndTimeLong;
	}

	public void setEffectiveEndTimeLong(Long effectiveEndTimeLong) {
		this.effectiveEndTimeLong = effectiveEndTimeLong;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Integer getReceiveState() {
		if (ObjectUtil.isNotNull(this.memberId)) {
			CellarMemberCouponDbService cellarMemberCouponDbService = SpringContextUtils.getBean(CellarMemberCouponDbService.class);
			CellarMemberCouponDbEntity cellarMemberCouponDbEntity = cellarMemberCouponDbService.getOne(new QueryWrapper<CellarMemberCouponDbEntity>().lambda()
					.eq(CellarMemberCouponDbEntity::getMemberId, this.memberId)
					.eq(CellarMemberCouponDbEntity::getCouponId, this.couponId)
			);
			return cellarMemberCouponDbEntity == null? Constants.RECEIVESTATE.ONE.getKey(): Constants.RECEIVESTATE.TWO.getKey();
		}
		return Constants.RECEIVESTATE.ONE.getKey();
	}
}
