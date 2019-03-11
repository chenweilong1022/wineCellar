package io.renren.modules.app.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
public class AppSubmitCellarCommodityEvaluationDbEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	@ApiModelProperty(required=false,value="商品id")
	private Long[] commodityId;
	/**
	 * 订单id
	 */
	@ApiModelProperty(required=false,value="订单id")
	private Long orderId;
	/**
	 * 评价星数
	 */
	@ApiModelProperty(required=false,value="评价星数")
	private Integer[] evaluationStarNumber;
	/**
	 * 评价内容
	 */
	@ApiModelProperty(required=false,value="评价内容")
	private String[] evaluationContent;
	/**
	 * 店铺id
	 */
	@ApiModelProperty(required=false,value="店铺id")
	private Long storeId;
	/**
	 * 评价图片
	 */
	@ApiModelProperty(required=false,value="评价图片")
	private String[] evaluationImage;

	public Long[] getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long[] commodityId) {
		this.commodityId = commodityId;
	}


	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer[] getEvaluationStarNumber() {
		return evaluationStarNumber;
	}

	public void setEvaluationStarNumber(Integer[] evaluationStarNumber) {
		this.evaluationStarNumber = evaluationStarNumber;
	}

	public String[] getEvaluationContent() {
		return evaluationContent;
	}

	public void setEvaluationContent(String[] evaluationContent) {
		this.evaluationContent = evaluationContent;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String[] getEvaluationImage() {
		return evaluationImage;
	}

	public void setEvaluationImage(String[] evaluationImage) {
		this.evaluationImage = evaluationImage;
	}
}
