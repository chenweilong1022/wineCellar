package io.renren.modules.cellar.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.constants.Constants;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.cellar.entity.CellarConfigDbEntity;
import io.renren.modules.cellar.service.CellarConfigDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 全局配置表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-14 15:13:04
 */
@RestController
@RequestMapping("cellar/cellarconfigdb")
public class CellarConfigDbController {
    @Autowired
    private CellarConfigDbService cellarConfigDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarconfigdb:list")
    public R list(CellarConfigDbEntity cellarConfigDb){
        PageUtils page = cellarConfigDbService.queryPage(cellarConfigDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{configId}")
    @RequiresPermissions("cellar:cellarconfigdb:info")
    public R info(@PathVariable("configId") Long configId){
			CellarConfigDbEntity cellarConfigDb = cellarConfigDbService.getById(configId);

        return R.ok().put("cellarConfigDb", cellarConfigDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarconfigdb:save")
    public R save(@RequestBody CellarConfigDbEntity cellarConfigDb){
			cellarConfigDbService.save(cellarConfigDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarconfigdb:update")
    public R update(@RequestBody CellarConfigDbEntity cellarConfigDb){
        int count = cellarConfigDbService.count(null);
        if (count == Constants.Number.zero.getKey()) {
            cellarConfigDbService.save(cellarConfigDb);
        }else {
            cellarConfigDbService.updateById(cellarConfigDb);
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarconfigdb:delete")
    public R delete(@RequestBody Long[] configIds){
			cellarConfigDbService.removeByIds(Arrays.asList(configIds));

        return R.ok();
    }

}
