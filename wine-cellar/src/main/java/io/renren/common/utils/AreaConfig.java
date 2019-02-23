package io.renren.common.utils;

import io.renren.modules.sys.entity.SysAreaEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 三级联动数据类
 * 启动时
 * 将数据加载到内存中
 */
@Component
public class AreaConfig {

    public static List<SysAreaEntity> SYS_AREA_ENTITIES;

    public static List<SysAreaEntity> getSysAreaEntities() {
        return SYS_AREA_ENTITIES;
    }

    public static void setSysAreaEntities(List<SysAreaEntity> sysAreaEntities) {
        SYS_AREA_ENTITIES = sysAreaEntities;
    }
}
