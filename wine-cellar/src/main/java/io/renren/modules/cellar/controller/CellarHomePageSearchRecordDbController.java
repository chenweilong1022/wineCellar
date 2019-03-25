package io.renren.modules.cellar.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.cellar.entity.CellarHomePageSearchRecordDbEntity;
import io.renren.modules.cellar.service.CellarHomePageSearchRecordDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 首页搜索记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-22 16:25:21
 */
@RestController
@RequestMapping("cellar/cellarhomepagesearchrecorddb")
public class CellarHomePageSearchRecordDbController {
    @Autowired
    private CellarHomePageSearchRecordDbService cellarHomePageSearchRecordDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarhomepagesearchrecorddb:list")
    public R list(CellarHomePageSearchRecordDbEntity cellarHomePageSearchRecordDb){
        PageUtils page = cellarHomePageSearchRecordDbService.queryPage(cellarHomePageSearchRecordDb);

        return R.ok().put("page", page);
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
