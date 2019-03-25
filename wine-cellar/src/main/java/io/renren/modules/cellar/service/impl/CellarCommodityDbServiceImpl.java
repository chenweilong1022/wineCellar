package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import io.renren.common.utils.ShiroUtils;
import io.renren.common.validator.Assert;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarCommodityDbDao;
import io.renren.modules.cellar.entity.CellarCommodityDbEntity;
import io.renren.modules.cellar.service.CellarCommodityDbService;


@Service("cellarCommodityDbService")
public class CellarCommodityDbServiceImpl extends ServiceImpl<CellarCommodityDbDao, CellarCommodityDbEntity> implements CellarCommodityDbService {

    @Autowired
    private CellarCommodityDbService cellarCommodityDbService;

    @Override
    public PageUtils queryPage(CellarCommodityDbEntity cellarCommodityDb) {
        SysUserEntity userEntity = ShiroUtils.getUserEntity();
        IPage<CellarCommodityDbEntity> page = baseMapper.selectPage(
                new Query<CellarCommodityDbEntity>(cellarCommodityDb).getPage(),
                new QueryWrapper<CellarCommodityDbEntity>().lambda()
                        .eq(ObjectUtil.isNotNull(userEntity.getStoreId()),CellarCommodityDbEntity::getStoreId,userEntity.getStoreId())
                        .like(StringUtils.isNotBlank(cellarCommodityDb.getKey()),CellarCommodityDbEntity::getCommodityName,cellarCommodityDb.getKey())
                        .eq(CellarCommodityDbEntity::getState, Constants.STATE.zero.getKey())
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageApp(CellarCommodityDbEntity cellarCommodityDb) {
        IPage<CellarCommodityDbEntity> page = baseMapper.selectPage(
                new Query<CellarCommodityDbEntity>(cellarCommodityDb).getPage(),
                new QueryWrapper<CellarCommodityDbEntity>().lambda()
                        .eq(CellarCommodityDbEntity::getState, Constants.STATE.zero.getKey())
                        .eq(ObjectUtil.isNotNull(cellarCommodityDb.getStoreId()),CellarCommodityDbEntity::getStoreId,cellarCommodityDb.getStoreId())
                        .eq(ObjectUtil.isNotNull(cellarCommodityDb.getCategoryId()),CellarCommodityDbEntity::getCategoryId,cellarCommodityDb.getCategoryId())
                        .eq(ObjectUtil.isNotNull(cellarCommodityDb.getHaveHandpick()),CellarCommodityDbEntity::getHaveHandpick,cellarCommodityDb.getHaveHandpick())
                        .like(StringUtils.isNotBlank(cellarCommodityDb.getKey()),CellarCommodityDbEntity::getCommodityName,cellarCommodityDb.getKey())
                        .eq(ObjectUtil.isNotNull(cellarCommodityDb.getCategoryActivityId()),CellarCommodityDbEntity::getCategoryActivityId,cellarCommodityDb.getCategoryActivityId())
                        .eq(ObjectUtil.isNotNull(cellarCommodityDb.getCategoryActivityId()),CellarCommodityDbEntity::getHaveCategoryActivity, Constants.HAVECATEGORYACTIVITY.YES.getKey())
                        .orderByDesc(cellarCommodityDb.getSort().equals(Constants.COMMODITYSORT.ONE.getKey()),CellarCommodityDbEntity::getTotalSales)
                        .orderByDesc(cellarCommodityDb.getSort().equals(Constants.COMMODITYSORT.TWO.getKey()),CellarCommodityDbEntity::getCreationTime)
                        .orderByAsc(cellarCommodityDb.getSort().equals(Constants.COMMODITYSORT.THREE.getKey()),CellarCommodityDbEntity::getPresentPrice)
                        .orderByDesc(cellarCommodityDb.getSort().equals(Constants.COMMODITYSORT.FOUR.getKey()),CellarCommodityDbEntity::getPresentPrice)
                        .orderByDesc(cellarCommodityDb.getSort().equals(Constants.COMMODITYSORT.FIVE.getKey()),CellarCommodityDbEntity::getHighPraise)
        );
        return new PageUtils(page);
    }

    @Override
    public void evaluationStarNumbers(Long commodityId, Integer evaluationStarNumber) {
        CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(commodityId);

        Assert.isNull(cellarCommodityDbEntity,"商品不能为空");

        /**
         * 判断是否为空
         * 为空说明第一次评价直接设置
         */
        if (ObjectUtil.isNull(cellarCommodityDbEntity.getEvaluationStarNumbers())) {
            cellarCommodityDbEntity.setEvaluationStarNumbers(BigDecimal.valueOf(evaluationStarNumber));
            cellarCommodityDbEntity.setEvaluationNumbers(BigDecimal.ONE);
        }else {
            /**
             * 如果不为空计算平均数
             * 获取之前平均数,评价数量 在计算平均数
             */
            BigDecimal evaluationStarNumbers = cellarCommodityDbEntity.getEvaluationStarNumbers();//平均数
            BigDecimal evaluationNumbers = cellarCommodityDbEntity.getEvaluationNumbers();//评价数量
            BigDecimal divide = evaluationStarNumbers.multiply(evaluationNumbers).add(BigDecimal.valueOf(evaluationStarNumber)).divide(evaluationNumbers.add(BigDecimal.ONE));
            cellarCommodityDbEntity.setEvaluationStarNumbers(divide);
            cellarCommodityDbEntity.setEvaluationNumbers(evaluationNumbers.add(BigDecimal.ONE));
        }
        /**
         * 修改平均数
         */
        cellarCommodityDbService.updateById(cellarCommodityDbEntity);
    }

}
