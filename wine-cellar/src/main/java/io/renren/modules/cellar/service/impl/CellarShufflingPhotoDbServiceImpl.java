package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarShufflingPhotoDbDao;
import io.renren.modules.cellar.entity.CellarShufflingPhotoDbEntity;
import io.renren.modules.cellar.service.CellarShufflingPhotoDbService;


@Service("cellarShufflingPhotoDbService")
public class CellarShufflingPhotoDbServiceImpl extends ServiceImpl<CellarShufflingPhotoDbDao, CellarShufflingPhotoDbEntity> implements CellarShufflingPhotoDbService {

    @Override
    public PageUtils queryPage(CellarShufflingPhotoDbEntity cellarShufflingPhotoDb) {
        IPage<CellarShufflingPhotoDbEntity> page = baseMapper.selectPage(
                new Query<CellarShufflingPhotoDbEntity>(cellarShufflingPhotoDb).getPage(),
                new QueryWrapper<CellarShufflingPhotoDbEntity>()
        );

        return new PageUtils(page);
    }

}
