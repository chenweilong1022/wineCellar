package io.renren.modules.cellar.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.common.constants.Constants;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.AbstractEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.cellar.entity.CellarCommodityDbEntity;
import io.renren.modules.cellar.service.CellarCommodityDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 酒窖商品表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 10:37:50
 */
@RestController
@RequestMapping("cellar/cellarcommoditydb")
public class CellarCommodityDbController extends AbstractController {
    @Autowired
    private CellarCommodityDbService cellarCommodityDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarcommoditydb:list")
    public R list(CellarCommodityDbEntity cellarCommodityDb){
        PageUtils page = cellarCommodityDbService.queryPage(cellarCommodityDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{commodityId}")
    @RequiresPermissions("cellar:cellarcommoditydb:info")
    public R info(@PathVariable("commodityId") Long commodityId){
			CellarCommodityDbEntity cellarCommodityDb = cellarCommodityDbService.getById(commodityId);

        return R.ok().put("cellarCommodityDb", cellarCommodityDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarcommoditydb:save")
    public R save(@RequestBody CellarCommodityDbEntity cellarCommodityDb){
        cellarCommodityDb.setState(Constants.STATE.zero.getKey());
        cellarCommodityDb.setCreationTime(new Date());
        cellarCommodityDb.setMonthSales(BigDecimal.ZERO);
        cellarCommodityDb.setTotalSales(BigDecimal.ZERO);
        cellarCommodityDb.setHighPraise(BigDecimal.ZERO);
        cellarCommodityDb.setStoreId(getUser().getStoreId());
        cellarCommodityDb.setHaveHandpick(Constants.HAVEHANDPICK.NO.getKey());
        cellarCommodityDbService.save(cellarCommodityDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarcommoditydb:update")
    public R update(@RequestBody CellarCommodityDbEntity cellarCommodityDb){
			cellarCommodityDbService.updateById(cellarCommodityDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarcommoditydb:delete")
    public R delete(@RequestBody Long[] commodityIds){
			cellarCommodityDbService.removeByIds(Arrays.asList(commodityIds));

        return R.ok();
    }

}
