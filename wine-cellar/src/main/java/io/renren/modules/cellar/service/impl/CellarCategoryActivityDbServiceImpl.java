package io.renren.modules.cellar.service.impl;

import io.renren.common.constants.Constants;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarCategoryActivityDbDao;
import io.renren.modules.cellar.entity.CellarCategoryActivityDbEntity;
import io.renren.modules.cellar.service.CellarCategoryActivityDbService;


@Service("cellarCategoryActivityDbService")
public class CellarCategoryActivityDbServiceImpl extends ServiceImpl<CellarCategoryActivityDbDao, CellarCategoryActivityDbEntity> implements CellarCategoryActivityDbService {

    @Override
    public PageUtils queryPage(CellarCategoryActivityDbEntity cellarCategoryActivityDb) {
        IPage<CellarCategoryActivityDbEntity> page = baseMapper.selectPage(
                new Query<CellarCategoryActivityDbEntity>(cellarCategoryActivityDb).getPage(),
                new QueryWrapper<CellarCategoryActivityDbEntity>().lambda()
                .eq(CellarCategoryActivityDbEntity::getState, Constants.STATE.zero.getKey())
        );

        return new PageUtils(page);
    }

}
