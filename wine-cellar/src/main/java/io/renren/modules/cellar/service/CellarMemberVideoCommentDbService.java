package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberVideoCommentDbEntity;

import java.util.Map;

/**
 * 会员交友视频评论记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-10 14:11:13
 */
public interface CellarMemberVideoCommentDbService extends IService<CellarMemberVideoCommentDbEntity> {

    PageUtils queryPage(CellarMemberVideoCommentDbEntity cellarMemberVideoCommentDb);
}

