package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberVideoDbEntity;

import java.util.Map;

/**
 * 会员交友视频表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 10:54:07
 */
public interface CellarMemberVideoDbService extends IService<CellarMemberVideoDbEntity> {

    PageUtils queryPage(CellarMemberVideoDbEntity cellarMemberVideoDb);
}

