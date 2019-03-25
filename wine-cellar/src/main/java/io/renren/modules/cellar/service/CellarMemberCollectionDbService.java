package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberCollectionDbEntity;

import java.util.Map;

/**
 * 会员收藏表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-23 13:51:41
 */
public interface CellarMemberCollectionDbService extends IService<CellarMemberCollectionDbEntity> {

    PageUtils queryPage(CellarMemberCollectionDbEntity cellarMemberCollectionDb);
}

