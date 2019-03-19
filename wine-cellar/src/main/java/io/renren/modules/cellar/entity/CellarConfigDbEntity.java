package io.renren.modules.cellar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 全局配置表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-14 15:13:04
 */
@TableName("cellar_config_db")
@ApiModel("全局配置表")
public class CellarConfigDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 配置id
	 */
	@TableId(type = IdType.NONE)
	@ApiModelProperty(required=false,value="配置id")
	private Long configId;
	/**
	 * 运费配置
	 */
	@ApiModelProperty(required=false,value="运费配置")
	private BigDecimal freight;
	/**
	 * 距离
	 */
	@ApiModelProperty(required=false,value="距离")
	private BigDecimal distance;

	/**
	 * 设置：配置id
	 */
	public void setConfigId(Long configId) {
		this.configId = configId;
	}
	/**
	 * 获取：配置id
	 */
	public Long getConfigId() {
		return configId;
	}
	/**
	 * 设置：运费配置
	 */
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	/**
	 * 获取：运费配置
	 */
	public BigDecimal getFreight() {
		return freight;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}
}
