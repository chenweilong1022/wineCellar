package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.constants.Constants;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.service.CellarCommodityDbService;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 首页广告图表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-18 09:47:37
 */
@TableName("cellar_advertising_photo_db")
@ApiModel("首页广告图表")
public class CellarAdvertisingPhotoDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 广告id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="广告id")
	private Long advertisingPhotoId;
	/**
	 * 广告图
	 */
	@ApiModelProperty(required=false,value="广告图")
	private String advertisingFigure;
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
	 * 类型
	 */
	@ApiModelProperty(required=false,value="类型")
	private Integer advertisingType;
	/**
	 * 类型
	 */
	@ApiModelProperty(required=false,value="类型")
	@TableField(exist = false)
	private String advertisingTypeStr;
	/**
	 * h5路径
	 */
	@ApiModelProperty(required=false,value="h5路径")
	private String h5Path;
	/**
	 * 店铺id
	 */
	@ApiModelProperty(required=false,value="店铺id")
	private Long storeId;
	/**
	 * 商品id
	 */
	@ApiModelProperty(required=false,value="商品id")
	private Long commodityId;
	/**
	 * 商品名称
	 */
	@ApiModelProperty(required=false,value="商品名称")
	@TableField(exist = false)
	private String commodityName;

	/**
	 * 设置：广告id
	 */
	public void setAdvertisingPhotoId(Long advertisingPhotoId) {
		this.advertisingPhotoId = advertisingPhotoId;
	}
	/**
	 * 获取：广告id
	 */
	public Long getAdvertisingPhotoId() {
		return advertisingPhotoId;
	}
	/**
	 * 设置：广告图
	 */
	public void setAdvertisingFigure(String advertisingFigure) {
		this.advertisingFigure = advertisingFigure;
	}
	/**
	 * 获取：广告图
	 */
	public String getAdvertisingFigure() {
		return advertisingFigure;
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
	 * 设置：类型
	 */
	public void setAdvertisingType(Integer advertisingType) {
		this.advertisingType = advertisingType;
	}
	/**
	 * 获取：类型
	 */
	public Integer getAdvertisingType() {
		return advertisingType;
	}
	/**
	 * 设置：h5路径
	 */
	public void setH5Path(String h5Path) {
		this.h5Path = h5Path;
	}
	/**
	 * 获取：h5路径
	 */
	public String getH5Path() {
		return h5Path;
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

	public String getAdvertisingTypeStr() {
		if(ObjectUtil.isNotNull(advertisingType)) {
			this.advertisingTypeStr = Constants.SHUFFLINGTYPE.getValueByKey(this.advertisingType);
		}
		return advertisingTypeStr;
	}

	public String getCommodityName() {
		if(ObjectUtil.isNotNull(commodityId)) {
			CellarCommodityDbService cellarCommodityDbService = SpringContextUtils.getBean(CellarCommodityDbService.class);
			CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(this.commodityId);
			this.commodityName = cellarCommodityDbEntity == null ? null : cellarCommodityDbEntity.getCommodityName();
		}
		return commodityName;
	}
}
