package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarCommodityDbDao;
import io.renren.modules.cellar.entity.CellarCommodityDbEntity;
import io.renren.modules.cellar.service.CellarCommodityDbService;


@Service("cellarCommodityDbService")
public class CellarCommodityDbServiceImpl extends ServiceImpl<CellarCommodityDbDao, CellarCommodityDbEntity> implements CellarCommodityDbService {

    @Override
    public PageUtils queryPage(CellarCommodityDbEntity cellarCommodityDb) {
        IPage<CellarCommodityDbEntity> page = baseMapper.selectPage(
                new Query<CellarCommodityDbEntity>(cellarCommodityDb).getPage(),
                new QueryWrapper<CellarCommodityDbEntity>()
        );

        return new PageUtils(page);
    }

}
