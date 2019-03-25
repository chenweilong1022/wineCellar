package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarCouponDbEntity;
import io.renren.modules.cellar.entity.CellarHomePageSearchRecordDbEntity;
import io.renren.modules.cellar.service.CellarHomePageSearchRecordDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.List;


/**
 * APP首页搜索记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-22 16:25:21
 */
@RestController
@RequestMapping("app/cellarhomepagesearchrecorddb")
@Api(value="APP首页搜索记录表",tags="APP首页搜索记录表")
public class AppCellarHomePageSearchRecordDbController {
    @Autowired
    private CellarHomePageSearchRecordDbService cellarHomePageSearchRecordDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "搜索记录列表",notes = "搜索记录列表",response = CellarHomePageSearchRecordDbEntity.class)
    public R list(
            @ApiIgnore CellarHomePageSearchRecordDbEntity cellarHomePageSearchRecordDb
    ){
        List<CellarHomePageSearchRecordDbEntity> list = cellarHomePageSearchRecordDbService.list(new QueryWrapper<CellarHomePageSearchRecordDbEntity>().lambda()
                .eq(CellarHomePageSearchRecordDbEntity::getState, Constants.STATE.zero.getKey())
                .orderByDesc(CellarHomePageSearchRecordDbEntity::getSearchNumber)
                .last(" limit 0,6")
        );

        return R.data(list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{homePageSearchRecordId}")
    @RequiresPermissions("cellar:cellarhomepagesearchrecorddb:info")
    public R info(@PathVariable("homePageSearchRecordId") Long homePageSearchRecordId){
			CellarHomePageSearchRecordDbEntity cellarHomePageSearchRecordDb = cellarHomePageSearchRecordDbService.getById(homePageSearchRecordId);

        return R.ok().put("cellarHomePageSearchRecordDb", cellarHomePageSearchRecordDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarhomepagesearchrecorddb:save")
    public R save(@RequestBody CellarHomePageSearchRecordDbEntity cellarHomePageSearchRecordDb){
			cellarHomePageSearchRecordDbService.save(cellarHomePageSearchRecordDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarhomepagesearchrecorddb:update")
    public R update(@RequestBody CellarHomePageSearchRecordDbEntity cellarHomePageSearchRecordDb){
			cellarHomePageSearchRecordDbService.updateById(cellarHomePageSearchRecordDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarhomepagesearchrecorddb:delete")
    public R delete(@RequestBody Long[] homePageSearchRecordIds){
			cellarHomePageSearchRecordDbService.removeByIds(Arrays.asList(homePageSearchRecordIds));

        return R.ok();
    }

}
