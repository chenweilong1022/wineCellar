package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberCardDbEntity;

import java.util.Map;

/**
 * 会员储值卡表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-28 13:34:38
 */
public interface CellarMemberCardDbService extends IService<CellarMemberCardDbEntity> {

    PageUtils queryPage(CellarMemberCardDbEntity cellarMemberCardDb);
}

