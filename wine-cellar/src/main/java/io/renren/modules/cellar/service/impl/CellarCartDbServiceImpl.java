package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarCartDbDao;
import io.renren.modules.cellar.entity.CellarCartDbEntity;
import io.renren.modules.cellar.service.CellarCartDbService;


@Service("cellarCartDbService")
public class CellarCartDbServiceImpl extends ServiceImpl<CellarCartDbDao, CellarCartDbEntity> implements CellarCartDbService {

    @Override
    public PageUtils queryPage(CellarCartDbEntity cellarCartDb) {
        IPage<CellarCartDbEntity> page = baseMapper.selectPage(
                new Query<CellarCartDbEntity>(cellarCartDb).getPage(),
                new QueryWrapper<CellarCartDbEntity>()
        );

        return new PageUtils(page);
    }

}
