package io.renren.modules.cellar.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.common.constants.Constants;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.cellar.entity.CellarShufflingPhotoDbEntity;
import io.renren.modules.cellar.service.CellarShufflingPhotoDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 首页轮播图表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-15 09:53:34
 */
@RestController
@RequestMapping("cellar/cellarshufflingphotodb")
public class CellarShufflingPhotoDbController {
    @Autowired
    private CellarShufflingPhotoDbService cellarShufflingPhotoDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarshufflingphotodb:list")
    public R list(CellarShufflingPhotoDbEntity cellarShufflingPhotoDb){
        PageUtils page = cellarShufflingPhotoDbService.queryPage(cellarShufflingPhotoDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{shufflingPhotoId}")
    @RequiresPermissions("cellar:cellarshufflingphotodb:info")
    public R info(@PathVariable("shufflingPhotoId") Long shufflingPhotoId){
			CellarShufflingPhotoDbEntity cellarShufflingPhotoDb = cellarShufflingPhotoDbService.getById(shufflingPhotoId);

        return R.ok().put("cellarShufflingPhotoDb", cellarShufflingPhotoDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarshufflingphotodb:save")
    public R save(@RequestBody CellarShufflingPhotoDbEntity cellarShufflingPhotoDb){

        cellarShufflingPhotoDb.setCreateTime(new Date());
        cellarShufflingPhotoDb.setState(Constants.STATE.zero.getKey());
        cellarShufflingPhotoDbService.save(cellarShufflingPhotoDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarshufflingphotodb:update")
    public R update(@RequestBody CellarShufflingPhotoDbEntity cellarShufflingPhotoDb){
			cellarShufflingPhotoDbService.updateById(cellarShufflingPhotoDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarshufflingphotodb:delete")
    public R delete(@RequestBody Long[] shufflingPhotoIds){
			cellarShufflingPhotoDbService.removeByIds(Arrays.asList(shufflingPhotoIds));

        return R.ok();
    }

}
