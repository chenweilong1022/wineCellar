package io.renren.modules.app.controller;

import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.renren.common.constants.Constants;
import io.renren.common.utils.R;
import io.renren.common.utils.SpringContextUtils;
import io.renren.common.validator.Assert;
import io.renren.config.HostNameConfig;
import io.renren.modules.app.entity.NativeShareEntity;
import io.renren.modules.cellar.entity.CellarCouponDbEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * APP原生分享
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-12 17:59:13
 */
@RestController
@RequestMapping("app/nativeShare")
@Api(value="APP原生分享",tags="APP原生分享")
public class AppNativeShareController {
    /**
     * 主机名称
     */
    private static String HOSTNAME = SpringContextUtils.getBean(HostNameConfig.class).getName();
    /**
     * 秒杀列表url
     */
    private static String KILLLISTURL = HOSTNAME + "/h5/index.html#/spike_list";
    /**
     * 秒杀详情url
     */
    private static String KILLDETAILSURL = HOSTNAME + "/h5/index.html#/spike_detail";
    /**
     * 砍价详情url
     */
    private static String BARGAININGDETAILSURL = HOSTNAME + "/h5/index.html#/bargain_detail";
    /**
     * 拼团详情url
     */
    private static String GROUPDETAILSURL = HOSTNAME + "/h5/index.html#/pintuan_detail";
    /**
     * 普通详情url
     */
    private static String DETAILSURL = HOSTNAME + "/h5/index.html#/detail";
    /**
     * 分享url
     */
    @GetMapping("/shareUrl")
    @ApiOperation(value = "分享url",notes = "分享url")
    @ApiImplicitParams({
    })
    public R shareUrl(
            NativeShareEntity nativeShareEntity
    ) {
        Assert.isNull(nativeShareEntity,"分享类型不能为空");
        int nativeShare = nativeShareEntity.getNativeShare();
        Assert.isNull(nativeShare,"分享类型不能为空");
        switch (Constants.NATIVESHARE.valueOf(nativeShare)) {
            case ONE: {
                Assert.isNull(nativeShareEntity.getStoreId(),"店铺id不能为空");
                return R.data(URLUtil.formatUrl(HttpUtil.urlWithForm(KILLLISTURL, JSONUtil.parseObj(nativeShareEntity), null, false)));
            }
            case TWO:{
                Assert.isNull(nativeShareEntity.getStoreId(),"店铺id不能为空");
                Assert.isNull(nativeShareEntity.getKillActivityId(),"秒杀活动id不能为空");
                return R.data(URLUtil.formatUrl(HttpUtil.urlWithForm(KILLDETAILSURL, JSONUtil.parseObj(nativeShareEntity), null, false)));
            }
            case THREE:{
                Assert.isNull(nativeShareEntity.getStoreId(),"店铺id不能为空");
                Assert.isNull(nativeShareEntity.getBargainingActivityId(),"砍价活动id不能为空");
                return R.data(URLUtil.formatUrl(HttpUtil.urlWithForm(BARGAININGDETAILSURL, JSONUtil.parseObj(nativeShareEntity), null, false)));
            }
            case FOUR:{
                Assert.isNull(nativeShareEntity.getStoreId(),"店铺id不能为空");
                Assert.isNull(nativeShareEntity.getGroupActivityId(),"拼团活动id不能为空");
                return R.data(URLUtil.formatUrl(HttpUtil.urlWithForm(GROUPDETAILSURL, JSONUtil.parseObj(nativeShareEntity), null, false)));
            }
            case FIVE:{
                Assert.isNull(nativeShareEntity.getStoreId(),"店铺id不能为空");
                Assert.isNull(nativeShareEntity.getCommodityId(),"商品id不能为空");
                return R.data(URLUtil.formatUrl(HttpUtil.urlWithForm(DETAILSURL, JSONUtil.parseObj(nativeShareEntity), null, false)));
            }
        }
        return R.ok();
    }


}
