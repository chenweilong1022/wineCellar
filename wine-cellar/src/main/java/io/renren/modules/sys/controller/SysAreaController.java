package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.AreaConfig;
import io.renren.config.StartupLoading;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.SysAreaEntity;
import io.renren.modules.sys.service.SysAreaService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 地域信息表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 15:12:55
 */
@RestController
@RequestMapping("cellar/sysarea")
public class SysAreaController {
    @Autowired
    private SysAreaService sysAreaService;
    @Autowired
    private AreaConfig areaConfig;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:sysarea:list")
    public R list(SysAreaEntity sysArea){
        PageUtils page = sysAreaService.queryPage(sysArea);

        return R.ok().put("page", page);
    }

    /**
     * 三级联动列表
     */
    @RequestMapping("/listThreeLevel")
    public R listThreeLevel(){
        if (areaConfig.SYS_AREA_ENTITIES != null) {
            return R.data(areaConfig.SYS_AREA_ENTITIES);
        }
        areaConfig.SYS_AREA_ENTITIES = sysAreaService.listThreeLevel();
        return R.data(areaConfig.SYS_AREA_ENTITIES);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cellar:sysarea:info")
    public R info(@PathVariable("id") Integer id){
			SysAreaEntity sysArea = sysAreaService.getById(id);

        return R.ok().put("sysArea", sysArea);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:sysarea:save")
    public R save(@RequestBody SysAreaEntity sysArea){
			sysAreaService.save(sysArea);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:sysarea:update")
    public R update(@RequestBody SysAreaEntity sysArea){
			sysAreaService.updateById(sysArea);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:sysarea:delete")
    public R delete(@RequestBody Integer[] ids){
			sysAreaService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
