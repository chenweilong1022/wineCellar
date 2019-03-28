package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarCommodityEntryDbDao;
import io.renren.modules.cellar.entity.CellarCommodityEntryDbEntity;
import io.renren.modules.cellar.service.CellarCommodityEntryDbService;


@Service("cellarCommodityEntryDbService")
public class CellarCommodityEntryDbServiceImpl extends ServiceImpl<CellarCommodityEntryDbDao, CellarCommodityEntryDbEntity> implements CellarCommodityEntryDbService {

    @Override
    public PageUtils queryPage(CellarCommodityEntryDbEntity cellarCommodityEntryDb) {
        IPage<CellarCommodityEntryDbEntity> page = baseMapper.selectPage(
                new Query<CellarCommodityEntryDbEntity>(cellarCommodityEntryDb).getPage(),
                new QueryWrapper<CellarCommodityEntryDbEntity>()
        );

        return new PageUtils(page);
    }

}
