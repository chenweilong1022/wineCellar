package io.renren.modules.cellar.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.common.constants.Constants;
import io.renren.modules.cellar.entity.CellarCommodityDbEntity;
import io.renren.modules.cellar.service.CellarCommodityDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.cellar.entity.CellarHandpickCommodityReviewDbEntity;
import io.renren.modules.cellar.service.CellarHandpickCommodityReviewDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 精选商品审核表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-20 10:56:44
 */
@RestController
@RequestMapping("cellar/cellarhandpickcommodityreviewdb")
public class CellarHandpickCommodityReviewDbController {
    @Autowired
    private CellarHandpickCommodityReviewDbService cellarHandpickCommodityReviewDbService;
    @Autowired
    private CellarCommodityDbService cellarCommodityDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarhandpickcommodityreviewdb:list")
    public R list(CellarHandpickCommodityReviewDbEntity cellarHandpickCommodityReviewDb){
        PageUtils page = cellarHandpickCommodityReviewDbService.queryPage(cellarHandpickCommodityReviewDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{handpickCommodityReviewId}")
    @RequiresPermissions("cellar:cellarhandpickcommodityreviewdb:info")
    public R info(@PathVariable("handpickCommodityReviewId") Long handpickCommodityReviewId){
			CellarHandpickCommodityReviewDbEntity cellarHandpickCommodityReviewDb = cellarHandpickCommodityReviewDbService.getById(handpickCommodityReviewId);

        return R.ok().put("cellarHandpickCommodityReviewDb", cellarHandpickCommodityReviewDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarhandpickcommodityreviewdb:save")
    public R save(@RequestBody CellarHandpickCommodityReviewDbEntity cellarHandpickCommodityReviewDb){
        cellarHandpickCommodityReviewDb.setState(Constants.STATE.zero.getKey());
        cellarHandpickCommodityReviewDb.setCreateTime(new Date());
        cellarHandpickCommodityReviewDb.setReviewStatus(Constants.REVIEWSTATUS.zero.getKey());
        cellarHandpickCommodityReviewDbService.save(cellarHandpickCommodityReviewDb);

        return R.ok();
    }

    /**
     * 精选商品
     */
    @RequestMapping("/handpick")
    @RequiresPermissions("cellar:cellarhandpickcommodityreviewdb:save")
    public R handpick(@RequestBody Long[] handpickCommodityReviewIds){

        for (Long commodityId : handpickCommodityReviewIds) {
            CellarHandpickCommodityReviewDbEntity cellarHandpickCommodityReviewDb = new CellarHandpickCommodityReviewDbEntity();
            cellarHandpickCommodityReviewDb.setState(Constants.STATE.zero.getKey());
            cellarHandpickCommodityReviewDb.setCreateTime(new Date());
            cellarHandpickCommodityReviewDb.setReviewStatus(Constants.REVIEWSTATUS.zero.getKey());
            cellarHandpickCommodityReviewDb.setCommodityId(commodityId);
            cellarHandpickCommodityReviewDbService.save(cellarHandpickCommodityReviewDb);
            /**
             * 修改精选商品为审核中
             */
            CellarCommodityDbEntity commodityDbEntity = cellarCommodityDbService.getById(commodityId);
            commodityDbEntity.setHaveHandpick(Constants.HAVEHANDPICK.REVIEW.getKey());
            cellarCommodityDbService.updateById(commodityDbEntity);
        }

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarhandpickcommodityreviewdb:update")
    public R update(@RequestBody CellarHandpickCommodityReviewDbEntity cellarHandpickCommodityReviewDb){
			cellarHandpickCommodityReviewDbService.updateById(cellarHandpickCommodityReviewDb);

        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/review")
    @RequiresPermissions("cellar:cellarhandpickcommodityreviewdb:update")
    public R review(@RequestBody CellarHandpickCommodityReviewDbEntity cellarHandpickCommodityReviewDb){

        for (Long handpickCommodityReviewId : cellarHandpickCommodityReviewDb.getHandpickCommodityReviewIds()) {
            CellarHandpickCommodityReviewDbEntity handpickCommodityReviewDbEntity = new CellarHandpickCommodityReviewDbEntity();
            handpickCommodityReviewDbEntity.setHandpickCommodityReviewId(handpickCommodityReviewId);
            handpickCommodityReviewDbEntity.setReviewStatus(cellarHandpickCommodityReviewDb.getReviewStatus());
            cellarHandpickCommodityReviewDbService.updateById(handpickCommodityReviewDbEntity);
            /**
             * 查询审核表 根据审核id
             * 获取到商品信息
             * 修改为精选商品
             */
            CellarHandpickCommodityReviewDbEntity reviewDbEntity = cellarHandpickCommodityReviewDbService.getById(handpickCommodityReviewId);
            CellarCommodityDbEntity commodityDbEntity = cellarCommodityDbService.getById(reviewDbEntity.getCommodityId());
            /**
             * 如果审核通过
             */
            if (cellarHandpickCommodityReviewDb.getReviewStatus().equals(Constants.REVIEWSTATUS.one.getKey())) {
                commodityDbEntity.setHaveHandpick(Constants.HAVEHANDPICK.YES.getKey());
            }else if (cellarHandpickCommodityReviewDb.getReviewStatus().equals(Constants.REVIEWSTATUS.fuone.getKey())) {
                commodityDbEntity.setHaveHandpick(Constants.HAVEHANDPICK.NO.getKey());
            }
            cellarCommodityDbService.updateById(commodityDbEntity);
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarhandpickcommodityreviewdb:delete")
    public R delete(@RequestBody Long[] handpickCommodityReviewIds){

        for (Long handpickCommodityReviewId : handpickCommodityReviewIds) {
            /**
             * 查询精选商品审核表
             * 获取商品信息
             * 设置为非精选商品
             */
            CellarHandpickCommodityReviewDbEntity handpickCommodityReviewDbEntity = cellarHandpickCommodityReviewDbService.getById(handpickCommodityReviewId);
            /**
             * 如果是审核通过的精选商品 删除之后将商品置为非精选商品
             */
            if (handpickCommodityReviewDbEntity.getReviewStatus().equals(Constants.REVIEWSTATUS.one.getKey())) {
                CellarCommodityDbEntity cellarCommodityDbEntity = handpickCommodityReviewDbEntity.getCellarCommodityDbEntity();
                cellarCommodityDbEntity.setHaveHandpick(Constants.HAVEHANDPICK.NO.getKey());
                cellarCommodityDbService.updateById(cellarCommodityDbEntity);
            }
        }
        cellarHandpickCommodityReviewDbService.removeByIds(Arrays.asList(handpickCommodityReviewIds));
        return R.ok();
    }

}
