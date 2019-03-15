package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarConfigDbDao;
import io.renren.modules.cellar.entity.CellarConfigDbEntity;
import io.renren.modules.cellar.service.CellarConfigDbService;


@Service("cellarConfigDbService")
public class CellarConfigDbServiceImpl extends ServiceImpl<CellarConfigDbDao, CellarConfigDbEntity> implements CellarConfigDbService {

    @Override
    public PageUtils queryPage(CellarConfigDbEntity cellarConfigDb) {
        IPage<CellarConfigDbEntity> page = baseMapper.selectPage(
                new Query<CellarConfigDbEntity>(cellarConfigDb).getPage(),
                new QueryWrapper<CellarConfigDbEntity>()
        );

        return new PageUtils(page);
    }

}
