package io.renren.modules.cellar.controller.controller;

import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarCouponDbEntity;
import io.renren.modules.cellar.service.CellarCouponDbService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;


/**
 * 优惠券表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-12 17:59:13
 */
@RestController
@RequestMapping("cellar/cellarcoupondb")
public class CellarCouponDbController extends AbstractController {
    @Autowired
    private CellarCouponDbService cellarCouponDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarcoupondb:list")
    public R list(CellarCouponDbEntity cellarCouponDb){
        SysUserEntity user = getUser();
        cellarCouponDb.setStoreId(user.getStoreId());
        PageUtils page = cellarCouponDbService.queryPage(cellarCouponDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{couponId}")
    @RequiresPermissions("cellar:cellarcoupondb:info")
    public R info(@PathVariable("couponId") Long couponId){
			CellarCouponDbEntity cellarCouponDb = cellarCouponDbService.getById(couponId);

        return R.ok().put("cellarCouponDb", cellarCouponDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarcoupondb:save")
    public R save(@RequestBody CellarCouponDbEntity cellarCouponDb){
        SysUserEntity sysUserEntity = getUser();
        cellarCouponDb.setCreateTime(new Date());
        cellarCouponDb.setState(Constants.STATE.zero.getKey());
        cellarCouponDb.setStoreId(sysUserEntity.getStoreId());
        cellarCouponDbService.save(cellarCouponDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarcoupondb:update")
    public R update(@RequestBody CellarCouponDbEntity cellarCouponDb){
			cellarCouponDbService.updateById(cellarCouponDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarcoupondb:delete")
    public R delete(@RequestBody Long[] couponIds){
			cellarCouponDbService.removeByIds(Arrays.asList(couponIds));

        return R.ok();
    }

}
