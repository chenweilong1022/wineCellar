package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarKillActivityDbEntity;

import java.util.Map;

/**
 * 秒杀活动表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-29 10:13:13
 */
public interface CellarKillActivityDbService extends IService<CellarKillActivityDbEntity> {

    PageUtils queryPage(CellarKillActivityDbEntity cellarKillActivityDb);
}

