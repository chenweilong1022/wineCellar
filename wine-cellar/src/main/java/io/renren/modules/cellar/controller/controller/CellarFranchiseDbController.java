package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarFranchiseDbEntity;
import io.renren.modules.cellar.service.CellarFranchiseDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 酒窖加盟表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-27 13:25:34
 */
@RestController
@RequestMapping("cellar/cellarfranchisedb")
public class CellarFranchiseDbController {
    @Autowired
    private CellarFranchiseDbService cellarFranchiseDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarfranchisedb:list")
    public R list(CellarFranchiseDbEntity cellarFranchiseDb){
        PageUtils page = cellarFranchiseDbService.queryPage(cellarFranchiseDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{franchiseId}")
    @RequiresPermissions("cellar:cellarfranchisedb:info")
    public R info(@PathVariable("franchiseId") Long franchiseId){
			CellarFranchiseDbEntity cellarFranchiseDb = cellarFranchiseDbService.getById(franchiseId);

        return R.ok().put("cellarFranchiseDb", cellarFranchiseDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarfranchisedb:save")
    public R save(@RequestBody CellarFranchiseDbEntity cellarFranchiseDb){
			cellarFranchiseDbService.save(cellarFranchiseDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarfranchisedb:update")
    public R update(@RequestBody CellarFranchiseDbEntity cellarFranchiseDb){
			cellarFranchiseDbService.updateById(cellarFranchiseDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarfranchisedb:delete")
    public R delete(@RequestBody Long[] franchiseIds){
			cellarFranchiseDbService.removeByIds(Arrays.asList(franchiseIds));

        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping("/review")
    @RequiresPermissions("cellar:cellarfranchisedb:update")
    public R review(
            @RequestBody CellarFranchiseDbEntity cellarFranchiseDbEntity
    ){

        for (Long franchiseId : cellarFranchiseDbEntity.getFranchiseIds()) {
            CellarFranchiseDbEntity cellarFranchiseDbEntityReview = new CellarFranchiseDbEntity();
            cellarFranchiseDbEntityReview.setFranchiseId(franchiseId);
            cellarFranchiseDbEntityReview.setReviewStatus(cellarFranchiseDbEntity.getReviewStatus());
            cellarFranchiseDbService.updateById(cellarFranchiseDbEntityReview);
        }

        return R.ok();
    }

}
