package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberBargainingRecordDbEntity;

import java.util.Map;

/**
 * 会员砍价记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-03 16:45:35
 */
public interface CellarMemberBargainingRecordDbService extends IService<CellarMemberBargainingRecordDbEntity> {

    PageUtils queryPage(CellarMemberBargainingRecordDbEntity cellarMemberBargainingRecordDb);
}

