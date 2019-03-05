package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarOrderDbEntity;

import java.util.Map;

/**
 * 酒窖订单表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-01 13:16:42
 */
public interface CellarOrderDbService extends IService<CellarOrderDbEntity> {

    PageUtils queryPage(CellarOrderDbEntity cellarOrderDb);

    /**
     * 购物车支付成功
     */
    void paySuccessByCart(String outtradeno);


}

