package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarShufflingPhotoDbEntity;
import io.renren.modules.cellar.entity.CellarStoreDbEntity;
import io.renren.modules.cellar.service.CellarShufflingPhotoDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * APP首页轮播图表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-15 09:53:34
 */
@RestController
@RequestMapping("app/cellarshufflingphotodb")
@Api(value="APP首页轮播图表",tags="APP首页轮播图表")
public class AppCellarShufflingPhotoDbController {
    @Autowired
    private CellarShufflingPhotoDbService cellarShufflingPhotoDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "APP首页轮播图列表",notes = "APP首页轮播图列表",response = CellarShufflingPhotoDbEntity.class)
    public R list(
            @ApiIgnore CellarShufflingPhotoDbEntity cellarShufflingPhotoDb
    ){
//        PageUtils page = cellarShufflingPhotoDbService.queryPage(cellarShufflingPhotoDb);
        List<CellarShufflingPhotoDbEntity> list = cellarShufflingPhotoDbService.list(new QueryWrapper<CellarShufflingPhotoDbEntity>().lambda()
                .eq(CellarShufflingPhotoDbEntity::getState, Constants.STATE.zero.getKey())
        );
        return R.data(list);
    }


    /**
     * 信息
     */
    @GetMapping("/info")
    @ApiOperation(value = "APP首页轮播图详情",notes = "APP首页轮播图详情",response = CellarShufflingPhotoDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="shufflingPhotoId",value="轮播图id",dataType="String",required=false,paramType="query"),
    })
    public R info(
            @ApiIgnore CellarShufflingPhotoDbEntity cellarShufflingPhotoDb
    ){
        CellarShufflingPhotoDbEntity cellarShufflingPhotoDbEntity = cellarShufflingPhotoDbService.getById(cellarShufflingPhotoDb.getShufflingPhotoId());
        return R.data(cellarShufflingPhotoDbEntity);
    }
//
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    @RequiresPermissions("cellar:cellarshufflingphotodb:save")
//    public R save(@RequestBody CellarShufflingPhotoDbEntity cellarShufflingPhotoDb){
//
//        cellarShufflingPhotoDb.setCreateTime(new Date());
//        cellarShufflingPhotoDb.setState(Constants.STATE.zero.getKey());
//        cellarShufflingPhotoDbService.save(cellarShufflingPhotoDb);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    @RequiresPermissions("cellar:cellarshufflingphotodb:update")
//    public R update(@RequestBody CellarShufflingPhotoDbEntity cellarShufflingPhotoDb){
//			cellarShufflingPhotoDbService.updateById(cellarShufflingPhotoDb);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    @RequiresPermissions("cellar:cellarshufflingphotodb:delete")
//    public R delete(@RequestBody Long[] shufflingPhotoIds){
//			cellarShufflingPhotoDbService.removeByIds(Arrays.asList(shufflingPhotoIds));
//
//        return R.ok();
//    }

}
