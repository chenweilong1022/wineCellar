package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.constants.Constants;
import io.renren.modules.cellar.entity.CellarCommodityDbEntity;
import io.renren.modules.cellar.entity.CellarOrderDetailsDbEntity;
import io.renren.modules.cellar.service.CellarCommodityDbService;
import io.renren.modules.cellar.service.CellarOrderDetailsDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarOrderDbDao;
import io.renren.modules.cellar.entity.CellarOrderDbEntity;
import io.renren.modules.cellar.service.CellarOrderDbService;
import org.springframework.transaction.annotation.Transactional;


@Service("cellarOrderDbService")
public class CellarOrderDbServiceImpl extends ServiceImpl<CellarOrderDbDao, CellarOrderDbEntity> implements CellarOrderDbService {

    @Autowired
    private CellarOrderDetailsDbService cellarOrderDetailsDbService;
    @Autowired
    private CellarCommodityDbService cellarCommodityDbService;

    @Override
    public PageUtils queryPage(CellarOrderDbEntity cellarOrderDb) {
        IPage<CellarOrderDbEntity> page = baseMapper.selectPage(
                new Query<CellarOrderDbEntity>(cellarOrderDb).getPage(),
                new QueryWrapper<CellarOrderDbEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void paySuccessByCart(String outtradeno) {
        /**
         * 根据支付号查询订单列表
         */
        List<CellarOrderDbEntity> cellarOrderDbEntities = baseMapper.selectList(new QueryWrapper<CellarOrderDbEntity>().lambda()
                .eq(CellarOrderDbEntity::getOrderNo, outtradeno)
        );

        /**
         * 判断
         */
        if (ObjectUtil.isNull(cellarOrderDbEntities) && cellarOrderDbEntities.size() == 0) {
            return;
        }

        /**
         * 循环
         */
        for (CellarOrderDbEntity cellarOrderDbEntity : cellarOrderDbEntities) {
            /**
             * 判断是否支付
             */
            if (!cellarOrderDbEntity.getOrderStatus().equals(Constants.ORDERSTATUS.FUONE.getKey())) {
                return;
            }

            /**
             * 修改支付时间 支付状态
             */
            cellarOrderDbEntity.setPaymentTime(new Date());
            cellarOrderDbEntity.setOrderStatus(Constants.ORDERSTATUS.ZERO.getKey());
            baseMapper.updateById(cellarOrderDbEntity);


            /**
             * 查询订单下商品信息
             */
            List<CellarOrderDetailsDbEntity> cellarOrderDetailsDbEntities = cellarOrderDetailsDbService.list(new QueryWrapper<CellarOrderDetailsDbEntity>().lambda()
                    .eq(CellarOrderDetailsDbEntity::getOrderId, cellarOrderDbEntity.getOrderId())

            );

            /**
             * 遍历商品信息
             */
            for (CellarOrderDetailsDbEntity cellarOrderDetailsDbEntity : cellarOrderDetailsDbEntities) {

                /**
                 * 修改库存
                 */
                CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(cellarOrderDetailsDbEntity.getCommodityId());
                cellarCommodityDbEntity.setMonthSales(cellarCommodityDbEntity.getMonthSales().add(cellarOrderDetailsDbEntity.getNumber()));
                cellarCommodityDbEntity.setTotalSales(cellarCommodityDbEntity.getTotalSales().add(cellarOrderDetailsDbEntity.getNumber()));
                cellarCommodityDbEntity.setTotalSales(cellarCommodityDbEntity.getInventory().subtract(cellarOrderDetailsDbEntity.getNumber()));
                cellarCommodityDbService.updateById(cellarCommodityDbEntity);
            }
        }

    }

}
