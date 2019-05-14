package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberBargainingRecordDbEntity;
import io.renren.modules.cellar.service.CellarMemberBargainingRecordDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 会员砍价记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-03 16:45:35
 */
@RestController
@RequestMapping("cellar/cellarmemberbargainingrecorddb")
public class CellarMemberBargainingRecordDbController {
    @Autowired
    private CellarMemberBargainingRecordDbService cellarMemberBargainingRecordDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmemberbargainingrecorddb:list")
    public R list(CellarMemberBargainingRecordDbEntity cellarMemberBargainingRecordDb){
        PageUtils page = cellarMemberBargainingRecordDbService.queryPage(cellarMemberBargainingRecordDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberBargainingRecordId}")
    @RequiresPermissions("cellar:cellarmemberbargainingrecorddb:info")
    public R info(@PathVariable("memberBargainingRecordId") Long memberBargainingRecordId){
			CellarMemberBargainingRecordDbEntity cellarMemberBargainingRecordDb = cellarMemberBargainingRecordDbService.getById(memberBargainingRecordId);

        return R.ok().put("cellarMemberBargainingRecordDb", cellarMemberBargainingRecordDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmemberbargainingrecorddb:save")
    public R save(@RequestBody CellarMemberBargainingRecordDbEntity cellarMemberBargainingRecordDb){
			cellarMemberBargainingRecordDbService.save(cellarMemberBargainingRecordDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmemberbargainingrecorddb:update")
    public R update(@RequestBody CellarMemberBargainingRecordDbEntity cellarMemberBargainingRecordDb){
			cellarMemberBargainingRecordDbService.updateById(cellarMemberBargainingRecordDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmemberbargainingrecorddb:delete")
    public R delete(@RequestBody Long[] memberBargainingRecordIds){
			cellarMemberBargainingRecordDbService.removeByIds(Arrays.asList(memberBargainingRecordIds));

        return R.ok();
    }

}
