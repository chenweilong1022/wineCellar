package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import io.renren.common.utils.Constant;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberCollectionDbDao;
import io.renren.modules.cellar.entity.CellarMemberCollectionDbEntity;
import io.renren.modules.cellar.service.CellarMemberCollectionDbService;


@Service("cellarMemberCollectionDbService")
public class CellarMemberCollectionDbServiceImpl extends ServiceImpl<CellarMemberCollectionDbDao, CellarMemberCollectionDbEntity> implements CellarMemberCollectionDbService {

    @Override
    public PageUtils queryPage(CellarMemberCollectionDbEntity cellarMemberCollectionDb) {
        IPage<CellarMemberCollectionDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberCollectionDbEntity>(cellarMemberCollectionDb).getPage(),
                new QueryWrapper<CellarMemberCollectionDbEntity>().lambda()
                .eq(ObjectUtil.isNotNull(cellarMemberCollectionDb.getMemberId()),CellarMemberCollectionDbEntity::getMemberId,cellarMemberCollectionDb.getMemberId())
                .eq(ObjectUtil.isNotNull(cellarMemberCollectionDb.getCollectionType()),CellarMemberCollectionDbEntity::getCollectionType,cellarMemberCollectionDb.getCollectionType())
                .eq(CellarMemberCollectionDbEntity::getState, Constants.STATE.zero.getKey())
        );

        return new PageUtils(page);
    }

}
