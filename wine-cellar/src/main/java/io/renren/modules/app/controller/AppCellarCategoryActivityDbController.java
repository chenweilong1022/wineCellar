package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.cellar.entity.CellarCategoryActivityDbEntity;
import io.renren.modules.cellar.service.CellarCategoryActivityDbService;
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
 * APP分类活动表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-21 10:06:01
 */
@RestController
@RequestMapping("app/cellarcategoryactivitydb")
@Api(value="APP分类活动表",tags="APP分类活动表")
public class AppCellarCategoryActivityDbController {
    @Autowired
    private CellarCategoryActivityDbService cellarCategoryActivityDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "APP分类活动表列表",notes = "APP分类活动表列表",response = CellarCategoryActivityDbEntity.class)
    public R list(
            @ApiIgnore CellarCategoryActivityDbEntity cellarCategoryActivityDb
    ){
        PageUtils page = cellarCategoryActivityDbService.queryPage(cellarCategoryActivityDb);

        return R.data(page);
    }

}
