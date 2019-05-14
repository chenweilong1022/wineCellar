package io.renren.modules.cellar.controller.controller;

import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberCardDbEntity;
import io.renren.modules.cellar.service.CellarMemberCardDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;


/**
 * 会员储值卡表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-28 13:34:38
 */
@RestController
@RequestMapping("cellar/cellarmembercarddb")
public class CellarMemberCardDbController {
    @Autowired
    private CellarMemberCardDbService cellarMemberCardDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmembercarddb:list")
    public R list(CellarMemberCardDbEntity cellarMemberCardDb){
        PageUtils page = cellarMemberCardDbService.queryPage(cellarMemberCardDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberCardId}")
    @RequiresPermissions("cellar:cellarmembercarddb:info")
    public R info(@PathVariable("memberCardId") Long memberCardId){
			CellarMemberCardDbEntity cellarMemberCardDb = cellarMemberCardDbService.getById(memberCardId);

        return R.ok().put("cellarMemberCardDb", cellarMemberCardDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmembercarddb:save")
    public R save(@RequestBody CellarMemberCardDbEntity cellarMemberCardDb){
        cellarMemberCardDb.setState(Constants.STATE.zero.getKey());
        cellarMemberCardDb.setCreateTime(new Date());
			cellarMemberCardDbService.save(cellarMemberCardDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmembercarddb:update")
    public R update(@RequestBody CellarMemberCardDbEntity cellarMemberCardDb){
			cellarMemberCardDbService.updateById(cellarMemberCardDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmembercarddb:delete")
    public R delete(@RequestBody Long[] memberCardIds){
			cellarMemberCardDbService.removeByIds(Arrays.asList(memberCardIds));

        return R.ok();
    }

}
