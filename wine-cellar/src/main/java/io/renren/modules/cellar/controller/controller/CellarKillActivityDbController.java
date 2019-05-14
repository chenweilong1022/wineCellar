package io.renren.modules.cellar.controller.controller;

import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarKillActivityDbEntity;
import io.renren.modules.cellar.service.CellarKillActivityDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;


/**
 * 秒杀活动表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-29 10:13:13
 */
@RestController
@RequestMapping("cellar/cellarkillactivitydb")
public class CellarKillActivityDbController {
    @Autowired
    private CellarKillActivityDbService cellarKillActivityDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarkillactivitydb:list")
    public R list(CellarKillActivityDbEntity cellarKillActivityDb){
        PageUtils page = cellarKillActivityDbService.queryPage(cellarKillActivityDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{killActivityId}")
    @RequiresPermissions("cellar:cellarkillactivitydb:info")
    public R info(@PathVariable("killActivityId") Long killActivityId){
			CellarKillActivityDbEntity cellarKillActivityDb = cellarKillActivityDbService.getById(killActivityId);

        return R.ok().put("cellarKillActivityDb", cellarKillActivityDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarkillactivitydb:save")
    public R save(@RequestBody CellarKillActivityDbEntity cellarKillActivityDb){
        cellarKillActivityDb.setState(Constants.STATE.zero.getKey());
        cellarKillActivityDb.setCreateTime(new Date());
        cellarKillActivityDbService.save(cellarKillActivityDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarkillactivitydb:update")
    public R update(@RequestBody CellarKillActivityDbEntity cellarKillActivityDb){
			cellarKillActivityDbService.updateById(cellarKillActivityDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarkillactivitydb:delete")
    public R delete(@RequestBody Long[] killActivityIds){
			cellarKillActivityDbService.removeByIds(Arrays.asList(killActivityIds));

        return R.ok();
    }

}
