package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberAddressDbEntity;

import java.util.Map;

/**
 * 会员地址表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-26 10:56:54
 */
public interface CellarMemberAddressDbService extends IService<CellarMemberAddressDbEntity> {

    PageUtils queryPage(CellarMemberAddressDbEntity cellarMemberAddressDb);
}

