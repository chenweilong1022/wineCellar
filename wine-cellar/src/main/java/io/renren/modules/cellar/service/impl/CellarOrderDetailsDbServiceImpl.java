package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarOrderDetailsDbDao;
import io.renren.modules.cellar.entity.CellarOrderDetailsDbEntity;
import io.renren.modules.cellar.service.CellarOrderDetailsDbService;


@Service("cellarOrderDetailsDbService")
public class CellarOrderDetailsDbServiceImpl extends ServiceImpl<CellarOrderDetailsDbDao, CellarOrderDetailsDbEntity> implements CellarOrderDetailsDbService {

    @Override
    public PageUtils queryPage(CellarOrderDetailsDbEntity cellarOrderDetailsDb) {
        IPage<CellarOrderDetailsDbEntity> page = baseMapper.selectPage(
                new Query<CellarOrderDetailsDbEntity>(cellarOrderDetailsDb).getPage(),
                new QueryWrapper<CellarOrderDetailsDbEntity>()
        );

        return new PageUtils(page);
    }

}
