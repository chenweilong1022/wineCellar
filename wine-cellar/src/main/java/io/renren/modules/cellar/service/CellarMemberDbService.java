package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;

import java.util.Map;

/**
 * 酒窖会员表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-01-16 17:34:20
 */
public interface CellarMemberDbService extends IService<CellarMemberDbEntity> {

    PageUtils queryPage(CellarMemberDbEntity cellarMemberDbEntity);

    /**
     * 登录校验
     * @param cellarMemberDbEntity
     * @return
     */
    CellarMemberDbEntity login(CellarMemberDbEntity cellarMemberDbEntity);

    /**
     * 余额充值成功
     * @param outtradeno
     */
    void rechargeBalanceSuccess(String outtradeno);
    /**
     * 储值卡余额充值成功
     * @param outtradeno
     */
    void rechargeCardBalanceSuccess(String outtradeno);
}

