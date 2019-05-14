package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberCollectionDbEntity;
import io.renren.modules.cellar.service.CellarMemberCollectionDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 会员收藏表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-23 13:51:41
 */
@RestController
@RequestMapping("cellar/cellarmembercollectiondb")
public class CellarMemberCollectionDbController {
    @Autowired
    private CellarMemberCollectionDbService cellarMemberCollectionDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmembercollectiondb:list")
    public R list(CellarMemberCollectionDbEntity cellarMemberCollectionDb){
        PageUtils page = cellarMemberCollectionDbService.queryPage(cellarMemberCollectionDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberCollectionId}")
    @RequiresPermissions("cellar:cellarmembercollectiondb:info")
    public R info(@PathVariable("memberCollectionId") Long memberCollectionId){
			CellarMemberCollectionDbEntity cellarMemberCollectionDb = cellarMemberCollectionDbService.getById(memberCollectionId);

        return R.ok().put("cellarMemberCollectionDb", cellarMemberCollectionDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmembercollectiondb:save")
    public R save(@RequestBody CellarMemberCollectionDbEntity cellarMemberCollectionDb){
			cellarMemberCollectionDbService.save(cellarMemberCollectionDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmembercollectiondb:update")
    public R update(@RequestBody CellarMemberCollectionDbEntity cellarMemberCollectionDb){
			cellarMemberCollectionDbService.updateById(cellarMemberCollectionDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmembercollectiondb:delete")
    public R delete(@RequestBody Long[] memberCollectionIds){
			cellarMemberCollectionDbService.removeByIds(Arrays.asList(memberCollectionIds));

        return R.ok();
    }

}
