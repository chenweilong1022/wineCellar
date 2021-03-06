package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.renren.common.constants.Constants;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.cellar.entity.CellarStoreDbEntity;
import io.renren.modules.cellar.service.CellarStoreDbService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;
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
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
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
	@TableField(exist = false)
	@ApiModelProperty(required=false,value="状态中文描述",hidden = true)
	private String stateStr;
	@TableField(exist = false)
	@ApiModelProperty(required=false,value="店铺名称",hidden = true)
	private String storeName;
	@TableField(exist = false)
	@ApiModelProperty(required=false,value="管理后台参数",hidden = true)
	private String t;
	@TableField(exist = false)
	@ApiModelProperty(required=false,value="是否设置参数",hidden = true)
	protected boolean flag = true;

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

	public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

	public void setStateStr(Integer state) {
		this.stateStr = Constants.STATE.getValueByKey(state);
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(Long storeId) {
		if (storeId != null) {
			CellarStoreDbService cellarStoreDbService = SpringContextUtils.getBean(CellarStoreDbService.class);
			CellarStoreDbEntity cellarStoreDbEntity = cellarStoreDbService.getById(storeId);
			this.storeName = cellarStoreDbEntity == null?"":cellarStoreDbEntity.getStoreName();
		}
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setT(String t) {
		this.t = t;
	}

	public boolean isSystem() {
		return StringUtils.isNotBlank(t);
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
