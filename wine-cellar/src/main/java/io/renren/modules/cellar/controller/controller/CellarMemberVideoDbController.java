package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberVideoDbEntity;
import io.renren.modules.cellar.service.CellarMemberVideoDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 会员交友视频表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 10:54:07
 */
@RestController
@RequestMapping("cellar/cellarmembervideodb")
public class CellarMemberVideoDbController {
    @Autowired
    private CellarMemberVideoDbService cellarMemberVideoDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmembervideodb:list")
    public R list(CellarMemberVideoDbEntity cellarMemberVideoDb){
        PageUtils page = cellarMemberVideoDbService.queryPage(cellarMemberVideoDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberVideoId}")
    @RequiresPermissions("cellar:cellarmembervideodb:info")
    public R info(@PathVariable("memberVideoId") Long memberVideoId){
			CellarMemberVideoDbEntity cellarMemberVideoDb = cellarMemberVideoDbService.getById(memberVideoId);

        return R.ok().put("cellarMemberVideoDb", cellarMemberVideoDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmembervideodb:save")
    public R save(@RequestBody CellarMemberVideoDbEntity cellarMemberVideoDb){
			cellarMemberVideoDbService.save(cellarMemberVideoDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmembervideodb:update")
    public R update(@RequestBody CellarMemberVideoDbEntity cellarMemberVideoDb){
			cellarMemberVideoDbService.updateById(cellarMemberVideoDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmembervideodb:delete")
    public R delete(@RequestBody Long[] memberVideoIds){
			cellarMemberVideoDbService.removeByIds(Arrays.asList(memberVideoIds));

        return R.ok();
    }

}
