package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.sys.entity.SysUserEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarCouponDbDao;
import io.renren.modules.cellar.entity.CellarCouponDbEntity;
import io.renren.modules.cellar.service.CellarCouponDbService;


@Service("cellarCouponDbService")
public class CellarCouponDbServiceImpl extends ServiceImpl<CellarCouponDbDao, CellarCouponDbEntity> implements CellarCouponDbService {

    @Override
    public PageUtils queryPage(CellarCouponDbEntity cellarCouponDb) {

        IPage<CellarCouponDbEntity> page = baseMapper.selectPage(
                new Query<CellarCouponDbEntity>(cellarCouponDb).getPage(),
                new QueryWrapper<CellarCouponDbEntity>().lambda()
                .eq(ObjectUtil.isNotNull(cellarCouponDb.getStoreId()),CellarCouponDbEntity::getStoreId,cellarCouponDb.getStoreId())
        );
        return new PageUtils(page);
    }

}
