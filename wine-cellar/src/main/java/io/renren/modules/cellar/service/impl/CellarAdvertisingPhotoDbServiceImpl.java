package io.renren.modules.cellar.service.impl;

import io.renren.common.constants.Constants;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarAdvertisingPhotoDbDao;
import io.renren.modules.cellar.entity.CellarAdvertisingPhotoDbEntity;
import io.renren.modules.cellar.service.CellarAdvertisingPhotoDbService;


@Service("cellarAdvertisingPhotoDbService")
public class CellarAdvertisingPhotoDbServiceImpl extends ServiceImpl<CellarAdvertisingPhotoDbDao, CellarAdvertisingPhotoDbEntity> implements CellarAdvertisingPhotoDbService {

    @Override
    public PageUtils queryPage(CellarAdvertisingPhotoDbEntity cellarAdvertisingPhotoDb) {
        cellarAdvertisingPhotoDb.setPage(Constants.Number.one.getKey());
        cellarAdvertisingPhotoDb.setLimit(Constants.Number.one.getKey());
        IPage<CellarAdvertisingPhotoDbEntity> page = baseMapper.selectPage(
                new Query<CellarAdvertisingPhotoDbEntity>(cellarAdvertisingPhotoDb).getPage(),
                new QueryWrapper<CellarAdvertisingPhotoDbEntity>().lambda()
                .orderByDesc(CellarAdvertisingPhotoDbEntity::getCreateTime)
                .eq(CellarAdvertisingPhotoDbEntity::getState, Constants.STATE.zero.getKey())
        );

        return new PageUtils(page);
    }

}
