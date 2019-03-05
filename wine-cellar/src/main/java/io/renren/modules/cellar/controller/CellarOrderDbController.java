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

import io.renren.modules.cellar.entity.CellarOrderDbEntity;
import io.renren.modules.cellar.service.CellarOrderDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 酒窖订单表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-01 13:16:42
 */
@RestController
@RequestMapping("cellar/cellarorderdb")
public class CellarOrderDbController {
    @Autowired
    private CellarOrderDbService cellarOrderDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarorderdb:list")
    public R list(CellarOrderDbEntity cellarOrderDb){
        PageUtils page = cellarOrderDbService.queryPage(cellarOrderDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderId}")
    @RequiresPermissions("cellar:cellarorderdb:info")
    public R info(@PathVariable("orderId") Long orderId){
			CellarOrderDbEntity cellarOrderDb = cellarOrderDbService.getById(orderId);

        return R.ok().put("cellarOrderDb", cellarOrderDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarorderdb:save")
    public R save(@RequestBody CellarOrderDbEntity cellarOrderDb){
			cellarOrderDbService.save(cellarOrderDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarorderdb:update")
    public R update(@RequestBody CellarOrderDbEntity cellarOrderDb){
			cellarOrderDbService.updateById(cellarOrderDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarorderdb:delete")
    public R delete(@RequestBody Long[] orderIds){
			cellarOrderDbService.removeByIds(Arrays.asList(orderIds));

        return R.ok();
    }

}
