package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.utils.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarStoreDbDao;
import io.renren.modules.cellar.entity.CellarStoreDbEntity;
import io.renren.modules.cellar.service.CellarStoreDbService;


@Service("cellarStoreDbService")
public class CellarStoreDbServiceImpl extends ServiceImpl<CellarStoreDbDao, CellarStoreDbEntity> implements CellarStoreDbService {

    @Override
    public PageUtils queryPage(CellarStoreDbEntity cellarStoreDb) {
        IPage<CellarStoreDbEntity> page = baseMapper.selectPage(
                new Query<CellarStoreDbEntity>(cellarStoreDb).getPage(),
                new QueryWrapper<CellarStoreDbEntity>().lambda()
                        .like(StringUtils.isNotBlank(cellarStoreDb.getKey()),CellarStoreDbEntity::getStoreName,cellarStoreDb.getKey())
                        .eq(ObjectUtil.isNotNull(cellarStoreDb.getStoreId()),CellarStoreDbEntity::getStoreId,cellarStoreDb.getStoreId())
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils nearStoreList(CellarStoreDbEntity cellarStoreDb) {

        IPage<CellarStoreDbEntity> page = baseMapper.nearStoreList(
                new Query<CellarStoreDbEntity>(cellarStoreDb).getPage(),
                cellarStoreDb
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils indexList(CellarStoreDbEntity cellarStoreDb) {
        IPage<CellarStoreDbEntity> page = baseMapper.indexList(
                new Query<CellarStoreDbEntity>(cellarStoreDb).getPage(),
                cellarStoreDb
        );
        return new PageUtils(page);
    }

}
