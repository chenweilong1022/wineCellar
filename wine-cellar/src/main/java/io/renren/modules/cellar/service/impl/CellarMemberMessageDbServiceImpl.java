package io.renren.modules.cellar.service.impl;

import io.renren.common.constants.Constants;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberMessageDbDao;
import io.renren.modules.cellar.entity.CellarMemberMessageDbEntity;
import io.renren.modules.cellar.service.CellarMemberMessageDbService;


@Service("cellarMemberMessageDbService")
public class CellarMemberMessageDbServiceImpl extends ServiceImpl<CellarMemberMessageDbDao, CellarMemberMessageDbEntity> implements CellarMemberMessageDbService {

    @Override
    public PageUtils queryPage(CellarMemberMessageDbEntity cellarMemberMessageDb) {
        IPage<CellarMemberMessageDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberMessageDbEntity>(cellarMemberMessageDb).getPage(),
                new QueryWrapper<CellarMemberMessageDbEntity>().lambda()
                .eq(CellarMemberMessageDbEntity::getMemberId, cellarMemberMessageDb.getMemberId())
                .eq(CellarMemberMessageDbEntity::getMessageType, cellarMemberMessageDb.getMessageType())
                .orderByDesc(CellarMemberMessageDbEntity::getCreateTime)
                .eq(CellarMemberMessageDbEntity::getState, Constants.STATE.zero.getKey())
        );

        return new PageUtils(page);
    }

}
