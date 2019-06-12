package io.renren.modules.app.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDynamicDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDynamicThumbDbEntity;
import io.renren.modules.cellar.service.CellarMemberDynamicDbService;
import io.renren.modules.cellar.service.CellarMemberDynamicThumbDbService;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Date;


/**
 * 会员动态点赞表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-05-14 17:54:33
 */
@RestController
@RequestMapping("app/cellarmemberdynamicthumbdb")
@Api(value="APP会员动态点赞表",tags="APP会员动态点赞表")
public class AppCellarMemberDynamicThumbDbController extends AbstractController {
    @Autowired
    private CellarMemberDynamicThumbDbService cellarMemberDynamicThumbDbService;
    @Autowired
    private CellarMemberDynamicDbService cellarMemberDynamicDbService;

    /**
     * 会员动态点赞/取消点赞接口
     */
    @PostMapping("/save")
    @ApiOperation(value = "会员动态点赞/取消点赞接口",notes = "会员动态点赞/取消点赞接口",response = R.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="memberDynamicId",value="会员动态id",dataType="String",required=false,paramType="query"),
    })
    @Transactional
    public R save(
            @ApiIgnore CellarMemberDynamicThumbDbEntity cellarMemberDynamicThumbDb
    ){
        /**
         * 校验前端数据
         */
        Assert.isNull(cellarMemberDynamicThumbDb.getMemberDynamicId(),"会员动态id不能为空");
        CellarMemberDynamicDbEntity cellarMemberDynamicDbEntity = cellarMemberDynamicDbService.getById(cellarMemberDynamicThumbDb.getMemberDynamicId());
        Assert.isNull(cellarMemberDynamicDbEntity,"动态不存在");
        /**
         * 根据用户和用户动态id
         * 查询是否已经点赞
         */
        cellarMemberDynamicThumbDb.setState(Constants.STATE.zero.getKey());
        cellarMemberDynamicThumbDb.setCreateTime(new Date());
        cellarMemberDynamicThumbDb.setMemberId(this.cellarMemberDbEntity.getMemberId());
        CellarMemberDynamicThumbDbEntity one = cellarMemberDynamicThumbDbService.getOne(new QueryWrapper<CellarMemberDynamicThumbDbEntity>().lambda()
                .eq(CellarMemberDynamicThumbDbEntity::getMemberId, cellarMemberDynamicThumbDb.getMemberId())
                .eq(CellarMemberDynamicThumbDbEntity::getMemberDynamicId, cellarMemberDynamicThumbDb.getMemberDynamicId())
        );
        /**
         * 判断
         * 如果已经点赞 删除点赞记录 同时点赞数量 - 1
         * 如果没有点赞 增加点赞记录 同时点赞数量 + 1
         */
        if (ObjectUtil.isNotNull(one)) {
            cellarMemberDynamicThumbDbService.removeById(one.getMemberDynamicThumbId());
            cellarMemberDynamicDbEntity.setThumbNumber(cellarMemberDynamicDbEntity.getThumbNumber() - Constants.Number.one.getKey());
        }else {
            cellarMemberDynamicThumbDbService.save(cellarMemberDynamicThumbDb);
            cellarMemberDynamicDbEntity.setThumbNumber(cellarMemberDynamicDbEntity.getThumbNumber() + Constants.Number.one.getKey());
        }
        cellarMemberDynamicDbService.updateById(cellarMemberDynamicDbEntity);
        return R.ok();
    }

}
