package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberDynamicDbEntity;

import java.util.Map;

/**
 * 会员动态表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-05-13 09:58:25
 */
public interface CellarMemberDynamicDbService extends IService<CellarMemberDynamicDbEntity> {

    PageUtils queryPage(CellarMemberDynamicDbEntity cellarMemberDynamicDb);
}

