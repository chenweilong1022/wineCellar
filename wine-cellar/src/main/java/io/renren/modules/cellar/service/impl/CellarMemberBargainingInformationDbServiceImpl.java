package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberBargainingInformationDbDao;
import io.renren.modules.cellar.entity.CellarMemberBargainingInformationDbEntity;
import io.renren.modules.cellar.service.CellarMemberBargainingInformationDbService;


@Service("cellarMemberBargainingInformationDbService")
public class CellarMemberBargainingInformationDbServiceImpl extends ServiceImpl<CellarMemberBargainingInformationDbDao, CellarMemberBargainingInformationDbEntity> implements CellarMemberBargainingInformationDbService {

    @Override
    public PageUtils queryPage(CellarMemberBargainingInformationDbEntity cellarMemberBargainingInformationDb) {
        IPage<CellarMemberBargainingInformationDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberBargainingInformationDbEntity>(cellarMemberBargainingInformationDb).getPage(),
                new QueryWrapper<CellarMemberBargainingInformationDbEntity>()
        );

        return new PageUtils(page);
    }

}
