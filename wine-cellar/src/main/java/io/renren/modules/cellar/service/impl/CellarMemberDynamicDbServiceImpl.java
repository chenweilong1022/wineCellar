package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberDynamicDbDao;
import io.renren.modules.cellar.entity.CellarMemberDynamicDbEntity;
import io.renren.modules.cellar.service.CellarMemberDynamicDbService;


@Service("cellarMemberDynamicDbService")
public class CellarMemberDynamicDbServiceImpl extends ServiceImpl<CellarMemberDynamicDbDao, CellarMemberDynamicDbEntity> implements CellarMemberDynamicDbService {

    @Override
    public PageUtils queryPage(CellarMemberDynamicDbEntity cellarMemberDynamicDb) {
        IPage<CellarMemberDynamicDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberDynamicDbEntity>(cellarMemberDynamicDb).getPage(),
                new QueryWrapper<CellarMemberDynamicDbEntity>().lambda()
                .eq(CellarMemberDynamicDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(ObjectUtil.isNotNull(cellarMemberDynamicDb.getMemberId()),CellarMemberDynamicDbEntity::getMemberId,cellarMemberDynamicDb.getMemberId())
                .orderByDesc(CellarMemberDynamicDbEntity::getCreateTime)
        );

        return new PageUtils(page);
    }


    @Override
    public List<CellarMemberDynamicDbEntity> activeMember() {
        return baseMapper.activeMember();
    }

}
