package io.renren.modules.cellar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.constants.Constants;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.service.CellarCommodityDbService;
import io.renren.modules.cellar.service.CellarStoreDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 酒窖购物车
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-28 13:42:16
 */
@TableName("cellar_cart_db")
@ApiModel("酒窖购物车")
public class CellarCartDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 购物车id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="购物车id")
	private Long cartId;
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
	 * 店铺id
	 */
	@ApiModelProperty(required=false,value="店铺id")
	private Long storeId;
	/**
	 * 店铺名称
	 */
	@ApiModelProperty(required=false,value="店铺名称")
	private String storeName;
	/**
	 * 商品id
	 */
	@ApiModelProperty(required=false,value="商品id")
	private Long commodityId;
	/**
	 * 商品名称
	 */
	@ApiModelProperty(required=false,value="商品名称")
	private String commodityName;
	/**
	 * 商品图片
	 */
	@ApiModelProperty(required=false,value="商品图片")
	private String picture;
	/**
	 * 商品价格
	 */
	@ApiModelProperty(required=false,value="商品价格")
	private BigDecimal prices;
	/**
	 * 商品数量
	 */
	@ApiModelProperty(required=false,value="商品数量")
	private BigDecimal number;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
	/**
	 * 类型
	 */
	@ApiModelProperty(required=false,value="类型")
	private Integer type;
	/**
	 * 类型
	 */
	@ApiModelProperty(required=false,value="店铺下商品列表")
	@TableField(exist = false)
	private List<CellarCartDbEntity> cellarCartDbEntities;

	/**
	 * 设置：购物车id
	 */
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	/**
	 * 获取：购物车id
	 */
	public Long getCartId() {
		return cartId;
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
	 * 设置：店铺id
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	/**
	 * 获取：店铺id
	 */
	public Long getStoreId() {
		if (storeId != null) {
			CellarStoreDbService cellarStoreDbService = SpringContextUtils.getBean(CellarStoreDbService.class);
			CellarStoreDbEntity cellarStoreDbEntity = cellarStoreDbService.getById(storeId);
			if (cellarStoreDbEntity == null) {
				return storeId;
			}
			this.storeName = cellarStoreDbEntity.getStoreName();
		}
		return storeId;
	}
	/**
	 * 设置：店铺名称
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	/**
	 * 获取：店铺名称
	 */
	public String getStoreName() {
		return storeName;
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
		if (this.commodityId != null) {
			CellarCommodityDbService cellarCommodityDbService = SpringContextUtils.getBean(CellarCommodityDbService.class);
			CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(this.commodityId);

			if (cellarCommodityDbEntity == null) {
				return commodityId;
			}
			this.commodityName = cellarCommodityDbEntity.getCommodityName();
			this.picture = cellarCommodityDbEntity.getPicture();
			if (this.type.equals(Constants.CARTTYPE.ZERO.getKey()) ||
					this.type.equals(Constants.CARTTYPE.ONE.getKey()) ||
					this.type.equals(Constants.CARTTYPE.TWO.getKey()) ||
					this.type.equals(Constants.CARTTYPE.THREE.getKey())) {
				this.prices = cellarCommodityDbEntity.getPresentPrice();
			}
		}
		return commodityId;
	}
	/**
	 * 设置：商品名称
	 */
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	/**
	 * 获取：商品名称
	 */
	public String getCommodityName() {
		return commodityName;
	}
	/**
	 * 设置：商品图片
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	/**
	 * 获取：商品图片
	 */
	public String getPicture() {
		return picture;
	}
	/**
	 * 设置：商品价格
	 */
	public void setPrices(BigDecimal prices) {
		this.prices = prices;
	}
	/**
	 * 获取：商品价格
	 */
	public BigDecimal getPrices() {
		return prices;
	}
	/**
	 * 设置：商品数量
	 */
	public void setNumber(BigDecimal number) {
		this.number = number;
	}
	/**
	 * 获取：商品数量
	 */
	public BigDecimal getNumber() {
		return number;
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
	 * 设置：类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public Integer getType() {
		return type;
	}

	public List<CellarCartDbEntity> getCellarCartDbEntities() {
		return cellarCartDbEntities;
	}

	public void setCellarCartDbEntities(List<CellarCartDbEntity> cellarCartDbEntities) {
		this.cellarCartDbEntities = cellarCartDbEntities;
	}
}
