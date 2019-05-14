package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberFootprintDbEntity;
import io.renren.modules.cellar.service.CellarMemberFootprintDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 会员足迹表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-25 17:29:13
 */
@RestController
@RequestMapping("cellar/cellarmemberfootprintdb")
public class CellarMemberFootprintDbController {
    @Autowired
    private CellarMemberFootprintDbService cellarMemberFootprintDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmemberfootprintdb:list")
    public R list(CellarMemberFootprintDbEntity cellarMemberFootprintDb){
        PageUtils page = cellarMemberFootprintDbService.queryPage(cellarMemberFootprintDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberFootprintId}")
    @RequiresPermissions("cellar:cellarmemberfootprintdb:info")
    public R info(@PathVariable("memberFootprintId") Long memberFootprintId){
			CellarMemberFootprintDbEntity cellarMemberFootprintDb = cellarMemberFootprintDbService.getById(memberFootprintId);

        return R.ok().put("cellarMemberFootprintDb", cellarMemberFootprintDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmemberfootprintdb:save")
    public R save(@RequestBody CellarMemberFootprintDbEntity cellarMemberFootprintDb){
			cellarMemberFootprintDbService.save(cellarMemberFootprintDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmemberfootprintdb:update")
    public R update(@RequestBody CellarMemberFootprintDbEntity cellarMemberFootprintDb){
			cellarMemberFootprintDbService.updateById(cellarMemberFootprintDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmemberfootprintdb:delete")
    public R delete(@RequestBody Long[] memberFootprintIds){
			cellarMemberFootprintDbService.removeByIds(Arrays.asList(memberFootprintIds));

        return R.ok();
    }

}
