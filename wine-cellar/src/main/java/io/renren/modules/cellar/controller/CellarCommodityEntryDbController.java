package io.renren.modules.cellar.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.cellar.entity.CellarFranchiseDbEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.cellar.entity.CellarCommodityEntryDbEntity;
import io.renren.modules.cellar.service.CellarCommodityEntryDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 产品入驻表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-27 14:36:40
 */
@RestController
@RequestMapping("cellar/cellarcommodityentrydb")
public class CellarCommodityEntryDbController {
    @Autowired
    private CellarCommodityEntryDbService cellarCommodityEntryDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarcommodityentrydb:list")
    public R list(CellarCommodityEntryDbEntity cellarCommodityEntryDb){
        PageUtils page = cellarCommodityEntryDbService.queryPage(cellarCommodityEntryDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{commodityEntryId}")
    @RequiresPermissions("cellar:cellarcommodityentrydb:info")
    public R info(@PathVariable("commodityEntryId") Long commodityEntryId){
			CellarCommodityEntryDbEntity cellarCommodityEntryDb = cellarCommodityEntryDbService.getById(commodityEntryId);

        return R.ok().put("cellarCommodityEntryDb", cellarCommodityEntryDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarcommodityentrydb:save")
    public R save(@RequestBody CellarCommodityEntryDbEntity cellarCommodityEntryDb){
			cellarCommodityEntryDbService.save(cellarCommodityEntryDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarcommodityentrydb:update")
    public R update(@RequestBody CellarCommodityEntryDbEntity cellarCommodityEntryDb){
			cellarCommodityEntryDbService.updateById(cellarCommodityEntryDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarcommodityentrydb:delete")
    public R delete(@RequestBody Long[] commodityEntryIds){
			cellarCommodityEntryDbService.removeByIds(Arrays.asList(commodityEntryIds));

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/review")
    @RequiresPermissions("cellar:cellarcommodityentrydb:update")
    public R review(
            @RequestBody CellarCommodityEntryDbEntity cellarCommodityEntryDbEntity
    ){

        for (Long commodityEntryId : cellarCommodityEntryDbEntity.getCommodityEntryIds()) {
            CellarCommodityEntryDbEntity cellarCommodityEntryDbEntityReview = new CellarCommodityEntryDbEntity();
            cellarCommodityEntryDbEntityReview.setCommodityEntryId(commodityEntryId);
            cellarCommodityEntryDbEntityReview.setReviewStatus(cellarCommodityEntryDbEntity.getReviewStatus());
            cellarCommodityEntryDbService.updateById(cellarCommodityEntryDbEntityReview);
        }

        return R.ok();
    }

}
