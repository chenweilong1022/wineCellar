package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberAddressDbEntity;
import io.renren.modules.cellar.service.CellarMemberAddressDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 会员地址表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-26 10:56:54
 */
@RestController
@RequestMapping("cellar/cellarmemberaddressdb")
public class CellarMemberAddressDbController {
    @Autowired
    private CellarMemberAddressDbService cellarMemberAddressDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmemberaddressdb:list")
    public R list(CellarMemberAddressDbEntity cellarMemberAddressDb){
        PageUtils page = cellarMemberAddressDbService.queryPage(cellarMemberAddressDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{addressId}")
    @RequiresPermissions("cellar:cellarmemberaddressdb:info")
    public R info(@PathVariable("addressId") Long addressId){
			CellarMemberAddressDbEntity cellarMemberAddressDb = cellarMemberAddressDbService.getById(addressId);

        return R.ok().put("cellarMemberAddressDb", cellarMemberAddressDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmemberaddressdb:save")
    public R save(@RequestBody CellarMemberAddressDbEntity cellarMemberAddressDb){
			cellarMemberAddressDbService.save(cellarMemberAddressDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmemberaddressdb:update")
    public R update(@RequestBody CellarMemberAddressDbEntity cellarMemberAddressDb){
			cellarMemberAddressDbService.updateById(cellarMemberAddressDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmemberaddressdb:delete")
    public R delete(@RequestBody Long[] addressIds){
			cellarMemberAddressDbService.removeByIds(Arrays.asList(addressIds));

        return R.ok();
    }

}
