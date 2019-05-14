package io.renren.modules.cellar.controller.controller;

import io.renren.common.annotation.MemberMessage;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberMessageDbEntity;
import io.renren.modules.cellar.service.CellarMemberMessageDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 会员消息
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-19 11:07:06
 */
@RestController
@RequestMapping("cellar/cellarmembermessagedb")
public class CellarMemberMessageDbController {
    @Autowired
    private CellarMemberMessageDbService cellarMemberMessageDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellarmembermessagedb:list")
    public R list(CellarMemberMessageDbEntity cellarMemberMessageDb){
        PageUtils page = cellarMemberMessageDbService.queryPage(cellarMemberMessageDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberMessageId}")
    @RequiresPermissions("cellar:cellarmembermessagedb:info")
    public R info(@PathVariable("memberMessageId") Long memberMessageId){
			CellarMemberMessageDbEntity cellarMemberMessageDb = cellarMemberMessageDbService.getById(memberMessageId);

        return R.ok().put("cellarMemberMessageDb", cellarMemberMessageDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellarmembermessagedb:save")
    public R save(@RequestBody CellarMemberMessageDbEntity cellarMemberMessageDb){
			cellarMemberMessageDbService.save(cellarMemberMessageDb);

        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/batch")
    @MemberMessage(MESSAGEHEAD = "系统消息",MESSAGETYPE = Constants.MESSAGETYPE.SYSTEM)
    @RequiresPermissions("cellar:cellarmembermessagedb:save")
    public R batch(@RequestBody CellarMemberMessageDbEntity cellarMemberMessageDb){
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellarmembermessagedb:update")
    public R update(@RequestBody CellarMemberMessageDbEntity cellarMemberMessageDb){
			cellarMemberMessageDbService.updateById(cellarMemberMessageDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellarmembermessagedb:delete")
    public R delete(@RequestBody Long[] memberMessageIds){
			cellarMemberMessageDbService.removeByIds(Arrays.asList(memberMessageIds));

        return R.ok();
    }

}
