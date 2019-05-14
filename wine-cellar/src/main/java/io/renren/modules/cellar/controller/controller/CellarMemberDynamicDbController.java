package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberDynamicDbEntity;
import io.renren.modules.cellar.service.CellarMemberDynamicDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 会员动态表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-05-13 09:58:25
 */
@RestController
@RequestMapping("cellar/cellarmemberdynamicdb")
public class CellarMemberDynamicDbController {
    @Autowired
    private CellarMemberDynamicDbService cellarMemberDynamicDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmemberdynamicdb:list")
    public R list(CellarMemberDynamicDbEntity cellarMemberDynamicDb){
        PageUtils page = cellarMemberDynamicDbService.queryPage(cellarMemberDynamicDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberDynamicId}")
    @RequiresPermissions("cellar:cellarmemberdynamicdb:info")
    public R info(@PathVariable("memberDynamicId") Long memberDynamicId){
			CellarMemberDynamicDbEntity cellarMemberDynamicDb = cellarMemberDynamicDbService.getById(memberDynamicId);

        return R.ok().put("cellarMemberDynamicDb", cellarMemberDynamicDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmemberdynamicdb:save")
    public R save(@RequestBody CellarMemberDynamicDbEntity cellarMemberDynamicDb){
			cellarMemberDynamicDbService.save(cellarMemberDynamicDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmemberdynamicdb:update")
    public R update(@RequestBody CellarMemberDynamicDbEntity cellarMemberDynamicDb){
			cellarMemberDynamicDbService.updateById(cellarMemberDynamicDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmemberdynamicdb:delete")
    public R delete(@RequestBody Long[] memberDynamicIds){
			cellarMemberDynamicDbService.removeByIds(Arrays.asList(memberDynamicIds));

        return R.ok();
    }

}
