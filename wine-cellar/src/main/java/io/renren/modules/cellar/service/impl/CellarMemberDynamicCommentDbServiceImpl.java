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

import io.renren.modules.cellar.dao.CellarMemberDynamicCommentDbDao;
import io.renren.modules.cellar.entity.CellarMemberDynamicCommentDbEntity;
import io.renren.modules.cellar.service.CellarMemberDynamicCommentDbService;


@Service("cellarMemberDynamicCommentDbService")
public class CellarMemberDynamicCommentDbServiceImpl extends ServiceImpl<CellarMemberDynamicCommentDbDao, CellarMemberDynamicCommentDbEntity> implements CellarMemberDynamicCommentDbService {

    @Override
    public PageUtils queryPage(CellarMemberDynamicCommentDbEntity cellarMemberDynamicCommentDb) {
        IPage<CellarMemberDynamicCommentDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberDynamicCommentDbEntity>(cellarMemberDynamicCommentDb).getPage(),
                new QueryWrapper<CellarMemberDynamicCommentDbEntity>().lambda()
                .eq(ObjectUtil.isNotNull(cellarMemberDynamicCommentDb.getMemberDynamicId()),CellarMemberDynamicCommentDbEntity::getMemberDynamicId,cellarMemberDynamicCommentDb.getMemberDynamicId())
                .eq(CellarMemberDynamicCommentDbEntity::getState, Constants.STATE.zero.getKey())
                .orderByDesc(CellarMemberDynamicCommentDbEntity::getCreateTime)
        );

        return new PageUtils(page);
    }

}
