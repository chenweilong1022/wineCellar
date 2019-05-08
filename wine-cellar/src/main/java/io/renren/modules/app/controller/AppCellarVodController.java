package io.renren.modules.app.controller;

import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.egzosn.pay.common.bean.PayOutMessage;
import io.renren.common.constants.Constants;
import io.renren.common.utils.R;
import io.renren.common.utils.pay.CallbackUtil;
import io.renren.common.utils.pay.WechatPayUtil;
import io.renren.common.utils.vod.VodUtil;
import io.renren.config.vod.StsUtil;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.entity.CellarStoreDbEntity;
import io.renren.modules.cellar.service.CellarMemberDbService;
import io.renren.modules.cellar.service.CellarOrderDbService;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.StringJoiner;


/**
 * APP酒窖支付回调
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-01 13:16:42
 */
@RestController
@RequestMapping("app/cellarvod")
@Api(value="app酒窖vod视频接口",tags="app酒窖vod视频接口")
public class AppCellarVodController extends AbstractController {
    @Autowired
    private CellarOrderDbService cellarOrderDbService;
    @Autowired
    private CellarMemberDbService cellarMemberDbService;


    /**
     * 获取视频上传地址和凭证
     */
    @GetMapping("/assumeRole")
//    @Login
    @ApiOperation(value = "获取sts临时权限",notes = "获取sts临时权限",response = AssumeRoleResponse.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
    })
    public R assumeRole(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity
    ){
        AssumeRoleResponse sts = null;
        try {
            sts = StsUtil.assumeRole(cellarMemberDbEntity == null? RandomUtil.randomNumbers(6):cellarMemberDbEntity.getMemberId().toString());
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return R.data(sts);
    }


    /**
     * 获取视频上传地址和凭证
     */
    @GetMapping("/createUploadVideo")
    @ApiOperation(value = "获取视频上传地址和凭证",notes = "获取视频上传地址和凭证",response = CreateUploadVideoResponse.class)
    @ApiImplicitParams({
//            @ApiImplicitParam(name="key",value="搜索条件",dataType="String",required=false,paramType="query"),
    })
    public R createUploadVideo(
    ){
        CreateUploadVideoResponse uploadVideo = VodUtil.createUploadVideo();
        return R.data(uploadVideo);
    }

}
