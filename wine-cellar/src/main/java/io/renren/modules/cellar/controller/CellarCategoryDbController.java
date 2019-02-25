package io.renren.modules.cellar.controller;

import java.util.*;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.cellar.entity.CellarCategoryDbEntity;
import io.renren.modules.cellar.service.CellarCategoryDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 酒窖类别表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-23 15:22:27
 */
@RestController
@RequestMapping("cellar/cellarcategorydb")
public class CellarCategoryDbController extends AbstractController {
    @Autowired
    private CellarCategoryDbService cellarCategoryDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarcategorydb:list")
    public R list(@RequestBody CellarCategoryDbEntity cellarCategoryDb){
        PageUtils page = cellarCategoryDbService.queryPage(cellarCategoryDb);

        return R.ok().put("page", page);
    }

    /**
     * 查询一级类别
     */
    @RequestMapping("/listOneLevel")
    @RequiresPermissions("cellar:cellarcategorydb:list")
    public R listOneLevel(){
        /**
         * 设置顶级类别
         */
        CellarCategoryDbEntity cellarCategoryDb = new CellarCategoryDbEntity();
        cellarCategoryDb.setLevel(Constants.STATE.zero.getKey());
        cellarCategoryDb.setCategoryName("顶级类别");
        cellarCategoryDb.setSupCategoryId(Constants.STATE.fuone.getKey().longValue());
        cellarCategoryDb.setCategoryId(Constants.STATE.zero.getKey().longValue());
        /**
         * 查询一级类别列表
         */
        List<CellarCategoryDbEntity> list = cellarCategoryDbService.list(new QueryWrapper<CellarCategoryDbEntity>().lambda().
                eq(CellarCategoryDbEntity::getSupCategoryId,cellarCategoryDb.getCategoryId()).
                eq(ObjectUtil.isNotNull(getUser().getStoreId()),CellarCategoryDbEntity::getStoreId,getUser().getStoreId()));
        cellarCategoryDb.setCellarCategoryDbEntities(list);

        /**
         * 封装数据返回
         */
        List<CellarCategoryDbEntity> cellarCategoryDbEntities = new ArrayList<>();
        cellarCategoryDbEntities.add(cellarCategoryDb);

        return R.data(cellarCategoryDbEntities);
    }

    /**
     * 查询所有类别
     */
    @RequestMapping("/listAllLevel")
    @RequiresPermissions("cellar:cellarcategorydb:list")
    public R listAllLevel(){

        List<CellarCategoryDbEntity> cellarCategoryDbEntities = cellarCategoryDbService.listAllLevel();
        return R.data(cellarCategoryDbEntities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{categoryId}")
    @RequiresPermissions("cellar:cellarcategorydb:info")
    public R info(@PathVariable("categoryId") Long categoryId){
			CellarCategoryDbEntity cellarCategoryDb = cellarCategoryDbService.getById(categoryId);

        return R.ok().put("cellarCategoryDb", cellarCategoryDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarcategorydb:save")
    public R save(@RequestBody CellarCategoryDbEntity cellarCategoryDb){
        cellarCategoryDb.setCreateTime(new Date());
        cellarCategoryDb.setState(Constants.STATE.zero.getKey());
        cellarCategoryDb.setStoreId(getUser().getStoreId());
        cellarCategoryDbService.save(cellarCategoryDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarcategorydb:update")
    public R update(@RequestBody CellarCategoryDbEntity cellarCategoryDb){
			cellarCategoryDbService.updateById(cellarCategoryDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarcategorydb:delete")
    public R delete(@RequestBody Long[] categoryIds){
			cellarCategoryDbService.removeByIds(Arrays.asList(categoryIds));

        return R.ok();
    }

}
