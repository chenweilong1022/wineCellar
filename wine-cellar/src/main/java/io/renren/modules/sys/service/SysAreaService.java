package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysAreaEntity;

import java.util.List;

/**
 * 地域信息表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 15:12:55
 */
public interface SysAreaService extends IService<SysAreaEntity> {

    PageUtils queryPage(SysAreaEntity sysArea);

    List<SysAreaEntity> listThreeLevel();
}

