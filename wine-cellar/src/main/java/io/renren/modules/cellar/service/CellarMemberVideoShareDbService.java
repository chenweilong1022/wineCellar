package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberVideoShareDbEntity;

import java.util.Map;

/**
 * 会员交友视频分享记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 15:10:37
 */
public interface CellarMemberVideoShareDbService extends IService<CellarMemberVideoShareDbEntity> {

    PageUtils queryPage(CellarMemberVideoShareDbEntity cellarMemberVideoShareDb);
}

