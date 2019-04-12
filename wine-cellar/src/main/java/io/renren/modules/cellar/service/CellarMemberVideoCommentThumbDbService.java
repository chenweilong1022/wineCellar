package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberVideoCommentThumbDbEntity;

import java.util.Map;

/**
 * 会员交友视频评论点赞记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 16:09:08
 */
public interface CellarMemberVideoCommentThumbDbService extends IService<CellarMemberVideoCommentThumbDbEntity> {

    PageUtils queryPage(CellarMemberVideoCommentThumbDbEntity cellarMemberVideoCommentThumbDb);
}

