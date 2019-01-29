package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberDbDao;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.service.CellarMemberDbService;


@Service("cellarMemberDbService")
public class CellarMemberDbServiceImpl extends ServiceImpl<CellarMemberDbDao, CellarMemberDbEntity> implements CellarMemberDbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CellarMemberDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberDbEntity>(params).getPage(),
                new QueryWrapper<CellarMemberDbEntity>()
        );

        return new PageUtils(page);
    }

}
