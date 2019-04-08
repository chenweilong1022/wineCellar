package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberBargainingInformationDbEntity;

import java.util.Map;

/**
 * 会员砍价信息表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-03 15:22:05
 */
public interface CellarMemberBargainingInformationDbService extends IService<CellarMemberBargainingInformationDbEntity> {

    PageUtils queryPage(CellarMemberBargainingInformationDbEntity cellarMemberBargainingInformationDb);
}

