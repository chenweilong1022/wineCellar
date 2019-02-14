package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.constants.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * entity公共类
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-01-16 17:34:20
 */
@ApiModel("entity公共类")
public abstract class AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会员id
	 */
	@TableField(exist = false)
	@ApiModelProperty(required=false,value="搜索关键字",hidden = true)
	private String key;
	@TableField(exist = false)
	@ApiModelProperty(required=false,value="当前页数",hidden = true)
	private Integer page;
	@TableField(exist = false)
	@ApiModelProperty(required=false,value="每页条数",hidden = true)
	private Integer limit;
	@TableField(exist = false)
	@ApiModelProperty(required=false,value="暂时无用",hidden = true)
	private String sidx;
	@TableField(exist = false)
	@ApiModelProperty(required=false,value="暂时无用",hidden = true)
	private String order;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
