package io.renren.modules.cellar.controller.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.cellar.entity.CellarRuleDbEntity;
import io.renren.modules.cellar.service.CellarRuleDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;


/**
 * 酒窖规则表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-30 10:12:45
 */
@RestController
@RequestMapping("cellar/cellarruledb")
public class CellarRuleDbController {
    @Autowired
    private CellarRuleDbService cellarRuleDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarruledb:list")
    public R list(CellarRuleDbEntity cellarRuleDb){
        PageUtils page = cellarRuleDbService.queryPage(cellarRuleDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{ruleId}")
    @RequiresPermissions("cellar:cellarruledb:info")
    public R info(@PathVariable("ruleId") Long ruleId){
			CellarRuleDbEntity cellarRuleDb = cellarRuleDbService.getById(ruleId);

        return R.ok().put("cellarRuleDb", cellarRuleDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarruledb:save")
    public R save(@RequestBody CellarRuleDbEntity cellarRuleDb){

        CellarRuleDbEntity ruleDbEntity = cellarRuleDbService.getOne(new QueryWrapper<CellarRuleDbEntity>().lambda()
                .eq(CellarRuleDbEntity::getRuleType, cellarRuleDb.getRuleType())
        );

        Assert.isTrue(ObjectUtil.isNotNull(ruleDbEntity),Constants.RULETYPE.getValueByKey(cellarRuleDb.getRuleType())+"已经添加过了");

        cellarRuleDb.setCreateTime(new Date());
        cellarRuleDb.setState(Constants.STATE.zero.getKey());
        cellarRuleDbService.save(cellarRuleDb);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarruledb:update")
    public R update(@RequestBody CellarRuleDbEntity cellarRuleDb){
			cellarRuleDbService.updateById(cellarRuleDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarruledb:delete")
    public R delete(@RequestBody Long[] ruleIds){
			cellarRuleDbService.removeByIds(Arrays.asList(ruleIds));

        return R.ok();
    }

}
