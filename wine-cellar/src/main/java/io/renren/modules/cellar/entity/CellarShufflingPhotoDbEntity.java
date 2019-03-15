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
 * 首页轮播图表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-15 09:53:34
 */
@TableName("cellar_shuffling_photo_db")
@ApiModel("首页轮播图表")
public class CellarShufflingPhotoDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 轮播图id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="轮播图id")
	private Long shufflingPhotoId;
	/**
	 * 轮播图
	 */
	@ApiModelProperty(required=false,value="轮播图")
	private String shufflingFigure;
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
	private Integer shufflingType;
	/**
	 * 类型
	 */
	@ApiModelProperty(required=false,value="类型")
	@TableField(exist = false)
	private String shufflingTypeStr;
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
	 * 设置：轮播图id
	 */
	public void setShufflingPhotoId(Long shufflingPhotoId) {
		this.shufflingPhotoId = shufflingPhotoId;
	}
	/**
	 * 获取：轮播图id
	 */
	public Long getShufflingPhotoId() {
		return shufflingPhotoId;
	}
	/**
	 * 设置：轮播图
	 */
	public void setShufflingFigure(String shufflingFigure) {
		this.shufflingFigure = shufflingFigure;
	}
	/**
	 * 获取：轮播图
	 */
	public String getShufflingFigure() {
		return shufflingFigure;
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
	 * 设置：类型
	 */
	public void setShufflingType(Integer shufflingType) {
		this.shufflingType = shufflingType;
	}
	/**
	 * 获取：类型
	 */
	public Integer getShufflingType() {
		return shufflingType;
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

	public String getShufflingTypeStr() {
		if(ObjectUtil.isNotNull(shufflingType)) {
			this.shufflingTypeStr = Constants.SHUFFLINGTYPE.getValueByKey(this.shufflingType);
		}
		return shufflingTypeStr;
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
