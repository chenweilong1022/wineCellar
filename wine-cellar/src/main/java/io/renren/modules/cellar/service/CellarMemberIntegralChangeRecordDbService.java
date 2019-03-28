package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberIntegralChangeRecordDbEntity;

import java.util.Map;

/**
 * 会员积分变动记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-26 13:19:43
 */
public interface CellarMemberIntegralChangeRecordDbService extends IService<CellarMemberIntegralChangeRecordDbEntity> {

    PageUtils queryPage(CellarMemberIntegralChangeRecordDbEntity cellarMemberIntegralChangeRecordDb);
}

