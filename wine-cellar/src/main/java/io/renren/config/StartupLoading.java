package io.renren.config;

import io.renren.common.utils.AreaConfig;
import io.renren.modules.sys.entity.SysAreaEntity;
import io.renren.modules.sys.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;
import java.util.logging.Logger;

/**
 * 启动加载类
 * 启动完成之后将三级联动数据加载进内存
 */
@Component
public class StartupLoading implements CommandLineRunner {

    @Autowired
    private AreaConfig areaConfig;

    @Autowired
    private SysAreaService sysAreaService;

    @Override
    public void run(String... args) throws Exception {

        if (areaConfig.SYS_AREA_ENTITIES == null) {
            areaConfig.SYS_AREA_ENTITIES = sysAreaService.listThreeLevel();
        }

    }
}
