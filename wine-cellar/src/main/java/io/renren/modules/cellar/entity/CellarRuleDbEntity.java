package io.renren.modules.cellar.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.constants.Constants;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 酒窖规则表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-30 10:12:45
 */
@Data
@TableName("cellar_rule_db")
@ApiModel("酒窖规则表")
public class CellarRuleDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 规则id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="规则id")
	private Long ruleId;
	/**
	 * 规则类型1.储值卡说明2.钱包说明3.酒币说明4.关于我们5.拼团规则6.预售规则7.秒杀规则8.砍价规则
	 */
	@ApiModelProperty(required=false,value="规则类型1.储值卡说明2.钱包说明3.酒币说明4.关于我们5.拼团规则6.预售规则7.秒杀规则8.砍价规则")
	private Integer ruleType;
	/**
	 * 规则类型1.储值卡说明2.钱包说明3.酒币说明4.关于我们5.拼团规则6.预售规则7.秒杀规则8.砍价规则
	 */
	@ApiModelProperty(required=false,value="规则类型1.储值卡说明2.钱包说明3.酒币说明4.关于我们5.拼团规则6.预售规则7.秒杀规则8.砍价规则")
	@TableField(exist = false)
	private String ruleTypeStr;
	/**
	 * 规则描述
	 */
	@ApiModelProperty(required=false,value="规则描述")
	private byte[] ruleDescription;
	/**
	 * 规则描述
	 */
	@ApiModelProperty(required=false,value="规则描述")
	@TableField(exist = false)
	private String ruleDescriptionStr;
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

	public void setState(Integer state) {
		this.state = state;
		super.setStateStr(this.state);
	}

	public byte[] getRuleDescription() {
		if (StringUtils.isNotBlank(this.ruleDescriptionStr)) {
			this.ruleDescription = this.ruleDescriptionStr.getBytes();
		}
		return ruleDescription;
	}

	public String getRuleDescriptionStr() {
		if (ObjectUtil.isNotNull(this.ruleDescription)) {
			this.ruleDescriptionStr = new String(this.ruleDescription);
		}
		return ruleDescriptionStr;
	}

	public String getRuleTypeStr() {
		if (ObjectUtil.isNotNull(this.ruleType)) {
			this.ruleTypeStr = Constants.RULETYPE.getValueByKey(this.ruleType);
		}
		return ruleTypeStr;
	}
}
