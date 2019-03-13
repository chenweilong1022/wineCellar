package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberCouponDbDao;
import io.renren.modules.cellar.entity.CellarMemberCouponDbEntity;
import io.renren.modules.cellar.service.CellarMemberCouponDbService;


@Service("cellarMemberCouponDbService")
public class CellarMemberCouponDbServiceImpl extends ServiceImpl<CellarMemberCouponDbDao, CellarMemberCouponDbEntity> implements CellarMemberCouponDbService {

    @Override
    public PageUtils queryPage(CellarMemberCouponDbEntity cellarMemberCouponDb) {
        IPage<CellarMemberCouponDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberCouponDbEntity>(cellarMemberCouponDb).getPage(),
                new QueryWrapper<CellarMemberCouponDbEntity>().lambda()
                .eq(ObjectUtil.isNotNull(cellarMemberCouponDb.getMemberId()),CellarMemberCouponDbEntity::getMemberId,cellarMemberCouponDb.getMemberId())
                .eq(CellarMemberCouponDbEntity::getState, Constants.STATE.zero.getKey())
        );

        return new PageUtils(page);
    }

}
