package io.renren.modules.cellar.service.impl;

import io.renren.common.constants.Constants;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberCardDbDao;
import io.renren.modules.cellar.entity.CellarMemberCardDbEntity;
import io.renren.modules.cellar.service.CellarMemberCardDbService;


@Service("cellarMemberCardDbService")
public class CellarMemberCardDbServiceImpl extends ServiceImpl<CellarMemberCardDbDao, CellarMemberCardDbEntity> implements CellarMemberCardDbService {

    @Override
    public PageUtils queryPage(CellarMemberCardDbEntity cellarMemberCardDb) {
        IPage<CellarMemberCardDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberCardDbEntity>(cellarMemberCardDb).getPage(),
                new QueryWrapper<CellarMemberCardDbEntity>().lambda()
                .orderByAsc(CellarMemberCardDbEntity::getCardBalance)
                .eq(CellarMemberCardDbEntity::getState, Constants.STATE.zero.getKey())
        );

        return new PageUtils(page);
    }

}
