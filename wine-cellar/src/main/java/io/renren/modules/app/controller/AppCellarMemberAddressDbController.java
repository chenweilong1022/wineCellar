package io.renren.modules.app.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.cellar.entity.CellarMemberAddressDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarMemberAddressDbService;
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
 * App会员地址表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-26 10:56:54
 */
@RestController
@RequestMapping("app/cellarmemberaddressdb")
@Api(value="APP会员地址表",tags="APP会员地址表")
public class AppCellarMemberAddressDbController {
    @Autowired
    private CellarMemberAddressDbService cellarMemberAddressDbService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "用户地址列表",notes = "用户地址列表",response = CellarMemberAddressDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="key",value="搜索条件",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="page",value="当前页数",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="limit",value="每页个数",dataType="String",required=false,paramType="query"),
    })
    public R list(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberAddressDbEntity cellarMemberAddressDb
    ){
        cellarMemberAddressDb.setMemberId(cellarMemberDbEntity.getMemberId());
        PageUtils page = cellarMemberAddressDbService.queryPage(cellarMemberAddressDb);

        return R.data(page);
    }



    /**
     * 信息
     */
    @GetMapping("/info")
    @ApiOperation(value = "根据地址id查询用户信息",notes = "根据地址id查询用户信息",response = CellarMemberAddressDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="登录token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="addressId",value="地址id",dataType="String",required=false,paramType="query"),
    })
    public R info(
            @ApiIgnore CellarMemberAddressDbEntity memberAddressDbEntity
    ){
		CellarMemberAddressDbEntity cellarMemberAddressDb = cellarMemberAddressDbService.getById(memberAddressDbEntity.getAddressId());

        return R.data(cellarMemberAddressDb);
    }

    /**
     * 信息
     */
    @GetMapping("/isDefault")
    @ApiOperation(value = "获取用户默认地址",notes = "获取用户默认地址",response = CellarMemberAddressDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="登录token",dataType="String",required=false,paramType="query"),
    })
    public R isDefault(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity
    ){
        /**
         * 获取用户默认地址
         */
        CellarMemberAddressDbEntity addressDbEntity = cellarMemberAddressDbService.getOne(new QueryWrapper<CellarMemberAddressDbEntity>().lambda()
                .eq(CellarMemberAddressDbEntity::getIsDefault, Constants.ISDEFAULT.YES.getKey())
                .eq(CellarMemberAddressDbEntity::getMemberId, cellarMemberDbEntity.getMemberId())
        );
        return R.data(addressDbEntity);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存用户地址信息",notes = "保存用户地址信息",response = CellarMemberAddressDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="登录token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="contact",value="联系人",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="contactPhone",value="联系电话",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="provinceName",value="省名称",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="cityName",value="市名称",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="countyName",value="县名称",dataType="String",required=false,paramType="query"),
//            @ApiImplicitParam(name="provinceId",value="省id",dataType="String",required=false,paramType="query"),
//            @ApiImplicitParam(name="cityId",value="市id",dataType="String",required=false,paramType="query"),
//            @ApiImplicitParam(name="countyId",value="县id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="detailedAddress",value="详细地址",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="mapAddress",value="地图地址",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="isDefault",value="是否默认 1 默认 0 非默认",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="longitude",value="经度",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="latitude",value="纬度",dataType="String",required=false,paramType="query"),
    })
    public R save(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberAddressDbEntity cellarMemberAddressDb
    ){
        /**
         * 校验
         */
        Assert.isBlank(cellarMemberAddressDb.getProvinceName(),"省名称不能为空");
        Assert.isBlank(cellarMemberAddressDb.getCityName(),"市名称不能为空");
        Assert.isBlank(cellarMemberAddressDb.getCountyName(),"县名称不能为空");
//        Assert.isNull(cellarMemberAddressDb.getProvinceId(),"省id不能为空");
//        Assert.isNull(cellarMemberAddressDb.getCityId(),"市id不能为空");
//        Assert.isNull(cellarMemberAddressDb.getCountyId(),"县id不能为空");
        Assert.isBlank(cellarMemberAddressDb.getContact(),"联系人不能为空");
        Assert.isBlank(cellarMemberAddressDb.getContactPhone(),"联系人手机号不能为空");
        Assert.isPhone(cellarMemberAddressDb.getContactPhone());
        Assert.isBlank(cellarMemberAddressDb.getDetailedAddress(),"详细地址不能为空");
        Assert.isBlank(cellarMemberAddressDb.getMapAddress(),"详细地址不能为空");
        /**
         * 保存用户地址
         */
        cellarMemberAddressDb.setState(Constants.STATE.zero.getKey());
        cellarMemberAddressDb.setMemberId(cellarMemberDbEntity.getMemberId());
        cellarMemberAddressDb.setCreateTime(new Date());
        cellarMemberAddressDbService.save(cellarMemberAddressDb);
        /**
         * 设置默认地址
         */
        setDefault(cellarMemberAddressDb,cellarMemberDbEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改用户地址信息",notes = "修改用户地址信息",response = CellarMemberAddressDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="登录token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="addressId",value="地址id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="contact",value="联系人",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="contactPhone",value="联系电话",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="provinceName",value="省名称",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="cityName",value="市名称",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="countyName",value="县名称",dataType="String",required=false,paramType="query"),
//            @ApiImplicitParam(name="provinceId",value="省id",dataType="String",required=false,paramType="query"),
//            @ApiImplicitParam(name="cityId",value="市id",dataType="String",required=false,paramType="query"),
//            @ApiImplicitParam(name="countyId",value="县id",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="detailedAddress",value="详细地址",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="mapAddress",value="地图地址",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="isDefault",value="是否默认 1 默认 0 非默认",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="longitude",value="经度",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="latitude",value="纬度",dataType="String",required=false,paramType="query"),
    })
    public R update(
            @ApiIgnore @LoginUser CellarMemberDbEntity cellarMemberDbEntity,
            @ApiIgnore CellarMemberAddressDbEntity cellarMemberAddressDb
    ){

        /**
         * 校验
         */
        Assert.isNull(cellarMemberAddressDb.getAddressId(),"地址id不能为空");
//        Assert.isNull(cellarMemberAddressDb.getProvinceId(),"省id不能为空");
//        Assert.isNull(cellarMemberAddressDb.getCityId(),"市id不能为空");
//        Assert.isNull(cellarMemberAddressDb.getCountyId(),"县id不能为空");
//        Assert.isBlank(cellarMemberAddressDb.getContact(),"联系人不能为空");
//        Assert.isBlank(cellarMemberAddressDb.getContactPhone(),"联系人手机号不能为空");
//        Assert.isPhone(cellarMemberAddressDb.getContactPhone());
//        Assert.isBlank(cellarMemberAddressDb.getDetailedAddress(),"详细地址不能为空");
        /**
         * 修改地址信息
         */
        cellarMemberAddressDbService.updateById(cellarMemberAddressDb);
        /**
         * 如果是默认地址 将其他地址修改为非默认
         */
        setDefault(cellarMemberAddressDb,cellarMemberDbEntity);

        return R.ok();
    }

    /**
     * 设置默认地址
     * @param cellarMemberAddressDb
     * @param cellarMemberDbEntity
     */
    private void setDefault(
            CellarMemberAddressDbEntity cellarMemberAddressDb,
            CellarMemberDbEntity cellarMemberDbEntity
    ) {
        if (cellarMemberAddressDb.getIsDefault().equals(Constants.ISDEFAULT.YES.getKey())) {

            CellarMemberAddressDbEntity cellarMemberAddressDbEntity = new CellarMemberAddressDbEntity();
            cellarMemberAddressDbEntity.setIsDefault(Constants.ISDEFAULT.NO.getKey());

            cellarMemberAddressDbService.update(cellarMemberAddressDbEntity,new QueryWrapper<CellarMemberAddressDbEntity>().lambda()
                    .eq(CellarMemberAddressDbEntity::getMemberId,cellarMemberDbEntity.getMemberId())
                    .notIn(CellarMemberAddressDbEntity::getAddressId,cellarMemberAddressDb.getAddressId()));
        }
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除用户地址信息",notes = "删除用户地址信息",response = CellarMemberAddressDbEntity.class)
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="登录token",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="addressIds",value="地址id",dataType="String",required=false,paramType="query"),
    })
    public R delete(@ApiIgnore Long[] addressIds){

        cellarMemberAddressDbService.removeByIds(Arrays.asList(addressIds));
        return R.ok();
    }

}
