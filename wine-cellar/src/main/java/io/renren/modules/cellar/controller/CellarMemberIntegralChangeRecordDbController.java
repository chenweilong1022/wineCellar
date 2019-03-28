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

import io.renren.modules.cellar.entity.CellarMemberIntegralChangeRecordDbEntity;
import io.renren.modules.cellar.service.CellarMemberIntegralChangeRecordDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 会员积分变动记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-26 13:19:43
 */
@RestController
@RequestMapping("cellar/cellarmemberintegralchangerecorddb")
public class CellarMemberIntegralChangeRecordDbController {
    @Autowired
    private CellarMemberIntegralChangeRecordDbService cellarMemberIntegralChangeRecordDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmemberintegralchangerecorddb:list")
    public R list(CellarMemberIntegralChangeRecordDbEntity cellarMemberIntegralChangeRecordDb){
        PageUtils page = cellarMemberIntegralChangeRecordDbService.queryPage(cellarMemberIntegralChangeRecordDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberIntegralChangeRecordId}")
    @RequiresPermissions("cellar:cellarmemberintegralchangerecorddb:info")
    public R info(@PathVariable("memberIntegralChangeRecordId") Long memberIntegralChangeRecordId){
			CellarMemberIntegralChangeRecordDbEntity cellarMemberIntegralChangeRecordDb = cellarMemberIntegralChangeRecordDbService.getById(memberIntegralChangeRecordId);

        return R.ok().put("cellarMemberIntegralChangeRecordDb", cellarMemberIntegralChangeRecordDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmemberintegralchangerecorddb:save")
    public R save(@RequestBody CellarMemberIntegralChangeRecordDbEntity cellarMemberIntegralChangeRecordDb){
			cellarMemberIntegralChangeRecordDbService.save(cellarMemberIntegralChangeRecordDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmemberintegralchangerecorddb:update")
    public R update(@RequestBody CellarMemberIntegralChangeRecordDbEntity cellarMemberIntegralChangeRecordDb){
			cellarMemberIntegralChangeRecordDbService.updateById(cellarMemberIntegralChangeRecordDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmemberintegralchangerecorddb:delete")
    public R delete(@RequestBody Long[] memberIntegralChangeRecordIds){
			cellarMemberIntegralChangeRecordDbService.removeByIds(Arrays.asList(memberIntegralChangeRecordIds));

        return R.ok();
    }

}
