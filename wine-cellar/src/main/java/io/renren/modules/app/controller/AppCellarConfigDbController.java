package io.renren.modules.app.controller;

import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarConfigDbEntity;
import io.renren.modules.cellar.entity.CellarCouponDbEntity;
import io.renren.modules.cellar.service.CellarConfigDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * APP全局配置表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-14 15:13:04
 */
@RestController
@RequestMapping("app/cellarconfigdb")
@Api(value="APP全局配置表",tags="APP全局配置表")
public class AppCellarConfigDbController {
    @Autowired
    private CellarConfigDbService cellarConfigDbService;

    /**
     * 信息
     */
    @GetMapping("/info")
    @ApiOperation(value = "全局配置信息",notes = "全局配置信息",response = CellarConfigDbEntity.class)
    public R info(){
        CellarConfigDbEntity cellarConfigDb = cellarConfigDbService.getById(Constants.Number.one.getKey());
        return R.data(cellarConfigDb);
    }

}
