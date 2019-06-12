package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarFeedbackDbDao;
import io.renren.modules.cellar.entity.CellarFeedbackDbEntity;
import io.renren.modules.cellar.service.CellarFeedbackDbService;


@Service("cellarFeedbackDbService")
public class CellarFeedbackDbServiceImpl extends ServiceImpl<CellarFeedbackDbDao, CellarFeedbackDbEntity> implements CellarFeedbackDbService {

    @Override
    public PageUtils queryPage(CellarFeedbackDbEntity cellarFeedbackDb) {
        IPage<CellarFeedbackDbEntity> page = baseMapper.selectPage(
                new Query<CellarFeedbackDbEntity>(cellarFeedbackDb).getPage(),
                new QueryWrapper<CellarFeedbackDbEntity>().lambda()
                .orderByDesc(CellarFeedbackDbEntity::getCreateTime)
        );

        return new PageUtils(page);
    }

}
