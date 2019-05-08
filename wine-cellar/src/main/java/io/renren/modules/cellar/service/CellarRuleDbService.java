package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarRuleDbEntity;

import java.util.Map;

/**
 * 酒窖规则表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-30 10:12:45
 */
public interface CellarRuleDbService extends IService<CellarRuleDbEntity> {

    PageUtils queryPage(CellarRuleDbEntity cellarRuleDb);
}

