package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarFranchiseDbEntity;

import java.util.Map;

/**
 * 酒窖加盟表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-27 13:25:34
 */
public interface CellarFranchiseDbService extends IService<CellarFranchiseDbEntity> {

    PageUtils queryPage(CellarFranchiseDbEntity cellarFranchiseDb);
}

