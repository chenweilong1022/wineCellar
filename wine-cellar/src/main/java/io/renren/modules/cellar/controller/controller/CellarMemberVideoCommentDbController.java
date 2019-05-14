package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberVideoCommentDbEntity;
import io.renren.modules.cellar.service.CellarMemberVideoCommentDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 会员交友视频评论记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 14:11:13
 */
@RestController
@RequestMapping("cellar/cellarmembervideocommentdb")
public class CellarMemberVideoCommentDbController {
    @Autowired
    private CellarMemberVideoCommentDbService cellarMemberVideoCommentDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmembervideocommentdb:list")
    public R list(CellarMemberVideoCommentDbEntity cellarMemberVideoCommentDb){
        PageUtils page = cellarMemberVideoCommentDbService.queryPage(cellarMemberVideoCommentDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberVideoCommentId}")
    @RequiresPermissions("cellar:cellarmembervideocommentdb:info")
    public R info(@PathVariable("memberVideoCommentId") Long memberVideoCommentId){
			CellarMemberVideoCommentDbEntity cellarMemberVideoCommentDb = cellarMemberVideoCommentDbService.getById(memberVideoCommentId);

        return R.ok().put("cellarMemberVideoCommentDb", cellarMemberVideoCommentDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmembervideocommentdb:save")
    public R save(@RequestBody CellarMemberVideoCommentDbEntity cellarMemberVideoCommentDb){
			cellarMemberVideoCommentDbService.save(cellarMemberVideoCommentDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmembervideocommentdb:update")
    public R update(@RequestBody CellarMemberVideoCommentDbEntity cellarMemberVideoCommentDb){
			cellarMemberVideoCommentDbService.updateById(cellarMemberVideoCommentDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmembervideocommentdb:delete")
    public R delete(@RequestBody Long[] memberVideoCommentIds){
			cellarMemberVideoCommentDbService.removeByIds(Arrays.asList(memberVideoCommentIds));

        return R.ok();
    }

}
