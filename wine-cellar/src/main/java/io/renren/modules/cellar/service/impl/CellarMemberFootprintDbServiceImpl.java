package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberFootprintDbDao;
import io.renren.modules.cellar.entity.CellarMemberFootprintDbEntity;
import io.renren.modules.cellar.service.CellarMemberFootprintDbService;


@Service("cellarMemberFootprintDbService")
public class CellarMemberFootprintDbServiceImpl extends ServiceImpl<CellarMemberFootprintDbDao, CellarMemberFootprintDbEntity> implements CellarMemberFootprintDbService {

    @Override
    public PageUtils queryPage(CellarMemberFootprintDbEntity cellarMemberFootprintDb) {
        IPage<CellarMemberFootprintDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberFootprintDbEntity>(cellarMemberFootprintDb).getPage(),
                new QueryWrapper<CellarMemberFootprintDbEntity>().lambda()
                .eq(ObjectUtil.isNotNull(cellarMemberFootprintDb.getMemberId()),CellarMemberFootprintDbEntity::getMemberId,cellarMemberFootprintDb.getMemberId())
                .eq(CellarMemberFootprintDbEntity::getState, Constants.STATE.zero.getKey())
                .orderByDesc(CellarMemberFootprintDbEntity::getLastTime)
        );

        return new PageUtils(page);
    }

}
