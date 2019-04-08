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

import io.renren.modules.cellar.entity.CellarBargainingActivityDbEntity;
import io.renren.modules.cellar.service.CellarBargainingActivityDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 砍价活动表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-03 13:47:40
 */
@RestController
@RequestMapping("cellar/cellarbargainingactivitydb")
public class CellarBargainingActivityDbController {
    @Autowired
    private CellarBargainingActivityDbService cellarBargainingActivityDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarbargainingactivitydb:list")
    public R list(CellarBargainingActivityDbEntity cellarBargainingActivityDb){
        PageUtils page = cellarBargainingActivityDbService.queryPage(cellarBargainingActivityDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{bargainingActivityId}")
    @RequiresPermissions("cellar:cellarbargainingactivitydb:info")
    public R info(@PathVariable("bargainingActivityId") Long bargainingActivityId){
			CellarBargainingActivityDbEntity cellarBargainingActivityDb = cellarBargainingActivityDbService.getById(bargainingActivityId);

        return R.ok().put("cellarBargainingActivityDb", cellarBargainingActivityDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarbargainingactivitydb:save")
    public R save(@RequestBody CellarBargainingActivityDbEntity cellarBargainingActivityDb){
        cellarBargainingActivityDb.setCreateTime(new Date());
        cellarBargainingActivityDb.setState(Constants.STATE.zero.getKey());
        cellarBargainingActivityDbService.save(cellarBargainingActivityDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarbargainingactivitydb:update")
    public R update(@RequestBody CellarBargainingActivityDbEntity cellarBargainingActivityDb){
			cellarBargainingActivityDbService.updateById(cellarBargainingActivityDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarbargainingactivitydb:delete")
    public R delete(@RequestBody Long[] bargainingActivityIds){
			cellarBargainingActivityDbService.removeByIds(Arrays.asList(bargainingActivityIds));

        return R.ok();
    }

}
