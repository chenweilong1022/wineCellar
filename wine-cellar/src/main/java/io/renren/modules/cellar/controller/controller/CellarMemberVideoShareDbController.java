package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberVideoShareDbEntity;
import io.renren.modules.cellar.service.CellarMemberVideoShareDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 会员交友视频分享记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 15:10:37
 */
@RestController
@RequestMapping("cellar/cellarmembervideosharedb")
public class CellarMemberVideoShareDbController {
    @Autowired
    private CellarMemberVideoShareDbService cellarMemberVideoShareDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmembervideosharedb:list")
    public R list(CellarMemberVideoShareDbEntity cellarMemberVideoShareDb){
        PageUtils page = cellarMemberVideoShareDbService.queryPage(cellarMemberVideoShareDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberVideoShareId}")
    @RequiresPermissions("cellar:cellarmembervideosharedb:info")
    public R info(@PathVariable("memberVideoShareId") Long memberVideoShareId){
			CellarMemberVideoShareDbEntity cellarMemberVideoShareDb = cellarMemberVideoShareDbService.getById(memberVideoShareId);

        return R.ok().put("cellarMemberVideoShareDb", cellarMemberVideoShareDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmembervideosharedb:save")
    public R save(@RequestBody CellarMemberVideoShareDbEntity cellarMemberVideoShareDb){
			cellarMemberVideoShareDbService.save(cellarMemberVideoShareDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmembervideosharedb:update")
    public R update(@RequestBody CellarMemberVideoShareDbEntity cellarMemberVideoShareDb){
			cellarMemberVideoShareDbService.updateById(cellarMemberVideoShareDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmembervideosharedb:delete")
    public R delete(@RequestBody Long[] memberVideoShareIds){
			cellarMemberVideoShareDbService.removeByIds(Arrays.asList(memberVideoShareIds));

        return R.ok();
    }

}
