package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberDynamicThumbDbEntity;

import java.util.Map;

/**
 * 会员动态点赞表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-05-14 17:54:33
 */
public interface CellarMemberDynamicThumbDbService extends IService<CellarMemberDynamicThumbDbEntity> {

    PageUtils queryPage(CellarMemberDynamicThumbDbEntity cellarMemberDynamicThumbDb);
}

