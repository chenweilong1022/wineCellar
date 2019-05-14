package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarFeedbackDbEntity;
import io.renren.modules.cellar.service.CellarFeedbackDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 意见反馈表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-26 17:15:05
 */
@RestController
@RequestMapping("cellar/cellarfeedbackdb")
public class CellarFeedbackDbController {
    @Autowired
    private CellarFeedbackDbService cellarFeedbackDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarfeedbackdb:list")
    public R list(CellarFeedbackDbEntity cellarFeedbackDb){
        PageUtils page = cellarFeedbackDbService.queryPage(cellarFeedbackDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{feedbackId}")
    @RequiresPermissions("cellar:cellarfeedbackdb:info")
    public R info(@PathVariable("feedbackId") Long feedbackId){
			CellarFeedbackDbEntity cellarFeedbackDb = cellarFeedbackDbService.getById(feedbackId);

        return R.ok().put("cellarFeedbackDb", cellarFeedbackDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarfeedbackdb:save")
    public R save(@RequestBody CellarFeedbackDbEntity cellarFeedbackDb){
			cellarFeedbackDbService.save(cellarFeedbackDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarfeedbackdb:update")
    public R update(@RequestBody CellarFeedbackDbEntity cellarFeedbackDb){
			cellarFeedbackDbService.updateById(cellarFeedbackDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarfeedbackdb:delete")
    public R delete(@RequestBody Long[] feedbackIds){
			cellarFeedbackDbService.removeByIds(Arrays.asList(feedbackIds));

        return R.ok();
    }

}
