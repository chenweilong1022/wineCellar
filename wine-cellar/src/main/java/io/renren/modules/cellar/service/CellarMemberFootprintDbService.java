package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberFootprintDbEntity;

import java.util.Map;

/**
 * 会员足迹表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-25 17:29:13
 */
public interface CellarMemberFootprintDbService extends IService<CellarMemberFootprintDbEntity> {

    PageUtils queryPage(CellarMemberFootprintDbEntity cellarMemberFootprintDb);
}

