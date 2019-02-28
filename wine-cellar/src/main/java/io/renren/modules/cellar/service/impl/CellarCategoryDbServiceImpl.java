package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.sys.entity.SysAreaEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarCategoryDbDao;
import io.renren.modules.cellar.entity.CellarCategoryDbEntity;
import io.renren.modules.cellar.service.CellarCategoryDbService;


@Service("cellarCategoryDbService")
public class CellarCategoryDbServiceImpl extends ServiceImpl<CellarCategoryDbDao, CellarCategoryDbEntity> implements CellarCategoryDbService {

    @Override
    public PageUtils queryPage(CellarCategoryDbEntity cellarCategoryDb) {
        SysUserEntity userEntity = ShiroUtils.getUserEntity();
        IPage<CellarCategoryDbEntity> page = baseMapper.selectPage(
                new Query<CellarCategoryDbEntity>(cellarCategoryDb).getPage(),
                new QueryWrapper<CellarCategoryDbEntity>().lambda().
                        eq(ObjectUtil.isNotNull(userEntity.getStoreId()),CellarCategoryDbEntity::getStoreId,userEntity.getStoreId()).
                        eq(ObjectUtil.isNotNull(cellarCategoryDb.getSupCategoryId()),CellarCategoryDbEntity::getSupCategoryId,cellarCategoryDb.getSupCategoryId())
                        .like(StringUtils.isNotBlank(cellarCategoryDb.getKey()),CellarCategoryDbEntity::getCategoryName,cellarCategoryDb.getKey())

        );

        return new PageUtils(page);
    }

    @Override
    public List<CellarCategoryDbEntity> listAllLevel() {
        CellarCategoryDbEntity cellarCategoryDbEntity = new CellarCategoryDbEntity();
        cellarCategoryDbEntity.setLevel(Constants.STATE.zero.getKey());
        cellarCategoryDbEntity.setCategoryName("顶级类别");
        cellarCategoryDbEntity.setSupCategoryId(Constants.STATE.fuone.getKey().longValue());
        cellarCategoryDbEntity.setCategoryId(Constants.STATE.zero.getKey().longValue());
        listAllLevelRecursive(cellarCategoryDbEntity);
        return cellarCategoryDbEntity.getCellarCategoryDbEntities();
    }

    /**
     * 递归调用 设置类别信息
     * @param cellarCategoryDbEntity
     */
    private void listAllLevelRecursive(CellarCategoryDbEntity cellarCategoryDbEntity) {
        SysUserEntity userEntity = ShiroUtils.getUserEntity();
        /**
         * 查询当前的级别的下一级
         */
        List<CellarCategoryDbEntity> cellarCategoryDbEntities = baseMapper.selectList(new QueryWrapper<CellarCategoryDbEntity>().lambda()
                .eq(CellarCategoryDbEntity::getSupCategoryId, cellarCategoryDbEntity.getCategoryId())
                .eq(ObjectUtil.isNotNull(userEntity.getStoreId()),CellarCategoryDbEntity::getStoreId,userEntity.getStoreId())
        );

        if (cellarCategoryDbEntities == null || cellarCategoryDbEntities.size() == 0) {

        }else {
            cellarCategoryDbEntity.setCellarCategoryDbEntities(cellarCategoryDbEntities);
            for (CellarCategoryDbEntity categoryDbEntity : cellarCategoryDbEntities) {
                listAllLevelRecursive(categoryDbEntity);
            }
        }
    }

}
