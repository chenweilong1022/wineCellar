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

import io.renren.modules.cellar.entity.CellarMemberVideoCommentThumbDbEntity;
import io.renren.modules.cellar.service.CellarMemberVideoCommentThumbDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 会员交友视频评论点赞记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 16:09:08
 */
@RestController
@RequestMapping("cellar/cellarmembervideocommentthumbdb")
public class CellarMemberVideoCommentThumbDbController {
    @Autowired
    private CellarMemberVideoCommentThumbDbService cellarMemberVideoCommentThumbDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmembervideocommentthumbdb:list")
    public R list(CellarMemberVideoCommentThumbDbEntity cellarMemberVideoCommentThumbDb){
        PageUtils page = cellarMemberVideoCommentThumbDbService.queryPage(cellarMemberVideoCommentThumbDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberVideoCommentThumbId}")
    @RequiresPermissions("cellar:cellarmembervideocommentthumbdb:info")
    public R info(@PathVariable("memberVideoCommentThumbId") Long memberVideoCommentThumbId){
			CellarMemberVideoCommentThumbDbEntity cellarMemberVideoCommentThumbDb = cellarMemberVideoCommentThumbDbService.getById(memberVideoCommentThumbId);

        return R.ok().put("cellarMemberVideoCommentThumbDb", cellarMemberVideoCommentThumbDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmembervideocommentthumbdb:save")
    public R save(@RequestBody CellarMemberVideoCommentThumbDbEntity cellarMemberVideoCommentThumbDb){
			cellarMemberVideoCommentThumbDbService.save(cellarMemberVideoCommentThumbDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmembervideocommentthumbdb:update")
    public R update(@RequestBody CellarMemberVideoCommentThumbDbEntity cellarMemberVideoCommentThumbDb){
			cellarMemberVideoCommentThumbDbService.updateById(cellarMemberVideoCommentThumbDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmembervideocommentthumbdb:delete")
    public R delete(@RequestBody Long[] memberVideoCommentThumbIds){
			cellarMemberVideoCommentThumbDbService.removeByIds(Arrays.asList(memberVideoCommentThumbIds));

        return R.ok();
    }

}
