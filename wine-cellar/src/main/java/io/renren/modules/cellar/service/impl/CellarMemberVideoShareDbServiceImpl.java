package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberVideoShareDbDao;
import io.renren.modules.cellar.entity.CellarMemberVideoShareDbEntity;
import io.renren.modules.cellar.service.CellarMemberVideoShareDbService;


@Service("cellarMemberVideoShareDbService")
public class CellarMemberVideoShareDbServiceImpl extends ServiceImpl<CellarMemberVideoShareDbDao, CellarMemberVideoShareDbEntity> implements CellarMemberVideoShareDbService {

    @Override
    public PageUtils queryPage(CellarMemberVideoShareDbEntity cellarMemberVideoShareDb) {
        IPage<CellarMemberVideoShareDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberVideoShareDbEntity>(cellarMemberVideoShareDb).getPage(),
                new QueryWrapper<CellarMemberVideoShareDbEntity>()
        );

        return new PageUtils(page);
    }

}
