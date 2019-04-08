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

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 秒杀活动表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-29 10:13:13
 */
@TableName("cellar_kill_activity_db")
@ApiModel("秒杀活动表")
public class CellarKillActivityDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 秒杀活动id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="秒杀活动id")
	private Long killActivityId;
	/**
	 * 商品id
	 */
	@ApiModelProperty(required=false,value="商品id")
	private Long commodityId;
	/**
	 * 审核商品信息
	 */
	@ApiModelProperty(required=false,value="审核商品信息")
	@TableField(exist = false)
	private CellarCommodityDbEntity cellarCommodityDbEntity;
	/**
	 * 秒杀开始时间
	 */
	@ApiModelProperty(required=false,value="秒杀开始时间")
	private Date killStartTime;
	/**
	 * 秒杀结束时间
	 */
	@ApiModelProperty(required=false,value="秒杀结束时间")
	private Date killEndTime;
	/**
	 * 秒杀价格
	 */
	@ApiModelProperty(required=false,value="秒杀价格")
	private BigDecimal killPrice;
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
	 * 秒杀数量
	 */
	@ApiModelProperty(required=false,value="秒杀数量")
	private BigDecimal killNumber;
	/**
	 * 秒杀列表
	 */
	@ApiModelProperty(required=false,value="秒杀列表")
	@TableField(exist = false)
	private List<CellarKillActivityDbEntity> cellarKillActivityDbEntities;

	/**
	 * 设置：秒杀活动id
	 */
	public void setKillActivityId(Long killActivityId) {
		this.killActivityId = killActivityId;
	}
	/**
	 * 获取：秒杀活动id
	 */
	public Long getKillActivityId() {
		return killActivityId;
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
	 * 设置：秒杀开始时间
	 */
	public void setKillStartTime(Date killStartTime) {
		this.killStartTime = killStartTime;
	}
	/**
	 * 获取：秒杀开始时间
	 */
	public Date getKillStartTime() {
		return killStartTime;
	}
	/**
	 * 设置：秒杀结束时间
	 */
	public void setKillEndTime(Date killEndTime) {
		this.killEndTime = killEndTime;
	}
	/**
	 * 获取：秒杀结束时间
	 */
	public Date getKillEndTime() {
		return killEndTime;
	}
	/**
	 * 设置：秒杀价格
	 */
	public void setKillPrice(BigDecimal killPrice) {
		this.killPrice = killPrice;
	}
	/**
	 * 获取：秒杀价格
	 */
	public BigDecimal getKillPrice() {
		return killPrice;
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
	 * 设置：秒杀数量
	 */
	public void setKillNumber(BigDecimal killNumber) {
		this.killNumber = killNumber;
	}
	/**
	 * 获取：秒杀数量
	 */
	public BigDecimal getKillNumber() {
		return killNumber;
	}

	public CellarCommodityDbEntity getCellarCommodityDbEntity() {
		if (flag && ObjectUtil.isNotNull(commodityId)) {
			CellarCommodityDbService cellarCommodityDbService = SpringContextUtils.getBean(CellarCommodityDbService.class);
			this.cellarCommodityDbEntity = cellarCommodityDbService.getById(this.commodityId);
		}
		return cellarCommodityDbEntity;
	}

	public List<CellarKillActivityDbEntity> getCellarKillActivityDbEntities() {
		return cellarKillActivityDbEntities;
	}

	public void setCellarKillActivityDbEntities(List<CellarKillActivityDbEntity> cellarKillActivityDbEntities) {
		this.cellarKillActivityDbEntities = cellarKillActivityDbEntities;
	}
}
