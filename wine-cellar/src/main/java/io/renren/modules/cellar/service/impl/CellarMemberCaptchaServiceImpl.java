package io.renren.modules.cellar.service.impl;

import io.renren.common.exception.RRException;
import io.renren.common.utils.R;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberCaptchaDao;
import io.renren.modules.cellar.entity.CellarMemberCaptchaEntity;
import io.renren.modules.cellar.service.CellarMemberCaptchaService;


@Service("cellarMemberCaptchaService")
public class CellarMemberCaptchaServiceImpl extends ServiceImpl<CellarMemberCaptchaDao, CellarMemberCaptchaEntity> implements CellarMemberCaptchaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CellarMemberCaptchaEntity> page = baseMapper.selectPage(
                new Query<CellarMemberCaptchaEntity>(params).getPage(),
                new QueryWrapper<CellarMemberCaptchaEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void checkCode(String uuid, String phone, String code) {

        /**
         * 根据手机号 uuid 查找验证码记录
         */
        CellarMemberCaptchaEntity cellarMemberCaptchaEntity = baseMapper.selectOne(new QueryWrapper<CellarMemberCaptchaEntity>().lambda()
                .eq(CellarMemberCaptchaEntity::getUuid, uuid)
                .eq(CellarMemberCaptchaEntity::getMemberMobile, phone));

        if (cellarMemberCaptchaEntity == null) {
            throw new RRException("验证码查找失败");
        }
        if (!cellarMemberCaptchaEntity.getCode().equalsIgnoreCase(code)) {
            throw new RRException("验证码错误");
        }

        if (cellarMemberCaptchaEntity.getExpireTime().getTime() <= System.currentTimeMillis()) {
            throw new RRException("验证码已失效");
        }


    }

}
