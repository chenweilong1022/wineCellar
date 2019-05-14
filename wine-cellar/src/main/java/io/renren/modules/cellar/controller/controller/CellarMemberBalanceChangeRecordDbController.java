package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberBalanceChangeRecordDbEntity;
import io.renren.modules.cellar.service.CellarMemberBalanceChangeRecordDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 会员余额变动记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-25 11:07:23
 */
@RestController
@RequestMapping("cellar/cellarmemberbalancechangerecorddb")
public class CellarMemberBalanceChangeRecordDbController {
    @Autowired
    private CellarMemberBalanceChangeRecordDbService cellarMemberBalanceChangeRecordDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmemberbalancechangerecorddb:list")
    public R list(CellarMemberBalanceChangeRecordDbEntity cellarMemberBalanceChangeRecordDb){
        PageUtils page = cellarMemberBalanceChangeRecordDbService.queryPage(cellarMemberBalanceChangeRecordDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberBalanceChangeRecordId}")
    @RequiresPermissions("cellar:cellarmemberbalancechangerecorddb:info")
    public R info(@PathVariable("memberBalanceChangeRecordId") Long memberBalanceChangeRecordId){
			CellarMemberBalanceChangeRecordDbEntity cellarMemberBalanceChangeRecordDb = cellarMemberBalanceChangeRecordDbService.getById(memberBalanceChangeRecordId);

        return R.ok().put("cellarMemberBalanceChangeRecordDb", cellarMemberBalanceChangeRecordDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmemberbalancechangerecorddb:save")
    public R save(@RequestBody CellarMemberBalanceChangeRecordDbEntity cellarMemberBalanceChangeRecordDb){
			cellarMemberBalanceChangeRecordDbService.save(cellarMemberBalanceChangeRecordDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmemberbalancechangerecorddb:update")
    public R update(@RequestBody CellarMemberBalanceChangeRecordDbEntity cellarMemberBalanceChangeRecordDb){
			cellarMemberBalanceChangeRecordDbService.updateById(cellarMemberBalanceChangeRecordDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmemberbalancechangerecorddb:delete")
    public R delete(@RequestBody Long[] memberBalanceChangeRecordIds){
			cellarMemberBalanceChangeRecordDbService.removeByIds(Arrays.asList(memberBalanceChangeRecordIds));

        return R.ok();
    }

}
