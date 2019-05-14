package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarCommodityEvaluationDbEntity;
import io.renren.modules.cellar.service.CellarCommodityEvaluationDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 商品评价表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-11 15:12:42
 */
@RestController
@RequestMapping("cellar/cellarcommodityevaluationdb")
public class CellarCommodityEvaluationDbController {
    @Autowired
    private CellarCommodityEvaluationDbService cellarCommodityEvaluationDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarcommodityevaluationdb:list")
    public R list(CellarCommodityEvaluationDbEntity cellarCommodityEvaluationDb){
        PageUtils page = cellarCommodityEvaluationDbService.queryPage(cellarCommodityEvaluationDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{evaluationId}")
    @RequiresPermissions("cellar:cellarcommodityevaluationdb:info")
    public R info(@PathVariable("evaluationId") Long evaluationId){
			CellarCommodityEvaluationDbEntity cellarCommodityEvaluationDb = cellarCommodityEvaluationDbService.getById(evaluationId);

        return R.ok().put("cellarCommodityEvaluationDb", cellarCommodityEvaluationDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarcommodityevaluationdb:save")
    public R save(@RequestBody CellarCommodityEvaluationDbEntity cellarCommodityEvaluationDb){
			cellarCommodityEvaluationDbService.save(cellarCommodityEvaluationDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarcommodityevaluationdb:update")
    public R update(@RequestBody CellarCommodityEvaluationDbEntity cellarCommodityEvaluationDb){
			cellarCommodityEvaluationDbService.updateById(cellarCommodityEvaluationDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarcommodityevaluationdb:delete")
    public R delete(@RequestBody Long[] evaluationIds){
			cellarCommodityEvaluationDbService.removeByIds(Arrays.asList(evaluationIds));

        return R.ok();
    }

}
