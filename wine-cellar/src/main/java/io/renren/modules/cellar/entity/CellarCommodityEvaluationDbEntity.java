package io.renren.modules.cellar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品评价表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-11 15:12:42
 */
@TableName("cellar_commodity_evaluation_db")
@ApiModel("商品评价表")
public class CellarCommodityEvaluationDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 评价id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="评价id")
	private Long evaluationId;
	/**
	 * 商品id
	 */
	@ApiModelProperty(required=false,value="商品id")
	private Long commodityId;
	/**
	 * 会员id
	 */
	@ApiModelProperty(required=false,value="会员id")
	private Long memberId;
	/**
	 * 头像
	 */
	@ApiModelProperty(required=false,value="头像")
	private String headPortrait;
	/**
	 * 昵称
	 */
	@ApiModelProperty(required=false,value="昵称")
	private String nickname;
	/**
	 * 订单id
	 */
	@ApiModelProperty(required=false,value="订单id")
	private Long orderId;
	/**
	 * 评价星数
	 */
	@ApiModelProperty(required=false,value="评价星数")
	private Integer evaluationStarNumber;
	/**
	 * 评价内容
	 */
	@ApiModelProperty(required=false,value="评价内容")
	private String evaluationContent;
	/**
	 * 店铺id
	 */
	@ApiModelProperty(required=false,value="店铺id")
	private Long storeId;
	/**
	 * 评价图片
	 */
	@ApiModelProperty(required=false,value="评价图片")
	private String evaluationImage;
	/**
	 * 评价图片
	 */
	@ApiModelProperty(required=false,value="评价图片")
	@TableField(exist = false)
	private String[] evaluationImageList;
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
	 * 设置：评价id
	 */
	public void setEvaluationId(Long evaluationId) {
		this.evaluationId = evaluationId;
	}
	/**
	 * 获取：评价id
	 */
	public Long getEvaluationId() {
		return evaluationId;
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
	 * 设置：订单id
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单id
	 */
	public Long getOrderId() {
		return orderId;
	}
	/**
	 * 设置：评价星数
	 */
	public void setEvaluationStarNumber(Integer evaluationStarNumber) {
		this.evaluationStarNumber = evaluationStarNumber;
	}
	/**
	 * 获取：评价星数
	 */
	public Integer getEvaluationStarNumber() {
		return evaluationStarNumber;
	}
	/**
	 * 设置：评价内容
	 */
	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}
	/**
	 * 获取：评价内容
	 */
	public String getEvaluationContent() {
		return evaluationContent;
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
		return storeId;
	}
	/**
	 * 设置：评价图片
	 */
	public void setEvaluationImage(String evaluationImage) {
		this.evaluationImage = evaluationImage;
	}
	/**
	 * 获取：评价图片
	 */
	public String getEvaluationImage() {
		return evaluationImage;
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

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String[] getEvaluationImageList() {
		if (StringUtils.isNotBlank(evaluationImage)) {
			return evaluationImage.split(",");
		}
		return evaluationImageList;
	}

	public void setEvaluationImageList(String[] evaluationImageList) {
		this.evaluationImageList = evaluationImageList;
	}
}
