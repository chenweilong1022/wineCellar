package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberMessageDbEntity;

import java.util.Map;

/**
 * 会员消息
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-19 11:07:06
 */
public interface CellarMemberMessageDbService extends IService<CellarMemberMessageDbEntity> {

    PageUtils queryPage(CellarMemberMessageDbEntity cellarMemberMessageDb);
}

