package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarFeedbackDbEntity;

import java.util.Map;

/**
 * 意见反馈表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-26 17:15:05
 */
public interface CellarFeedbackDbService extends IService<CellarFeedbackDbEntity> {

    PageUtils queryPage(CellarFeedbackDbEntity cellarFeedbackDb);
}

