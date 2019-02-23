package io.renren.modules.sys.service.impl;

import io.renren.common.constants.Constants;
import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysAreaDao;
import io.renren.modules.sys.entity.SysAreaEntity;
import io.renren.modules.sys.service.SysAreaService;


@Service("sysAreaService")
public class SysAreaServiceImpl extends ServiceImpl<SysAreaDao, SysAreaEntity> implements SysAreaService {

    @Override
    public PageUtils queryPage(SysAreaEntity sysArea) {
        IPage<SysAreaEntity> page = baseMapper.selectPage(
                new Query<SysAreaEntity>(sysArea).getPage(),
                new QueryWrapper<SysAreaEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SysAreaEntity> listThreeLevel() {
        SysAreaEntity sysAreaEntity = new SysAreaEntity();
        sysAreaEntity.setId(Constants.Number.one.getKey());
        listThreeLevelRecursive(sysAreaEntity);
        return sysAreaEntity.getSysAreaEntities();
    }

    /**
     * 递归调用 设置三级省市级数据
     * @param sysAreaEntity
     */
    private void listThreeLevelRecursive(SysAreaEntity sysAreaEntity) {
        /**
         * 查询当前的级别的下一级
         */
        List<SysAreaEntity> sysAreaEntities = baseMapper.selectList(new QueryWrapper<SysAreaEntity>().lambda()
                .eq(SysAreaEntity::getPid, sysAreaEntity.getId()));

        if (sysAreaEntities == null || sysAreaEntities.size() == 0) {

        }else {
            sysAreaEntity.setSysAreaEntities(sysAreaEntities);
            for (SysAreaEntity areaEntity : sysAreaEntities) {
                listThreeLevelRecursive(areaEntity);
            }
        }
    }

}
