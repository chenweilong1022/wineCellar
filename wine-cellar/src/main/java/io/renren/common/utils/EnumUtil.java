package io.renren.common.utils;

import cn.hutool.core.util.ReflectUtil;
import io.renren.common.constants.Constants;

import java.lang.reflect.Field;
import java.util.*;

public class EnumUtil {


    public static <E extends Enum<E>> List<Map<String,Object>> enumToMaps(final Class<E> enumClass) {

        List<Map<String,Object>> list = new ArrayList<>();
        E[] enumConstants = enumClass.getEnumConstants();

        for (E enumConstant : enumConstants) {
            Map<String,Object> map = new HashMap<>();
            String name = enumConstant.name();
            Object key = ReflectUtil.getFieldValue(enumConstant, "key");
            Object value = ReflectUtil.getFieldValue(enumConstant, "value");
            map.put("key",key);
            map.put("value",value);
            list.add(map);
        }
        return list;
    }
}
