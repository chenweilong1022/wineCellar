package io.renren.modules.app.controller;

import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarCommodityDbEntity;
import io.renren.modules.cellar.entity.CellarCouponDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarCouponDbService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * APP优惠券表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-12 17:59:13
 */
@RestController
@RequestMapping("app/cellarcoupondb")
@Api(value="APP优惠券表",tags="APP优惠券表")
public class AppCellarCouponDbController extends AbstractController {
    @Autowired
    private CellarCouponDbService cellarCouponDbService;

    /**
     * 优惠券列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "优惠券列表",notes = "优惠券列表",response = CellarCouponDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="storeId",value="店铺id",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarCouponDbEntity cellarCouponDb
    ){

        PageUtils page = cellarCouponDbService.queryPage(cellarCouponDb);
        /**
         * 判断当前用户是否领取
         */
        List<CellarCouponDbEntity> list = (List<CellarCouponDbEntity>) page.getList();
        list.forEach(cellarCouponDbEntity -> cellarCouponDbEntity.setMemberId(cellarMemberDbEntity == null?null:cellarMemberDbEntity.getMemberId()));

        return R.data(page);
    }
}
