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

import io.renren.modules.cellar.entity.CellarMemberDynamicThumbDbEntity;
import io.renren.modules.cellar.service.CellarMemberDynamicThumbDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 会员动态点赞表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-05-14 17:54:33
 */
@RestController
@RequestMapping("cellar/cellarmemberdynamicthumbdb")
public class CellarMemberDynamicThumbDbController {
    @Autowired
    private CellarMemberDynamicThumbDbService cellarMemberDynamicThumbDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmemberdynamicthumbdb:list")
    public R list(CellarMemberDynamicThumbDbEntity cellarMemberDynamicThumbDb){
        PageUtils page = cellarMemberDynamicThumbDbService.queryPage(cellarMemberDynamicThumbDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberDynamicThumbId}")
    @RequiresPermissions("cellar:cellarmemberdynamicthumbdb:info")
    public R info(@PathVariable("memberDynamicThumbId") Long memberDynamicThumbId){
			CellarMemberDynamicThumbDbEntity cellarMemberDynamicThumbDb = cellarMemberDynamicThumbDbService.getById(memberDynamicThumbId);

        return R.ok().put("cellarMemberDynamicThumbDb", cellarMemberDynamicThumbDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmemberdynamicthumbdb:save")
    public R save(@RequestBody CellarMemberDynamicThumbDbEntity cellarMemberDynamicThumbDb){
			cellarMemberDynamicThumbDbService.save(cellarMemberDynamicThumbDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmemberdynamicthumbdb:update")
    public R update(@RequestBody CellarMemberDynamicThumbDbEntity cellarMemberDynamicThumbDb){
			cellarMemberDynamicThumbDbService.updateById(cellarMemberDynamicThumbDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmemberdynamicthumbdb:delete")
    public R delete(@RequestBody Long[] memberDynamicThumbIds){
			cellarMemberDynamicThumbDbService.removeByIds(Arrays.asList(memberDynamicThumbIds));

        return R.ok();
    }

}
