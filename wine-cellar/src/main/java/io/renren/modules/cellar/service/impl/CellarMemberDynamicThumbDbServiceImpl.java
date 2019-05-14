package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberDynamicThumbDbDao;
import io.renren.modules.cellar.entity.CellarMemberDynamicThumbDbEntity;
import io.renren.modules.cellar.service.CellarMemberDynamicThumbDbService;


@Service("cellarMemberDynamicThumbDbService")
public class CellarMemberDynamicThumbDbServiceImpl extends ServiceImpl<CellarMemberDynamicThumbDbDao, CellarMemberDynamicThumbDbEntity> implements CellarMemberDynamicThumbDbService {

    @Override
    public PageUtils queryPage(CellarMemberDynamicThumbDbEntity cellarMemberDynamicThumbDb) {
        IPage<CellarMemberDynamicThumbDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberDynamicThumbDbEntity>(cellarMemberDynamicThumbDb).getPage(),
                new QueryWrapper<CellarMemberDynamicThumbDbEntity>()
        );

        return new PageUtils(page);
    }

}
