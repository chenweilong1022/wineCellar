package io.renren.modules.cellar.controller.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberCaptchaEntity;
import io.renren.modules.cellar.service.CellarMemberCaptchaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 验证码表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-01-29 14:59:19
 */
@RestController
@RequestMapping("cellar/cellarmembercaptcha")
public class CellarMemberCaptchaController {
    @Autowired
    private CellarMemberCaptchaService cellarMemberCaptchaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmembercaptcha:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cellarMemberCaptchaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{uuid}")
    @RequiresPermissions("cellar:cellarmembercaptcha:info")
    public R info(@PathVariable("uuid") String uuid){
			CellarMemberCaptchaEntity cellarMemberCaptcha = cellarMemberCaptchaService.getById(uuid);

        return R.ok().put("cellarMemberCaptcha", cellarMemberCaptcha);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmembercaptcha:save")
    public R save(@RequestBody CellarMemberCaptchaEntity cellarMemberCaptcha){
			cellarMemberCaptchaService.save(cellarMemberCaptcha);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmembercaptcha:update")
    public R update(@RequestBody CellarMemberCaptchaEntity cellarMemberCaptcha){
			cellarMemberCaptchaService.updateById(cellarMemberCaptcha);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmembercaptcha:delete")
    public R delete(@RequestBody String[] uuids){
			cellarMemberCaptchaService.removeByIds(Arrays.asList(uuids));

        return R.ok();
    }

}
