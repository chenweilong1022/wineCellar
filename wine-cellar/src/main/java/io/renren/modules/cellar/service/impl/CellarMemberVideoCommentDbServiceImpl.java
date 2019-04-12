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

import io.renren.modules.cellar.dao.CellarMemberVideoCommentDbDao;
import io.renren.modules.cellar.entity.CellarMemberVideoCommentDbEntity;
import io.renren.modules.cellar.service.CellarMemberVideoCommentDbService;


@Service("cellarMemberVideoCommentDbService")
public class CellarMemberVideoCommentDbServiceImpl extends ServiceImpl<CellarMemberVideoCommentDbDao, CellarMemberVideoCommentDbEntity> implements CellarMemberVideoCommentDbService {

    @Override
    public PageUtils queryPage(CellarMemberVideoCommentDbEntity cellarMemberVideoCommentDb) {
        IPage<CellarMemberVideoCommentDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberVideoCommentDbEntity>(cellarMemberVideoCommentDb).getPage(),
                new QueryWrapper<CellarMemberVideoCommentDbEntity>().lambda()
                .eq(CellarMemberVideoCommentDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(ObjectUtil.isNotNull(cellarMemberVideoCommentDb.getMemberVideoId()),CellarMemberVideoCommentDbEntity::getMemberVideoId,cellarMemberVideoCommentDb.getMemberVideoId())
                .orderByDesc(CellarMemberVideoCommentDbEntity::getCommentThumbNumber,CellarMemberVideoCommentDbEntity::getCreateTime)
        );

        return new PageUtils(page);
    }

}
