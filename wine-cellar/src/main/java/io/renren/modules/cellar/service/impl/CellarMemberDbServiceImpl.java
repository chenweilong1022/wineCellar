package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.exception.RRException;
import io.renren.common.validator.Assert;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberDbDao;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarMemberDbService;


@Service("cellarMemberDbService")
public class CellarMemberDbServiceImpl extends ServiceImpl<CellarMemberDbDao, CellarMemberDbEntity> implements CellarMemberDbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params,CellarMemberDbEntity cellarMemberDbEntity) {
        IPage<CellarMemberDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberDbEntity>(params).getPage(),
                new QueryWrapper<CellarMemberDbEntity>().lambda()
                .notIn(CellarMemberDbEntity::getState, Constants.STATE.funine.getKey())
                .eq(!StrUtil.isBlankIfStr(params.get("gender").toString()),CellarMemberDbEntity::getGender,params.get("gender"))
                .eq(!StrUtil.isBlankIfStr(params.get("state").toString()),CellarMemberDbEntity::getState,params.get("state"))
                .apply(!StrUtil.isBlankIfStr(params.get("key").toString()),"concat(mobile_phone,',',nickname) like concat('%','"+params.get("key")+"','%')")
        );

        return new PageUtils(page);
    }

    @Override
    public CellarMemberDbEntity login(CellarMemberDbEntity cellarMemberDbEntity) {
        /**
         * 根据手机号查询账号
         */
        CellarMemberDbEntity cellarMemberDbEntityByPhone = baseMapper.selectOne(new QueryWrapper<CellarMemberDbEntity>()
                .lambda().eq(CellarMemberDbEntity::getMobilePhone, cellarMemberDbEntity.getMobilePhone()));
        /**
         * 判断是否为空
         */
        Assert.isNull(cellarMemberDbEntityByPhone, "手机号未注册");
        /**
         * 校验密码
         */
        if (!DigestUtils.sha256Hex(cellarMemberDbEntity.getPassword()).equals(cellarMemberDbEntityByPhone.getPassword())) {
            throw new RRException("账号或密码错误");
        }
        return cellarMemberDbEntityByPhone;
    }

}
