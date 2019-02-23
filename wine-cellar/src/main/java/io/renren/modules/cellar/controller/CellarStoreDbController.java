package io.renren.modules.cellar.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.common.constants.Constants;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.cellar.entity.CellarStoreDbEntity;
import io.renren.modules.cellar.service.CellarStoreDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 酒窖店铺表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
@RestController
@RequestMapping("cellar/cellarstoredb")
public class CellarStoreDbController {
    @Autowired
    private CellarStoreDbService cellarStoreDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarstoredb:list")
    public R list(CellarStoreDbEntity cellarStoreDb){
        PageUtils page = cellarStoreDbService.queryPage(cellarStoreDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{storeId}")
    @RequiresPermissions("cellar:cellarstoredb:info")
    public R info(@PathVariable("storeId") Long storeId){
			CellarStoreDbEntity cellarStoreDb = cellarStoreDbService.getById(storeId);

        return R.ok().put("cellarStoreDb", cellarStoreDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarstoredb:save")
    public R save(@RequestBody CellarStoreDbEntity cellarStoreDb){
        cellarStoreDb.setCreateTime(new Date());
        cellarStoreDb.setState(Constants.STATE.zero.getKey());
        cellarStoreDbService.save(cellarStoreDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarstoredb:update")
    public R update(@RequestBody CellarStoreDbEntity cellarStoreDb){
			cellarStoreDbService.updateById(cellarStoreDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarstoredb:delete")
    public R delete(@RequestBody Long[] storeIds){
			cellarStoreDbService.removeByIds(Arrays.asList(storeIds));

        return R.ok();
    }

}
