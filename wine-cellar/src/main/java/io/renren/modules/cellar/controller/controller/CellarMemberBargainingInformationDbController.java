package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberBargainingInformationDbEntity;
import io.renren.modules.cellar.service.CellarMemberBargainingInformationDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 会员砍价信息表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-03 15:22:05
 */
@RestController
@RequestMapping("cellar/cellarmemberbargaininginformationdb")
public class CellarMemberBargainingInformationDbController {
    @Autowired
    private CellarMemberBargainingInformationDbService cellarMemberBargainingInformationDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmemberbargaininginformationdb:list")
    public R list(CellarMemberBargainingInformationDbEntity cellarMemberBargainingInformationDb){
        PageUtils page = cellarMemberBargainingInformationDbService.queryPage(cellarMemberBargainingInformationDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberBargainingInformationId}")
    @RequiresPermissions("cellar:cellarmemberbargaininginformationdb:info")
    public R info(@PathVariable("memberBargainingInformationId") Long memberBargainingInformationId){
			CellarMemberBargainingInformationDbEntity cellarMemberBargainingInformationDb = cellarMemberBargainingInformationDbService.getById(memberBargainingInformationId);

        return R.ok().put("cellarMemberBargainingInformationDb", cellarMemberBargainingInformationDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmemberbargaininginformationdb:save")
    public R save(@RequestBody CellarMemberBargainingInformationDbEntity cellarMemberBargainingInformationDb){
			cellarMemberBargainingInformationDbService.save(cellarMemberBargainingInformationDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmemberbargaininginformationdb:update")
    public R update(@RequestBody CellarMemberBargainingInformationDbEntity cellarMemberBargainingInformationDb){
			cellarMemberBargainingInformationDbService.updateById(cellarMemberBargainingInformationDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmemberbargaininginformationdb:delete")
    public R delete(@RequestBody Long[] memberBargainingInformationIds){
			cellarMemberBargainingInformationDbService.removeByIds(Arrays.asList(memberBargainingInformationIds));

        return R.ok();
    }

}
