package io.renren.modules.cellar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarMemberCaptchaEntity;

import java.util.Map;

/**
 * 验证码表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-01-29 14:59:19
 */
public interface CellarMemberCaptchaService extends IService<CellarMemberCaptchaEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void checkCode(String uuid, String phone, String code);
}

