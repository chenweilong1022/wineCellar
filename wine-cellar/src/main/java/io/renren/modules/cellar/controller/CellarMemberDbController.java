package io.renren.modules.cellar.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.constants.Constants;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarMemberDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 酒窖会员表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-01-16 17:34:20
 */
@RestController
@RequestMapping("cellar/cellarmemberdb")
public class CellarMemberDbController {
    @Autowired
    private CellarMemberDbService cellarMemberDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmemberdb:list")
    public R list(@RequestParam Map<String, Object> params,CellarMemberDbEntity cellarMemberDbEntity){
        PageUtils page = cellarMemberDbService.queryPage(params,cellarMemberDbEntity);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberId}")
    @RequiresPermissions("cellar:cellarmemberdb:info")
    public R info(@PathVariable("memberId") Long memberId){
			CellarMemberDbEntity cellarMemberDb = cellarMemberDbService.getById(memberId);

        return R.ok().put("cellarMemberDb", cellarMemberDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmemberdb:save")
    public R save(@RequestBody CellarMemberDbEntity cellarMemberDb){
			cellarMemberDbService.save(cellarMemberDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmemberdb:update")
    public R update(@RequestBody CellarMemberDbEntity cellarMemberDb){
			cellarMemberDbService.updateById(cellarMemberDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmemberdb:delete")
    public R delete(@RequestBody Long[] memberIds){
//			cellarMemberDbService.removeByIds(Arrays.asList(memberIds));

        List<CellarMemberDbEntity> list = new ArrayList<>();
        for (Long memberId : memberIds) {
            CellarMemberDbEntity cellarMemberDbEntity = new CellarMemberDbEntity();
            cellarMemberDbEntity.setMemberId(memberId);
            cellarMemberDbEntity.setState(Constants.STATE.funine.getKey());
            list.add(cellarMemberDbEntity);
        }
        cellarMemberDbService.updateBatchById(list);
        return R.ok();
    }

}
