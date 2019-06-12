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

import io.renren.modules.cellar.entity.CellarMemberDynamicCommentDbEntity;
import io.renren.modules.cellar.service.CellarMemberDynamicCommentDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 会员动态评论表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-05-15 13:18:56
 */
@RestController
@RequestMapping("cellar/cellarmemberdynamiccommentdb")
public class CellarMemberDynamicCommentDbController {
    @Autowired
    private CellarMemberDynamicCommentDbService cellarMemberDynamicCommentDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmemberdynamiccommentdb:list")
    public R list(CellarMemberDynamicCommentDbEntity cellarMemberDynamicCommentDb){
        PageUtils page = cellarMemberDynamicCommentDbService.queryPage(cellarMemberDynamicCommentDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberDynamicCommentId}")
    @RequiresPermissions("cellar:cellarmemberdynamiccommentdb:info")
    public R info(@PathVariable("memberDynamicCommentId") Long memberDynamicCommentId){
			CellarMemberDynamicCommentDbEntity cellarMemberDynamicCommentDb = cellarMemberDynamicCommentDbService.getById(memberDynamicCommentId);

        return R.ok().put("cellarMemberDynamicCommentDb", cellarMemberDynamicCommentDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmemberdynamiccommentdb:save")
    public R save(@RequestBody CellarMemberDynamicCommentDbEntity cellarMemberDynamicCommentDb){
			cellarMemberDynamicCommentDbService.save(cellarMemberDynamicCommentDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmemberdynamiccommentdb:update")
    public R update(@RequestBody CellarMemberDynamicCommentDbEntity cellarMemberDynamicCommentDb){
			cellarMemberDynamicCommentDbService.updateById(cellarMemberDynamicCommentDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmemberdynamiccommentdb:delete")
    public R delete(@RequestBody Long[] memberDynamicCommentIds){
			cellarMemberDynamicCommentDbService.removeByIds(Arrays.asList(memberDynamicCommentIds));

        return R.ok();
    }

}
