package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarFranchiseDbDao;
import io.renren.modules.cellar.entity.CellarFranchiseDbEntity;
import io.renren.modules.cellar.service.CellarFranchiseDbService;


@Service("cellarFranchiseDbService")
public class CellarFranchiseDbServiceImpl extends ServiceImpl<CellarFranchiseDbDao, CellarFranchiseDbEntity> implements CellarFranchiseDbService {

    @Override
    public PageUtils queryPage(CellarFranchiseDbEntity cellarFranchiseDb) {
        IPage<CellarFranchiseDbEntity> page = baseMapper.selectPage(
                new Query<CellarFranchiseDbEntity>(cellarFranchiseDb).getPage(),
                new QueryWrapper<CellarFranchiseDbEntity>().lambda()
                .orderByDesc(CellarFranchiseDbEntity::getCreateTime)
        );

        return new PageUtils(page);
    }

}
