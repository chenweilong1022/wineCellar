package io.renren.common.annotation;

import io.renren.common.constants.Constants;

import java.lang.annotation.*;

/**
 * 会员积分注解
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017年3月8日 上午11:07:35
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MemberIntegral {

	Constants.CHANGETYPE CHANGETYPE() default Constants.CHANGETYPE.ONE;
}
