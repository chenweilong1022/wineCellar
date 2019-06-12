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

import io.renren.modules.cellar.dao.CellarMemberVideoDbDao;
import io.renren.modules.cellar.entity.CellarMemberVideoDbEntity;
import io.renren.modules.cellar.service.CellarMemberVideoDbService;


@Service("cellarMemberVideoDbService")
public class CellarMemberVideoDbServiceImpl extends ServiceImpl<CellarMemberVideoDbDao, CellarMemberVideoDbEntity> implements CellarMemberVideoDbService {

    @Override
    public PageUtils queryPage(CellarMemberVideoDbEntity cellarMemberVideoDb) {
        IPage<CellarMemberVideoDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberVideoDbEntity>(cellarMemberVideoDb).getPage(),
                new QueryWrapper<CellarMemberVideoDbEntity>().lambda()
                .eq(CellarMemberVideoDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(ObjectUtil.isNotNull(cellarMemberVideoDb.getMemberId()),CellarMemberVideoDbEntity::getMemberId, cellarMemberVideoDb.getMemberId())
                .orderByDesc(CellarMemberVideoDbEntity::getCreateTime)
        );

        return new PageUtils(page);
    }

}
