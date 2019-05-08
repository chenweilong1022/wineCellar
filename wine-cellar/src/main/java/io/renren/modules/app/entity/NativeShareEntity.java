package io.renren.modules.app.entity;

/**
 * 用户余额充值
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-01-16 17:34:20
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分享实体类
 */
@Data
public class NativeShareEntity {
    /**
     * 1秒杀列表 2秒杀详情3砍价详情4拼团详情5普通详情
     */
    @ApiModelProperty(required=false,value="1秒杀列表2秒杀详情3砍价详情4拼团详情5普通详情")
    private Integer nativeShare;
    /**
     * 店铺id
     */
    @ApiModelProperty(required=false,value="店铺id")
    private Long storeId;
    /**
     * 秒杀活动id
     */
    @ApiModelProperty(required=false,value="秒杀活动id")
    private Long killActivityId;
    /**
     * 砍价活动id
     */
    @ApiModelProperty(required=false,value="砍价活动id")
    private Long bargainingActivityId;
    /**
     * 拼团活动id
     */
    @ApiModelProperty(required=false,value="拼团活动id")
    private Long groupActivityId;
    /**
     * 商品id
     */
    @ApiModelProperty(required=false,value="商品id")
    private Long commodityId;
}
