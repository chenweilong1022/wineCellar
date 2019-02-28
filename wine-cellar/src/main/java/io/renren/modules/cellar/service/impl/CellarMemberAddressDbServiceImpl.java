package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberAddressDbDao;
import io.renren.modules.cellar.entity.CellarMemberAddressDbEntity;
import io.renren.modules.cellar.service.CellarMemberAddressDbService;


@Service("cellarMemberAddressDbService")
public class CellarMemberAddressDbServiceImpl extends ServiceImpl<CellarMemberAddressDbDao, CellarMemberAddressDbEntity> implements CellarMemberAddressDbService {

    @Override
    public PageUtils queryPage(CellarMemberAddressDbEntity cellarMemberAddressDb) {
        IPage<CellarMemberAddressDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberAddressDbEntity>(cellarMemberAddressDb).getPage(),
                new QueryWrapper<CellarMemberAddressDbEntity>().lambda()
                        .eq(CellarMemberAddressDbEntity::getMemberId,cellarMemberAddressDb.getMemberId())
                        .orderByDesc(CellarMemberAddressDbEntity::getIsDefault)
        );

        return new PageUtils(page);
    }

}
