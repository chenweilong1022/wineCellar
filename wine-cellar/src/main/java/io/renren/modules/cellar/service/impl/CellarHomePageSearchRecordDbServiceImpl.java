package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarHomePageSearchRecordDbDao;
import io.renren.modules.cellar.entity.CellarHomePageSearchRecordDbEntity;
import io.renren.modules.cellar.service.CellarHomePageSearchRecordDbService;


@Service("cellarHomePageSearchRecordDbService")
public class CellarHomePageSearchRecordDbServiceImpl extends ServiceImpl<CellarHomePageSearchRecordDbDao, CellarHomePageSearchRecordDbEntity> implements CellarHomePageSearchRecordDbService {

    @Override
    public PageUtils queryPage(CellarHomePageSearchRecordDbEntity cellarHomePageSearchRecordDb) {
        IPage<CellarHomePageSearchRecordDbEntity> page = baseMapper.selectPage(
                new Query<CellarHomePageSearchRecordDbEntity>(cellarHomePageSearchRecordDb).getPage(),
                new QueryWrapper<CellarHomePageSearchRecordDbEntity>()
        );

        return new PageUtils(page);
    }

}
