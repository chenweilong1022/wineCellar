package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarOrderDetailsDbEntity;
import io.renren.modules.cellar.service.CellarOrderDetailsDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 酒窖订单明细表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-01 13:16:42
 */
@RestController
@RequestMapping("cellar/cellarorderdetailsdb")
public class CellarOrderDetailsDbController {
    @Autowired
    private CellarOrderDetailsDbService cellarOrderDetailsDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarorderdetailsdb:list")
    public R list(CellarOrderDetailsDbEntity cellarOrderDetailsDb){
        PageUtils page = cellarOrderDetailsDbService.queryPage(cellarOrderDetailsDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderDetailsId}")
    @RequiresPermissions("cellar:cellarorderdetailsdb:info")
    public R info(@PathVariable("orderDetailsId") Long orderDetailsId){
			CellarOrderDetailsDbEntity cellarOrderDetailsDb = cellarOrderDetailsDbService.getById(orderDetailsId);

        return R.ok().put("cellarOrderDetailsDb", cellarOrderDetailsDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarorderdetailsdb:save")
    public R save(@RequestBody CellarOrderDetailsDbEntity cellarOrderDetailsDb){
			cellarOrderDetailsDbService.save(cellarOrderDetailsDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarorderdetailsdb:update")
    public R update(@RequestBody CellarOrderDetailsDbEntity cellarOrderDetailsDb){
			cellarOrderDetailsDbService.updateById(cellarOrderDetailsDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarorderdetailsdb:delete")
    public R delete(@RequestBody Long[] orderDetailsIds){
			cellarOrderDetailsDbService.removeByIds(Arrays.asList(orderDetailsIds));

        return R.ok();
    }

}
