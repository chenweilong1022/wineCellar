package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberVideoThumbDbEntity;
import io.renren.modules.cellar.service.CellarMemberVideoThumbDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 会员交友视频点赞记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 13:17:10
 */
@RestController
@RequestMapping("cellar/cellarmembervideothumbdb")
public class CellarMemberVideoThumbDbController {
    @Autowired
    private CellarMemberVideoThumbDbService cellarMemberVideoThumbDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmembervideothumbdb:list")
    public R list(CellarMemberVideoThumbDbEntity cellarMemberVideoThumbDb){
        PageUtils page = cellarMemberVideoThumbDbService.queryPage(cellarMemberVideoThumbDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberVideoThumbId}")
    @RequiresPermissions("cellar:cellarmembervideothumbdb:info")
    public R info(@PathVariable("memberVideoThumbId") Long memberVideoThumbId){
			CellarMemberVideoThumbDbEntity cellarMemberVideoThumbDb = cellarMemberVideoThumbDbService.getById(memberVideoThumbId);

        return R.ok().put("cellarMemberVideoThumbDb", cellarMemberVideoThumbDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmembervideothumbdb:save")
    public R save(@RequestBody CellarMemberVideoThumbDbEntity cellarMemberVideoThumbDb){
			cellarMemberVideoThumbDbService.save(cellarMemberVideoThumbDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmembervideothumbdb:update")
    public R update(@RequestBody CellarMemberVideoThumbDbEntity cellarMemberVideoThumbDb){
			cellarMemberVideoThumbDbService.updateById(cellarMemberVideoThumbDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmembervideothumbdb:delete")
    public R delete(@RequestBody Long[] memberVideoThumbIds){
			cellarMemberVideoThumbDbService.removeByIds(Arrays.asList(memberVideoThumbIds));

        return R.ok();
    }

}
