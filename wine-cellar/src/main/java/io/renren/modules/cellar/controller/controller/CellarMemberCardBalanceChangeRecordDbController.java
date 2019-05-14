package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberCardBalanceChangeRecordDbEntity;
import io.renren.modules.cellar.service.CellarMemberCardBalanceChangeRecordDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 会员储值卡余额变动记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-28 14:31:48
 */
@RestController
@RequestMapping("cellar/cellarmembercardbalancechangerecorddb")
public class CellarMemberCardBalanceChangeRecordDbController {
    @Autowired
    private CellarMemberCardBalanceChangeRecordDbService cellarMemberCardBalanceChangeRecordDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmembercardbalancechangerecorddb:list")
    public R list(CellarMemberCardBalanceChangeRecordDbEntity cellarMemberCardBalanceChangeRecordDb){
        PageUtils page = cellarMemberCardBalanceChangeRecordDbService.queryPage(cellarMemberCardBalanceChangeRecordDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberCardBalanceChangeRecordId}")
    @RequiresPermissions("cellar:cellarmembercardbalancechangerecorddb:info")
    public R info(@PathVariable("memberCardBalanceChangeRecordId") Long memberCardBalanceChangeRecordId){
			CellarMemberCardBalanceChangeRecordDbEntity cellarMemberCardBalanceChangeRecordDb = cellarMemberCardBalanceChangeRecordDbService.getById(memberCardBalanceChangeRecordId);

        return R.ok().put("cellarMemberCardBalanceChangeRecordDb", cellarMemberCardBalanceChangeRecordDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmembercardbalancechangerecorddb:save")
    public R save(@RequestBody CellarMemberCardBalanceChangeRecordDbEntity cellarMemberCardBalanceChangeRecordDb){
			cellarMemberCardBalanceChangeRecordDbService.save(cellarMemberCardBalanceChangeRecordDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmembercardbalancechangerecorddb:update")
    public R update(@RequestBody CellarMemberCardBalanceChangeRecordDbEntity cellarMemberCardBalanceChangeRecordDb){
			cellarMemberCardBalanceChangeRecordDbService.updateById(cellarMemberCardBalanceChangeRecordDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmembercardbalancechangerecorddb:delete")
    public R delete(@RequestBody Long[] memberCardBalanceChangeRecordIds){
			cellarMemberCardBalanceChangeRecordDbService.removeByIds(Arrays.asList(memberCardBalanceChangeRecordIds));

        return R.ok();
    }

}
