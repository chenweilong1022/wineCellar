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

import io.renren.modules.cellar.entity.CellarAdvertisingPhotoDbEntity;
import io.renren.modules.cellar.service.CellarAdvertisingPhotoDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 首页广告图表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-18 09:47:37
 */
@RestController
@RequestMapping("cellar/cellaradvertisingphotodb")
public class CellarAdvertisingPhotoDbController {
    @Autowired
    private CellarAdvertisingPhotoDbService cellarAdvertisingPhotoDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellaradvertisingphotodb:list")
    public R list(CellarAdvertisingPhotoDbEntity cellarAdvertisingPhotoDb){
        PageUtils page = cellarAdvertisingPhotoDbService.queryPage(cellarAdvertisingPhotoDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{advertisingPhotoId}")
    @RequiresPermissions("cellar:cellaradvertisingphotodb:info")
    public R info(@PathVariable("advertisingPhotoId") Long advertisingPhotoId){
			CellarAdvertisingPhotoDbEntity cellarAdvertisingPhotoDb = cellarAdvertisingPhotoDbService.getById(advertisingPhotoId);

        return R.ok().put("cellarAdvertisingPhotoDb", cellarAdvertisingPhotoDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellaradvertisingphotodb:save")
    public R save(@RequestBody CellarAdvertisingPhotoDbEntity cellarAdvertisingPhotoDb){
        cellarAdvertisingPhotoDb.setCreateTime(new Date());
        cellarAdvertisingPhotoDb.setState(Constants.STATE.zero.getKey());
			cellarAdvertisingPhotoDbService.save(cellarAdvertisingPhotoDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellaradvertisingphotodb:update")
    public R update(@RequestBody CellarAdvertisingPhotoDbEntity cellarAdvertisingPhotoDb){
			cellarAdvertisingPhotoDbService.updateById(cellarAdvertisingPhotoDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellaradvertisingphotodb:delete")
    public R delete(@RequestBody Long[] advertisingPhotoIds){
			cellarAdvertisingPhotoDbService.removeByIds(Arrays.asList(advertisingPhotoIds));

        return R.ok();
    }

}
