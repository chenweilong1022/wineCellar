package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import io.renren.modules.cellar.entity.CellarKillActivityDbEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarGroupActivityDbDao;
import io.renren.modules.cellar.entity.CellarGroupActivityDbEntity;
import io.renren.modules.cellar.service.CellarGroupActivityDbService;


@Service("cellarGroupActivityDbService")
public class CellarGroupActivityDbServiceImpl extends ServiceImpl<CellarGroupActivityDbDao, CellarGroupActivityDbEntity> implements CellarGroupActivityDbService {

    @Override
    public PageUtils queryPage(CellarGroupActivityDbEntity cellarGroupActivityDb) {
        IPage<CellarGroupActivityDbEntity> page = baseMapper.selectPage(
                new Query<CellarGroupActivityDbEntity>(cellarGroupActivityDb).getPage(),
                new QueryWrapper<CellarGroupActivityDbEntity>().lambda()
                .eq(CellarGroupActivityDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(ObjectUtil.isNotNull(cellarGroupActivityDb.getStoreId()), CellarGroupActivityDbEntity::getStoreId,cellarGroupActivityDb.getStoreId())
                .orderByDesc(CellarGroupActivityDbEntity::getGroupStartTime)
        );

        return new PageUtils(page);
    }

}
