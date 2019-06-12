package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cellar.entity.CellarMemberDynamicCommentDbEntity;

import java.util.Map;

/**
 * 会员动态评论表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-05-15 13:18:56
 */
public interface CellarMemberDynamicCommentDbService extends IService<CellarMemberDynamicCommentDbEntity> {

    PageUtils queryPage(CellarMemberDynamicCommentDbEntity cellarMemberDynamicCommentDb);
}

