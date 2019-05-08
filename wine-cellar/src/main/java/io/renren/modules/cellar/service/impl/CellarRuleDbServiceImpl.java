package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarRuleDbDao;
import io.renren.modules.cellar.entity.CellarRuleDbEntity;
import io.renren.modules.cellar.service.CellarRuleDbService;


@Service("cellarRuleDbService")
public class CellarRuleDbServiceImpl extends ServiceImpl<CellarRuleDbDao, CellarRuleDbEntity> implements CellarRuleDbService {

    @Override
    public PageUtils queryPage(CellarRuleDbEntity cellarRuleDb) {
        IPage<CellarRuleDbEntity> page = baseMapper.selectPage(
                new Query<CellarRuleDbEntity>(cellarRuleDb).getPage(),
                new QueryWrapper<CellarRuleDbEntity>()
        );

        return new PageUtils(page);
    }

}
