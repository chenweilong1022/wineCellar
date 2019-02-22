package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
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
                new QueryWrapper<CellarStoreDbEntity>()
        );

        return new PageUtils(page);
    }

}
