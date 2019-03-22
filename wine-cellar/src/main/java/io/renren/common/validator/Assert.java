package io.renren.common.validator;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import io.renren.common.exception.RRException;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isTrue(boolean flag, String message) {
        if (flag) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }

    public static<T> void isNullArray(T[] object, String message) {
        if (object == null || object.length == 0) {
            throw new RRException(message);
        }
    }

    public static void isPhone(String phone) {
        if (!Validator.isMobile(phone)) {
            throw new RRException("手机格式错误");
        }
    }

}
