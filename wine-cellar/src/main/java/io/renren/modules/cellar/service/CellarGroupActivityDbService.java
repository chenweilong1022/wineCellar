package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarGroupActivityDbEntity;

import java.util.Map;

/**
 * 拼团活动表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-01 16:22:22
 */
public interface CellarGroupActivityDbService extends IService<CellarGroupActivityDbEntity> {

    PageUtils queryPage(CellarGroupActivityDbEntity cellarGroupActivityDb);
}

