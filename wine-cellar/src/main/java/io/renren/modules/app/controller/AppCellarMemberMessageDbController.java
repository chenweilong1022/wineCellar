package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.MessageTypeEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.entity.CellarMemberMessageDbEntity;
import io.renren.modules.cellar.service.CellarMemberMessageDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * APP会员消息
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-19 11:07:06
 */
@RestController
@RequestMapping("app/cellarmembermessagedb")
@Api(value="APP会员消息",tags="APP会员消息")
public class AppCellarMemberMessageDbController {
    @Autowired
    private CellarMemberMessageDbService cellarMemberMessageDbService;

    /**
     * 会员消息未读数量
     */
    @GetMapping("/unreadCount")
    @ApiOperation(value = "会员消息未读数量",notes = "会员消息未读数量",response = CellarMemberMessageDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
    })
    public R unreadCount(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity
    ){
        int count = cellarMemberMessageDbService.count(new QueryWrapper<CellarMemberMessageDbEntity>().lambda()
                .eq(CellarMemberMessageDbEntity::getMemberId, cellarMemberDbEntity.getMemberId())
                .eq(CellarMemberMessageDbEntity::getHaveRead, Constants.HAVEREAD.UNREAD.getKey())
        );
        return R.data(count);
    }

    /**
     * 消息类型列表
     */
    @GetMapping("/messageTypeList")
    @ApiOperation(value = "消息类型列表",notes = "消息类型列表",response = MessageTypeEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
    })
    public R messageTypeList(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity
    ){

        List<MessageTypeEntity> messageTypeEntities = new ArrayList<>();
        /**
         * 遍历消息类型
         */
        Constants.MESSAGETYPE[] values = Constants.MESSAGETYPE.values();
        for (Constants.MESSAGETYPE value : values) {
            /**
             * 封装消息类型实体类
             */
            MessageTypeEntity messageTypeEntity = new MessageTypeEntity();
            messageTypeEntity.setKey(value.getKey());
            messageTypeEntity.setValue(value.getValue());
            /**
             * 查询所有数量
             */
            int allCount = cellarMemberMessageDbService.count(new QueryWrapper<CellarMemberMessageDbEntity>().lambda()
                .eq(CellarMemberMessageDbEntity::getMemberId, cellarMemberDbEntity.getMemberId())
                .eq(CellarMemberMessageDbEntity::getMessageType,value.getKey())
            );
            /**
             * 查询未读数量
             */
            int unReadCount = cellarMemberMessageDbService.count(new QueryWrapper<CellarMemberMessageDbEntity>().lambda()
                    .eq(CellarMemberMessageDbEntity::getMemberId, cellarMemberDbEntity.getMemberId())
                    .eq(CellarMemberMessageDbEntity::getMessageType,value.getKey())
                    .eq(CellarMemberMessageDbEntity::getHaveRead, Constants.HAVEREAD.UNREAD)
            );

            /**
             * 获取最后一条信息
             */
            CellarMemberMessageDbEntity memberMessageDbEntity = cellarMemberMessageDbService.getOne(new QueryWrapper<CellarMemberMessageDbEntity>().lambda()
                    .eq(CellarMemberMessageDbEntity::getMemberId, cellarMemberDbEntity.getMemberId())
                    .eq(CellarMemberMessageDbEntity::getMessageType, value.getKey())
                    .eq(CellarMemberMessageDbEntity::getHaveRead, Constants.HAVEREAD.UNREAD)
                    .orderByDesc(CellarMemberMessageDbEntity::getCreateTime)
                    .last(" limit 0,1")
            );

            messageTypeEntity.setAllCount(allCount);
            messageTypeEntity.setUnReadCount(unReadCount);
            messageTypeEntity.setCellarMemberMessageDbEntity(memberMessageDbEntity);
            messageTypeEntities.add(messageTypeEntity);
        }
        return R.data(messageTypeEntities);
    }


    /**
     * 消息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "消息列表",notes = "消息列表",response = CellarMemberMessageDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="messageType",value="消息类型",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            CellarMemberMessageDbEntity cellarMemberMessageDbEntity
    ){

        Assert.isNull(cellarMemberMessageDbEntity,"消息类型不能为空");
        Assert.isNull(cellarMemberMessageDbEntity.getMessageType(),"消息类型不能为空");

        List<CellarMemberMessageDbEntity> list = cellarMemberMessageDbService.list(new QueryWrapper<CellarMemberMessageDbEntity>().lambda()
                .eq(CellarMemberMessageDbEntity::getMemberId, cellarMemberDbEntity.getMemberId())
                .eq(CellarMemberMessageDbEntity::getMessageType, cellarMemberMessageDbEntity.getMessageType())
                .orderByDesc(CellarMemberMessageDbEntity::getCreateTime)
        );
        return R.data(list);
    }

    /**
     * 设置已读
     */
    @GetMapping("/setRead")
    @ApiOperation(value = "设置已读",notes = "设置已读",response = CellarMemberMessageDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token,用于校验当前用户",dataType="String",required=false,paramType="query"),
    })
    public R setRead(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            Long[] memberMessageIds
    ){

        Assert.isNull(memberMessageIds,"消息id不能为空");
        /**
         * 设置消息已读
         */
        CellarMemberMessageDbEntity cellarMemberMessageDbEntity = new CellarMemberMessageDbEntity();
        cellarMemberMessageDbEntity.setHaveRead(Constants.HAVEREAD.READ.getKey());

        cellarMemberMessageDbService.update(cellarMemberMessageDbEntity,new QueryWrapper<CellarMemberMessageDbEntity>().lambda()
                .in(CellarMemberMessageDbEntity::getMemberMessageId,memberMessageIds)
                .eq(CellarMemberMessageDbEntity::getMemberId,cellarMemberDbEntity.getMemberId())
        );
        return R.ok();
    }

}
