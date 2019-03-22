package io.renren.modules.cellar.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.common.constants.Constants;
import io.renren.modules.cellar.entity.CellarCommodityDbEntity;
import io.renren.modules.cellar.entity.CellarHandpickCommodityReviewDbEntity;
import io.renren.modules.cellar.service.CellarCommodityDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.cellar.entity.CellarCategoryActivityCommodityReviewDbEntity;
import io.renren.modules.cellar.service.CellarCategoryActivityCommodityReviewDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 分类活动商品审核表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-21 13:43:20
 */
@RestController
@RequestMapping("cellar/cellarcategoryactivitycommodityreviewdb")
public class CellarCategoryActivityCommodityReviewDbController {
    @Autowired
    private CellarCategoryActivityCommodityReviewDbService cellarCategoryActivityCommodityReviewDbService;
    @Autowired
    private CellarCommodityDbService cellarCommodityDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarcategoryactivitycommodityreviewdb:list")
    public R list(CellarCategoryActivityCommodityReviewDbEntity cellarCategoryActivityCommodityReviewDb){
        PageUtils page = cellarCategoryActivityCommodityReviewDbService.queryPage(cellarCategoryActivityCommodityReviewDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{categoryActivityCommodityReviewId}")
    @RequiresPermissions("cellar:cellarcategoryactivitycommodityreviewdb:info")
    public R info(@PathVariable("categoryActivityCommodityReviewId") Long categoryActivityCommodityReviewId){
			CellarCategoryActivityCommodityReviewDbEntity cellarCategoryActivityCommodityReviewDb = cellarCategoryActivityCommodityReviewDbService.getById(categoryActivityCommodityReviewId);

        return R.ok().put("cellarCategoryActivityCommodityReviewDb", cellarCategoryActivityCommodityReviewDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarcategoryactivitycommodityreviewdb:save")
    public R save(@RequestBody CellarCategoryActivityCommodityReviewDbEntity cellarCategoryActivityCommodityReviewDb){
        /**
         * 保存商品审核
         */
        cellarCategoryActivityCommodityReviewDb.setCreateTime(new Date());
        cellarCategoryActivityCommodityReviewDb.setReviewStatus(Constants.REVIEWSTATUS.zero.getKey());
        cellarCategoryActivityCommodityReviewDb.setState(Constants.STATE.zero.getKey());
        cellarCategoryActivityCommodityReviewDbService.save(cellarCategoryActivityCommodityReviewDb);
        /**
         * 修改商品为审核中
         */
        CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(cellarCategoryActivityCommodityReviewDb.getCommodityId());
        cellarCommodityDbEntity.setHaveCategoryActivity(Constants.HAVECATEGORYACTIVITY.REVIEW.getKey());
        cellarCommodityDbService.updateById(cellarCommodityDbEntity);

        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/review")
    @RequiresPermissions("cellar:cellarhandpickcommodityreviewdb:update")
    public R review(@RequestBody CellarCategoryActivityCommodityReviewDbEntity cellarCategoryActivityCommodityReviewDb){


        for (Long categoryActivityCommodityReviewId : cellarCategoryActivityCommodityReviewDb.getCategoryActivityCommodityReviewIds()) {

            CellarCategoryActivityCommodityReviewDbEntity cellarCategoryActivityCommodityReviewDbEntity = new CellarCategoryActivityCommodityReviewDbEntity();
            cellarCategoryActivityCommodityReviewDbEntity.setCategoryActivityCommodityReviewId(categoryActivityCommodityReviewId);
            cellarCategoryActivityCommodityReviewDbEntity.setReviewStatus(cellarCategoryActivityCommodityReviewDb.getReviewStatus());
            cellarCategoryActivityCommodityReviewDbService.updateById(cellarCategoryActivityCommodityReviewDbEntity);

            CellarCategoryActivityCommodityReviewDbEntity categoryActivityCommodityReviewDbEntity = cellarCategoryActivityCommodityReviewDbService.getById(categoryActivityCommodityReviewId);

            /**
             * 查询商品信息
             */
            CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(categoryActivityCommodityReviewDbEntity.getCommodityId());
            /**
             * 审核通过
             */
            if (cellarCategoryActivityCommodityReviewDb.getReviewStatus().equals(Constants.REVIEWSTATUS.one.getKey())) {
                cellarCommodityDbEntity.setHaveCategoryActivity(Constants.HAVECATEGORYACTIVITY.YES.getKey());
                cellarCommodityDbEntity.setCategoryActivityId(categoryActivityCommodityReviewDbEntity.getCategoryActivityId());
            /**
             * 审核不通过
              */
            }else if (cellarCategoryActivityCommodityReviewDb.getReviewStatus().equals(Constants.REVIEWSTATUS.fuone.getKey())) {
                cellarCommodityDbEntity.setHaveCategoryActivity(Constants.HAVECATEGORYACTIVITY.NO.getKey());
            }
            cellarCommodityDbService.updateById(cellarCommodityDbEntity);

        }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarcategoryactivitycommodityreviewdb:update")
    public R update(@RequestBody CellarCategoryActivityCommodityReviewDbEntity cellarCategoryActivityCommodityReviewDb){
			cellarCategoryActivityCommodityReviewDbService.updateById(cellarCategoryActivityCommodityReviewDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarcategoryactivitycommodityreviewdb:delete")
    public R delete(@RequestBody Long[] categoryActivityCommodityReviewIds){
        for (Long categoryActivityCommodityReviewId : categoryActivityCommodityReviewIds) {
            /**
             * 获取分类活动商品审核信息
             */
            CellarCategoryActivityCommodityReviewDbEntity cellarCategoryActivityCommodityReviewDbEntity = cellarCategoryActivityCommodityReviewDbService.getById(categoryActivityCommodityReviewId);
            /**
             * 获取商品信息
             */
            if (cellarCategoryActivityCommodityReviewDbEntity.getReviewStatus().equals(Constants.REVIEWSTATUS.one.getKey())) {
                CellarCommodityDbEntity cellarCommodityDbEntity = cellarCategoryActivityCommodityReviewDbEntity.getCellarCommodityDbEntity();
                cellarCommodityDbEntity.setHaveCategoryActivity(Constants.HAVECATEGORYACTIVITY.NO.getKey());
                /**
                 * 修改为非审核
                 */
                cellarCommodityDbService.updateById(cellarCommodityDbEntity);
            }
        }
		cellarCategoryActivityCommodityReviewDbService.removeByIds(Arrays.asList(categoryActivityCommodityReviewIds));
        return R.ok();
    }

}
