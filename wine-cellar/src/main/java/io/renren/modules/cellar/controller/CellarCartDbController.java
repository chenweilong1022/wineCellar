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

import io.renren.modules.cellar.entity.CellarCartDbEntity;
import io.renren.modules.cellar.service.CellarCartDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 酒窖购物车
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-28 13:42:16
 */
@RestController
@RequestMapping("cellar/cellarcartdb")
public class CellarCartDbController {
    @Autowired
    private CellarCartDbService cellarCartDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarcartdb:list")
    public R list(CellarCartDbEntity cellarCartDb){
        PageUtils page = cellarCartDbService.queryPage(cellarCartDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{cartId}")
    @RequiresPermissions("cellar:cellarcartdb:info")
    public R info(@PathVariable("cartId") Long cartId){
			CellarCartDbEntity cellarCartDb = cellarCartDbService.getById(cartId);

        return R.ok().put("cellarCartDb", cellarCartDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarcartdb:save")
    public R save(@RequestBody CellarCartDbEntity cellarCartDb){
			cellarCartDbService.save(cellarCartDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarcartdb:update")
    public R update(@RequestBody CellarCartDbEntity cellarCartDb){
			cellarCartDbService.updateById(cellarCartDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarcartdb:delete")
    public R delete(@RequestBody Long[] cartIds){
			cellarCartDbService.removeByIds(Arrays.asList(cartIds));

        return R.ok();
    }

}
