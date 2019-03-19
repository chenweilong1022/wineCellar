package io.renren.modules.app.controller;

import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.cellar.entity.CellarAdvertisingPhotoDbEntity;
import io.renren.modules.cellar.entity.CellarCartDbEntity;
import io.renren.modules.cellar.service.CellarAdvertisingPhotoDbService;
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


/**
 * APP首页广告图表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-18 09:47:37
 */
@RestController
@RequestMapping("app/cellaradvertisingphotodb")
@Api(value="APP首页广告图表",tags="APP首页广告图表")
public class AppCellarAdvertisingPhotoDbController {
    @Autowired
    private CellarAdvertisingPhotoDbService cellarAdvertisingPhotoDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "APP首页广告图表",notes = "APP首页广告图表",response = CellarAdvertisingPhotoDbEntity.class)
    public R list(
            @ApiIgnore CellarAdvertisingPhotoDbEntity cellarAdvertisingPhotoDb
    ){
        PageUtils page = cellarAdvertisingPhotoDbService.queryPage(cellarAdvertisingPhotoDb);

        return R.data(page);
    }

}
