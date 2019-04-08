package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import io.renren.modules.cellar.entity.CellarGroupActivityDbEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarBargainingActivityDbDao;
import io.renren.modules.cellar.entity.CellarBargainingActivityDbEntity;
import io.renren.modules.cellar.service.CellarBargainingActivityDbService;


@Service("cellarBargainingActivityDbService")
public class CellarBargainingActivityDbServiceImpl extends ServiceImpl<CellarBargainingActivityDbDao, CellarBargainingActivityDbEntity> implements CellarBargainingActivityDbService {

    @Override
    public PageUtils queryPage(CellarBargainingActivityDbEntity cellarBargainingActivityDb) {
        IPage<CellarBargainingActivityDbEntity> page = baseMapper.selectPage(
                new Query<CellarBargainingActivityDbEntity>(cellarBargainingActivityDb).getPage(),
                new QueryWrapper<CellarBargainingActivityDbEntity>().lambda()
                .eq(CellarBargainingActivityDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(ObjectUtil.isNotNull(cellarBargainingActivityDb.getStoreId()), CellarBargainingActivityDbEntity::getStoreId,cellarBargainingActivityDb.getStoreId())
                .orderByDesc(CellarBargainingActivityDbEntity::getCreateTime)
        );

        return new PageUtils(page);
    }

}
