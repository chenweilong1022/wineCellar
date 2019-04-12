package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberVideoThumbDbEntity;

import java.util.Map;

/**
 * 会员交友视频点赞记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 13:17:10
 */
public interface CellarMemberVideoThumbDbService extends IService<CellarMemberVideoThumbDbEntity> {

    PageUtils queryPage(CellarMemberVideoThumbDbEntity cellarMemberVideoThumbDb);
}

