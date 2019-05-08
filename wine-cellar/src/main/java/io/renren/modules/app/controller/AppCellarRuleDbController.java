package io.renren.modules.app.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.cellar.entity.CellarOrderDbEntity;
import io.renren.modules.cellar.entity.CellarRuleDbEntity;
import io.renren.modules.cellar.service.CellarRuleDbService;
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


/**
 * App酒窖规则表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-30 10:12:45
 */
@RestController
@RequestMapping("app/cellarruledb")
@Api(value="APP酒窖规则表",tags="APP酒窖规则表")
public class AppCellarRuleDbController {
    @Autowired
    private CellarRuleDbService cellarRuleDbService;


    /**
     * 信息
     */
    @GetMapping("/info")
    @ApiOperation(value = "酒窖规则详情",notes = "酒窖规则详情",response = CellarRuleDbEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="ruleType",value="规则类型1.储值卡说明2.钱包说明3.酒币说明4.关于我们5.拼团规则6.预售规则7.秒杀规则8.砍价规则",dataType="int",required=false,paramType="query"),

    })
    public R info(
            @ApiIgnore CellarRuleDbEntity cellarRuleDbEntity
    ){
        Assert.isNull(cellarRuleDbEntity,"规则类型不能为空");
        Assert.isNull(cellarRuleDbEntity.getRuleType(),"规则类型不能为空");
        CellarRuleDbEntity one = cellarRuleDbService.getOne(new QueryWrapper<CellarRuleDbEntity>().lambda()
                .eq(CellarRuleDbEntity::getRuleType, cellarRuleDbEntity.getRuleType())
        );
        return R.data(one);
    }

}
