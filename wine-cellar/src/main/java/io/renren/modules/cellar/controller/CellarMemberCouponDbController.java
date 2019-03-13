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

import io.renren.modules.cellar.entity.CellarMemberCouponDbEntity;
import io.renren.modules.cellar.service.CellarMemberCouponDbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 会员优惠券表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-13 14:22:14
 */
@RestController
@RequestMapping("cellar/cellarmembercoupondb")
public class CellarMemberCouponDbController {
    @Autowired
    private CellarMemberCouponDbService cellarMemberCouponDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmembercoupondb:list")
    public R list(CellarMemberCouponDbEntity cellarMemberCouponDb){
        PageUtils page = cellarMemberCouponDbService.queryPage(cellarMemberCouponDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberCouponId}")
    @RequiresPermissions("cellar:cellarmembercoupondb:info")
    public R info(@PathVariable("memberCouponId") Long memberCouponId){
			CellarMemberCouponDbEntity cellarMemberCouponDb = cellarMemberCouponDbService.getById(memberCouponId);

        return R.ok().put("cellarMemberCouponDb", cellarMemberCouponDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmembercoupondb:save")
    public R save(@RequestBody CellarMemberCouponDbEntity cellarMemberCouponDb){
			cellarMemberCouponDbService.save(cellarMemberCouponDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmembercoupondb:update")
    public R update(@RequestBody CellarMemberCouponDbEntity cellarMemberCouponDb){
			cellarMemberCouponDbService.updateById(cellarMemberCouponDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmembercoupondb:delete")
    public R delete(@RequestBody Long[] memberCouponIds){
			cellarMemberCouponDbService.removeByIds(Arrays.asList(memberCouponIds));

        return R.ok();
    }

}
