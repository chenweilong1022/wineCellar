package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarCommodityDbDao;
import io.renren.modules.cellar.entity.CellarCommodityDbEntity;
import io.renren.modules.cellar.service.CellarCommodityDbService;


@Service("cellarCommodityDbService")
public class CellarCommodityDbServiceImpl extends ServiceImpl<CellarCommodityDbDao, CellarCommodityDbEntity> implements CellarCommodityDbService {

    @Override
    public PageUtils queryPage(CellarCommodityDbEntity cellarCommodityDb) {
        SysUserEntity userEntity = ShiroUtils.getUserEntity();
        IPage<CellarCommodityDbEntity> page = baseMapper.selectPage(
                new Query<CellarCommodityDbEntity>(cellarCommodityDb).getPage(),
                new QueryWrapper<CellarCommodityDbEntity>().lambda()
                        .eq(ObjectUtil.isNotNull(userEntity.getStoreId()),CellarCommodityDbEntity::getStoreId,userEntity.getStoreId())
                        .like(StringUtils.isNotBlank(cellarCommodityDb.getKey()),CellarCommodityDbEntity::getCommodityName,cellarCommodityDb.getKey())
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageApp(CellarCommodityDbEntity cellarCommodityDb) {
        IPage<CellarCommodityDbEntity> page = baseMapper.selectPage(
                new Query<CellarCommodityDbEntity>(cellarCommodityDb).getPage(),
                new QueryWrapper<CellarCommodityDbEntity>().lambda()
                        .eq(CellarCommodityDbEntity::getState, Constants.STATE.zero.getKey())
                        .eq(CellarCommodityDbEntity::getStoreId,cellarCommodityDb.getStoreId())
                        .eq(CellarCommodityDbEntity::getCategoryId,cellarCommodityDb.getCategoryId())
        );
        return new PageUtils(page);
    }

}
