package io.renren.modules.cellar.service.impl;

import cn.hutool.core.util.ObjectUtil;
import io.renren.common.annotation.MemberIntegral;
import io.renren.common.annotation.MemberMessage;
import io.renren.common.constants.Constants;
import io.renren.common.utils.ShiroUtils;
import io.renren.common.utils.pay.AliUtil;
import io.renren.common.validator.Assert;
import io.renren.modules.cellar.entity.*;
import io.renren.modules.cellar.service.*;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cellar.dao.CellarOrderDbDao;
import org.springframework.transaction.annotation.Transactional;


@Service("cellarOrderDbService")
public class CellarOrderDbServiceImpl extends ServiceImpl<CellarOrderDbDao, CellarOrderDbEntity> implements CellarOrderDbService {

    @Autowired
    private CellarOrderDetailsDbService cellarOrderDetailsDbService;
    @Autowired
    private CellarCommodityDbService cellarCommodityDbService;
    @Autowired
    private CellarMemberCouponDbService cellarMemberCouponDbService;
    @Autowired
    private CellarCartDbService cellarCartDbService;
    @Autowired
    private CellarKillActivityDbService cellarKillActivityDbService;
    @Autowired
    private CellarGroupActivityDbService cellarGroupActivityDbService;

    @Override
    public PageUtils queryPage(CellarOrderDbEntity cellarOrderDb) {
        /**
         *
         */
        SysUserEntity userEntity = ShiroUtils.getUserEntity();
        boolean flag = userEntity == null?false:ObjectUtil.isNotNull(userEntity.getStoreId())?true:false;
        Long storeId = userEntity == null?null:ObjectUtil.isNotNull(userEntity.getStoreId())?userEntity.getStoreId():null;
        /**
         * 分页查询订单
         */
        IPage<CellarOrderDbEntity> page = baseMapper.selectPage(
                new Query<CellarOrderDbEntity>(cellarOrderDb).getPage(),
                new QueryWrapper<CellarOrderDbEntity>().lambda()
                .notIn(CellarOrderDbEntity::getOrderStatus, Constants.ORDERSTATUS.FUONE.getKey())
                .in(ObjectUtil.isNotNull(cellarOrderDb.getOrderType()),CellarOrderDbEntity::getOrderType,cellarOrderDb.getOrderType())
                .eq(ObjectUtil.isNotNull(cellarOrderDb.getMemberId()),CellarOrderDbEntity::getMemberId,cellarOrderDb.getMemberId())
                .eq(ObjectUtil.isNotNull(cellarOrderDb.getMethodPayment()),CellarOrderDbEntity::getMethodPayment,cellarOrderDb.getMethodPayment())
                .eq(ObjectUtil.isNotNull(cellarOrderDb.getOrderType()),CellarOrderDbEntity::getOrderType,cellarOrderDb.getOrderType())
                .eq(ObjectUtil.isNotNull(cellarOrderDb.getOrderStatus()),CellarOrderDbEntity::getOrderStatus,cellarOrderDb.getOrderStatus())
                .eq(flag,CellarOrderDbEntity::getStoreId,storeId)
                .like(StringUtils.isNotBlank(cellarOrderDb.getKey()),CellarOrderDbEntity::getOrderId,cellarOrderDb.getKey())
                .orderByDesc(CellarOrderDbEntity::getCreateTime)
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional
    @MemberMessage(MESSAGEHEAD = "支付成功",MESSAGETYPE = Constants.MESSAGETYPE.ORDER,MESSAGECONTENT = "您的订单支付成功,等待平台发货")
    @MemberIntegral(CHANGETYPE = Constants.CHANGETYPE.TWO)//用户积分注解拦截
    public void paySuccess(String outtradeno) {
        List<CellarOrderDbEntity> cellarOrderDbEntities = isPay(outtradeno);
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
             * 获取订单优惠券
             */
            Long memberCouponId = cellarOrderDbEntity.getMemberCouponId();
            CellarMemberCouponDbEntity cellarMemberCouponDbEntity = cellarMemberCouponDbService.getById(memberCouponId);
            /**
             * 使用了
             * 优惠券
             */
            if (ObjectUtil.isNotNull(memberCouponId) && ObjectUtil.isNotNull(cellarMemberCouponDbEntity)) {
                cellarMemberCouponDbEntity.setUseTime(new Date());
                cellarMemberCouponDbEntity.setUsingState(Constants.USINGSTATE.TWO.getKey());
                cellarMemberCouponDbService.updateById(cellarMemberCouponDbEntity);
            }
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
                 * 如果是秒杀活动去修改秒杀表库存
                 */
                if (cellarOrderDbEntity.getOrderType().equals(Constants.CARTTYPE.FIVE.getKey())) {
                    /**
                     * 修改秒杀活动库存
                     */
                    CellarKillActivityDbEntity cellarKillActivityDbEntity = cellarKillActivityDbService.getById(cellarOrderDetailsDbEntity.getKillActivityId());
                    cellarKillActivityDbEntity.setKillNumber(cellarKillActivityDbEntity.getKillNumber().subtract(cellarOrderDetailsDbEntity.getNumber()));
                    cellarKillActivityDbService.updateById(cellarKillActivityDbEntity);

                    CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(cellarOrderDetailsDbEntity.getCommodityId());
                    cellarCommodityDbEntity.setMonthSales(cellarCommodityDbEntity.getMonthSales().add(cellarOrderDetailsDbEntity.getNumber()));
                    cellarCommodityDbEntity.setTotalSales(cellarCommodityDbEntity.getTotalSales().add(cellarOrderDetailsDbEntity.getNumber()));
                    cellarCommodityDbService.updateById(cellarCommodityDbEntity);
                    continue;
                }else if (cellarOrderDbEntity.getOrderType().equals(Constants.CARTTYPE.FOUR.getKey())) {
                    /**
                     * 修改拼团活动库存
                     */
                    CellarGroupActivityDbEntity cellarGroupActivityDbEntity = cellarGroupActivityDbService.getById(cellarOrderDetailsDbEntity.getGroupActivityId());
                    cellarGroupActivityDbEntity.setHasGroupNumber(cellarGroupActivityDbEntity.getHasGroupNumber().add(cellarOrderDetailsDbEntity.getNumber()));
                    cellarGroupActivityDbEntity.setStayGroupNumber(cellarGroupActivityDbEntity.getGroupTotalNumber().subtract(cellarGroupActivityDbEntity.getHasGroupNumber()));
                    cellarGroupActivityDbService.updateById(cellarGroupActivityDbEntity);

                    CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(cellarOrderDetailsDbEntity.getCommodityId());
                    cellarCommodityDbEntity.setMonthSales(cellarCommodityDbEntity.getMonthSales().add(cellarOrderDetailsDbEntity.getNumber()));
                    cellarCommodityDbEntity.setTotalSales(cellarCommodityDbEntity.getTotalSales().add(cellarOrderDetailsDbEntity.getNumber()));
                    cellarCommodityDbService.updateById(cellarCommodityDbEntity);
                    continue;
                }
                /**
                 * 修改库存
                 */
                CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(cellarOrderDetailsDbEntity.getCommodityId());
                cellarCommodityDbEntity.setMonthSales(cellarCommodityDbEntity.getMonthSales().add(cellarOrderDetailsDbEntity.getNumber()));
                cellarCommodityDbEntity.setTotalSales(cellarCommodityDbEntity.getTotalSales().add(cellarOrderDetailsDbEntity.getNumber()));
                cellarCommodityDbEntity.setInventory(cellarCommodityDbEntity.getInventory().subtract(cellarOrderDetailsDbEntity.getNumber()));
                cellarCommodityDbService.updateById(cellarCommodityDbEntity);
            }
        }
        /**
         * 删除购物车
         */
        CellarCartDbEntity cellarCartDbEntity = new CellarCartDbEntity();
        cellarCartDbEntity.setState(Constants.STATE.funine.getKey());
        cellarCartDbService.update(cellarCartDbEntity,new QueryWrapper<CellarCartDbEntity>().lambda()
                .eq(CellarCartDbEntity::getOrderNo,outtradeno)
        );
    }

    @Override
    @Transactional
    public void refund(String outtradeno) {
        List<CellarOrderDbEntity> cellarOrderDbEntities = isRefund(outtradeno);
        /**
         * 循环
         */
        for (CellarOrderDbEntity cellarOrderDbEntity : cellarOrderDbEntities) {
            /**
             * 判断是否退款
             */
            if (cellarOrderDbEntity.getOrderStatus().equals(Constants.ORDERSTATUS.FUNINE.getKey())) {
                return;
            }
            /**
             * 修改退款状态时间 款状态
             */
            cellarOrderDbEntity.setOrderStatus(Constants.ORDERSTATUS.FUNINE.getKey());
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
                 * 如果是秒杀活动去修改秒杀表库存
                 */
                if (cellarOrderDbEntity.getOrderType().equals(Constants.CARTTYPE.FIVE.getKey())) {
                    /**
                     * 修改秒杀活动库存
                     */
                    CellarKillActivityDbEntity cellarKillActivityDbEntity = cellarKillActivityDbService.getById(cellarOrderDetailsDbEntity.getKillActivityId());
                    cellarKillActivityDbEntity.setKillNumber(cellarKillActivityDbEntity.getKillNumber().subtract(cellarOrderDetailsDbEntity.getNumber()));
                    cellarKillActivityDbService.updateById(cellarKillActivityDbEntity);

                    CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(cellarOrderDetailsDbEntity.getCommodityId());
                    cellarCommodityDbEntity.setMonthSales(cellarCommodityDbEntity.getMonthSales().add(cellarOrderDetailsDbEntity.getNumber()));
                    cellarCommodityDbEntity.setTotalSales(cellarCommodityDbEntity.getTotalSales().add(cellarOrderDetailsDbEntity.getNumber()));
                    cellarCommodityDbService.updateById(cellarCommodityDbEntity);
                    continue;
                }else if (cellarOrderDbEntity.getOrderType().equals(Constants.CARTTYPE.FOUR.getKey())) {
                    CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(cellarOrderDetailsDbEntity.getCommodityId());
                    cellarCommodityDbEntity.setMonthSales(cellarCommodityDbEntity.getMonthSales().subtract(cellarOrderDetailsDbEntity.getNumber()));
                    cellarCommodityDbEntity.setTotalSales(cellarCommodityDbEntity.getTotalSales().subtract(cellarOrderDetailsDbEntity.getNumber()));
                    cellarCommodityDbService.updateById(cellarCommodityDbEntity);
                    continue;
                }
                /**
                 * 修改库存
                 */
                CellarCommodityDbEntity cellarCommodityDbEntity = cellarCommodityDbService.getById(cellarOrderDetailsDbEntity.getCommodityId());
                cellarCommodityDbEntity.setMonthSales(cellarCommodityDbEntity.getMonthSales().subtract(cellarOrderDetailsDbEntity.getNumber()));
                cellarCommodityDbEntity.setTotalSales(cellarCommodityDbEntity.getTotalSales().subtract(cellarOrderDetailsDbEntity.getNumber()));
                cellarCommodityDbEntity.setInventory(cellarCommodityDbEntity.getInventory().add(cellarOrderDetailsDbEntity.getNumber()));
                cellarCommodityDbService.updateById(cellarCommodityDbEntity);
            }
        }
    }

    /**
     * 判断是否支付
     * @param outtradeno
     * @return
     */
    @Override
    public List<CellarOrderDbEntity> isPay(String outtradeno) {
        /**
         * 根据支付号查询订单列表
         */
        List<CellarOrderDbEntity> cellarOrderDbEntities = baseMapper.selectList(new QueryWrapper<CellarOrderDbEntity>().lambda()
                .eq(CellarOrderDbEntity::getOrderNo, outtradeno)
                .eq(CellarOrderDbEntity::getOrderStatus,Constants.ORDERSTATUS.FUONE.getKey())
        );
        /**
         * 判断
         */
        if (ObjectUtil.isNull(cellarOrderDbEntities) || cellarOrderDbEntities.size() == 0) {
            Assert.isTrue(true,"重复调用");
            return null;
        }
        return cellarOrderDbEntities;
    }

    /**
     * 判断是否支付
     * @param outtradeno
     * @return
     */
    public List<CellarOrderDbEntity> isRefund(String outtradeno) {
        /**
         * 根据支付号查询订单列表
         */
        List<CellarOrderDbEntity> cellarOrderDbEntities = baseMapper.selectList(new QueryWrapper<CellarOrderDbEntity>().lambda()
                .eq(CellarOrderDbEntity::getOrderNo, outtradeno)
                .notIn(CellarOrderDbEntity::getOrderStatus,Constants.ORDERSTATUS.FUNINE.getKey(),Constants.ORDERSTATUS.FUONE.getKey())
        );
        /**
         * 判断
         */
        if (ObjectUtil.isNull(cellarOrderDbEntities) || cellarOrderDbEntities.size() == 0) {
            Assert.isTrue(true,"重复调用");
            return null;
        }
        return cellarOrderDbEntities;
    }

}
