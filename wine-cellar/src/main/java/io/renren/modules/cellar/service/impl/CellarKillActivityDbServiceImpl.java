package io.renren.modules.cellar.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarKillActivityDbDao;
import io.renren.modules.cellar.entity.CellarKillActivityDbEntity;
import io.renren.modules.cellar.service.CellarKillActivityDbService;


@Service("cellarKillActivityDbService")
public class CellarKillActivityDbServiceImpl extends ServiceImpl<CellarKillActivityDbDao, CellarKillActivityDbEntity> implements CellarKillActivityDbService {

    @Override
    public PageUtils queryPage(CellarKillActivityDbEntity cellarKillActivityDb) {
        /**
         * 来自管理后台
         */
        if (cellarKillActivityDb.isSystem()) {
            IPage<CellarKillActivityDbEntity> page = baseMapper.selectPage(
                    new Query<CellarKillActivityDbEntity>(cellarKillActivityDb).getPage(),
                    new QueryWrapper<CellarKillActivityDbEntity>().lambda()
                            .eq(ObjectUtil.isNotNull(cellarKillActivityDb.getStoreId()),CellarKillActivityDbEntity::getStoreId,cellarKillActivityDb.getStoreId())
                            .eq(CellarKillActivityDbEntity::getState, Constants.STATE.zero.getKey())
                            .orderByDesc(CellarKillActivityDbEntity::getKillStartTime)
            );
            return new PageUtils(page);
        }
        /**
         * app接口
         */
        IPage<CellarKillActivityDbEntity> page = baseMapper.selectPage(
                new Query<CellarKillActivityDbEntity>(cellarKillActivityDb).getPage(),
                new QueryWrapper<CellarKillActivityDbEntity>().lambda()
                .groupBy(CellarKillActivityDbEntity::getKillStartTime)
                .gt(CellarKillActivityDbEntity::getKillStartTime,DateUtil.beginOfDay(new Date()))
                .lt(CellarKillActivityDbEntity::getKillStartTime,DateUtil.endOfDay(new Date()))
                .eq(ObjectUtil.isNotNull(cellarKillActivityDb.getStoreId()),CellarKillActivityDbEntity::getStoreId,cellarKillActivityDb.getStoreId())
                .eq(CellarKillActivityDbEntity::getState, Constants.STATE.zero.getKey())
                .orderByAsc(CellarKillActivityDbEntity::getKillStartTime)
        );

        List<CellarKillActivityDbEntity> records = page.getRecords();
        for (CellarKillActivityDbEntity cellarKillActivityDbEntity : records) {
            cellarKillActivityDbEntity.setFlag(false);
            List<CellarKillActivityDbEntity> cellarKillActivityDbEntities = baseMapper.selectList(new QueryWrapper<CellarKillActivityDbEntity>().lambda()
                    .eq(ObjectUtil.isNotNull(cellarKillActivityDb.getStoreId()), CellarKillActivityDbEntity::getStoreId, cellarKillActivityDb.getStoreId())
                    .eq(CellarKillActivityDbEntity::getState, Constants.STATE.zero.getKey())
                    .eq(CellarKillActivityDbEntity::getKillStartTime, cellarKillActivityDbEntity.getKillStartTime())
            );
            cellarKillActivityDbEntity.setCellarKillActivityDbEntities(cellarKillActivityDbEntities);
        }
        return new PageUtils(page);
    }

}
