package io.renren;

import cn.hutool.core.annotation.AnnotationUtil;
import io.renren.modules.cellar.entity.*;
import io.swagger.annotations.ApiModelProperty;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class AnoTest {

    public static void main(String[] args) {
        Annotation[] annotations = AnnotationUtil.getAnnotations(ApiModelProperty.class, true);

        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        Field[] declaredFields = CellarMemberVideoDbEntity.class.getDeclaredFields();
        for (Field field : declaredFields) {
            ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
            if (annotation == null)
                continue;
            String value = annotation.value();
            System.out.println(value);
            String name = field.getName();
//            System.out.println(name);

        }
    }

}
