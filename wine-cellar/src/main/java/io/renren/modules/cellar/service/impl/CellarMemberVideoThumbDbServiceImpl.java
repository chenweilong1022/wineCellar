package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberVideoThumbDbDao;
import io.renren.modules.cellar.entity.CellarMemberVideoThumbDbEntity;
import io.renren.modules.cellar.service.CellarMemberVideoThumbDbService;


@Service("cellarMemberVideoThumbDbService")
public class CellarMemberVideoThumbDbServiceImpl extends ServiceImpl<CellarMemberVideoThumbDbDao, CellarMemberVideoThumbDbEntity> implements CellarMemberVideoThumbDbService {

    @Override
    public PageUtils queryPage(CellarMemberVideoThumbDbEntity cellarMemberVideoThumbDb) {
        IPage<CellarMemberVideoThumbDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberVideoThumbDbEntity>(cellarMemberVideoThumbDb).getPage(),
                new QueryWrapper<CellarMemberVideoThumbDbEntity>()
        );

        return new PageUtils(page);
    }

}
