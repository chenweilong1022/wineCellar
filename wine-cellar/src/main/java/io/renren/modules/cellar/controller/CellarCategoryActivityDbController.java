package io.renren.modules.cellar.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.validator.Assert;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.cellar.entity.CellarCategoryActivityDbEntity;
import io.renren.modules.cellar.service.CellarCategoryActivityDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 分类活动表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-21 10:06:01
 */
@RestController
@RequestMapping("cellar/cellarcategoryactivitydb")
public class CellarCategoryActivityDbController {
    @Autowired
    private CellarCategoryActivityDbService cellarCategoryActivityDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarcategoryactivitydb:list")
    public R list(CellarCategoryActivityDbEntity cellarCategoryActivityDb){
        PageUtils page = cellarCategoryActivityDbService.queryPage(cellarCategoryActivityDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{categoryActivityId}")
    @RequiresPermissions("cellar:cellarcategoryactivitydb:info")
    public R info(@PathVariable("categoryActivityId") Long categoryActivityId){
			CellarCategoryActivityDbEntity cellarCategoryActivityDb = cellarCategoryActivityDbService.getById(categoryActivityId);

        return R.ok().put("cellarCategoryActivityDb", cellarCategoryActivityDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarcategoryactivitydb:save")
    public R save(@RequestBody CellarCategoryActivityDbEntity cellarCategoryActivityDb){
        /**
         * 查询活动数量
         */
        int count = cellarCategoryActivityDbService.count(new QueryWrapper<CellarCategoryActivityDbEntity>().lambda()
                .eq(CellarCategoryActivityDbEntity::getState, Constants.STATE.zero.getKey())
        );
        /**
         * 校验活动数量是否超过
         */
        Assert.isTrue(count >= 5,"活动超过限定数量,最高为" + 5 + "个");
        /**
         * 保存活动
         */
        cellarCategoryActivityDb.setState(Constants.STATE.zero.getKey());
        cellarCategoryActivityDb.setCreateTime(new Date());
        cellarCategoryActivityDbService.save(cellarCategoryActivityDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarcategoryactivitydb:update")
    public R update(@RequestBody CellarCategoryActivityDbEntity cellarCategoryActivityDb){
			cellarCategoryActivityDbService.updateById(cellarCategoryActivityDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarcategoryactivitydb:delete")
    public R delete(@RequestBody Long[] categoryActivityIds){
			cellarCategoryActivityDbService.removeByIds(Arrays.asList(categoryActivityIds));

        return R.ok();
    }

}
