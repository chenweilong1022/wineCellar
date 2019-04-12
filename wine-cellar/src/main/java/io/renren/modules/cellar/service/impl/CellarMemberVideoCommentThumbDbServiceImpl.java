package io.renren.modules.cellar.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarMemberVideoCommentThumbDbDao;
import io.renren.modules.cellar.entity.CellarMemberVideoCommentThumbDbEntity;
import io.renren.modules.cellar.service.CellarMemberVideoCommentThumbDbService;


@Service("cellarMemberVideoCommentThumbDbService")
public class CellarMemberVideoCommentThumbDbServiceImpl extends ServiceImpl<CellarMemberVideoCommentThumbDbDao, CellarMemberVideoCommentThumbDbEntity> implements CellarMemberVideoCommentThumbDbService {

    @Override
    public PageUtils queryPage(CellarMemberVideoCommentThumbDbEntity cellarMemberVideoCommentThumbDb) {
        IPage<CellarMemberVideoCommentThumbDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberVideoCommentThumbDbEntity>(cellarMemberVideoCommentThumbDb).getPage(),
                new QueryWrapper<CellarMemberVideoCommentThumbDbEntity>()
        );

        return new PageUtils(page);
    }

}
