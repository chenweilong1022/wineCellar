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

import io.renren.modules.cellar.dao.CellarMemberBargainingRecordDbDao;
import io.renren.modules.cellar.entity.CellarMemberBargainingRecordDbEntity;
import io.renren.modules.cellar.service.CellarMemberBargainingRecordDbService;


@Service("cellarMemberBargainingRecordDbService")
public class CellarMemberBargainingRecordDbServiceImpl extends ServiceImpl<CellarMemberBargainingRecordDbDao, CellarMemberBargainingRecordDbEntity> implements CellarMemberBargainingRecordDbService {

    @Override
    public PageUtils queryPage(CellarMemberBargainingRecordDbEntity cellarMemberBargainingRecordDb) {
        IPage<CellarMemberBargainingRecordDbEntity> page = baseMapper.selectPage(
                new Query<CellarMemberBargainingRecordDbEntity>(cellarMemberBargainingRecordDb).getPage(),
                new QueryWrapper<CellarMemberBargainingRecordDbEntity>().lambda()
                .eq(CellarMemberBargainingRecordDbEntity::getState, Constants.STATE.zero.getKey())
                .eq(ObjectUtil.isNotNull(cellarMemberBargainingRecordDb.getMemberBargainingInformationId()),CellarMemberBargainingRecordDbEntity::getMemberBargainingInformationId, cellarMemberBargainingRecordDb.getMemberBargainingInformationId())
        );
        return new PageUtils(page);
    }

}
