package io.renren.modules.sys.controller;

import io.renren.common.constants.Constants;
import io.renren.common.utils.AreaConfig;
import io.renren.common.utils.EnumUtil;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysAreaEntity;
import io.renren.modules.sys.service.SysAreaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 全局配置
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-02-22 15:12:55
 */
@RestController
@RequestMapping("sys/constants")
public class SysConstantsController {


    /**
     * 列表
     */
    @RequestMapping("/statelist")
    public R statelist(){
        List<Map<String, Object>> maps = EnumUtil.enumToMaps(Constants.STATE.class);
        return R.data(maps);
    }

    /**
     * 订单类型
     */
    @RequestMapping("/carttypelist")
    public R carttypelist(){
        List<Map<String, Object>> maps = EnumUtil.enumToMaps(Constants.CARTTYPE.class);
        return R.data(maps);
    }

    /**
     * 支付方式
     */
    @RequestMapping("/methodpaymentlist")
    public R methodpaymentlist(){
        List<Map<String, Object>> maps = EnumUtil.enumToMaps(Constants.METHODPAYMENT.class);
        return R.data(maps);
    }

    /**
     * 订单状态
     */
    @RequestMapping("/orderstatuslist")
    public R orderstatuslist(){
        List<Map<String, Object>> maps = EnumUtil.enumToMaps(Constants.ORDERSTATUS.class);
        return R.data(maps);
    }

    /**
     * 优惠券类型
     */
    @RequestMapping("/coupontypelist")
    public R coupontypelist(){
        List<Map<String, Object>> maps = EnumUtil.enumToMaps(Constants.COUPONTYPE.class);
        return R.data(maps);
    }

    /**
     * 轮播图类型 点击跳转的路径
     */
    @RequestMapping("/shufflingtypelist")
    public R shufflingtypelist(){
        List<Map<String, Object>> maps = EnumUtil.enumToMaps(Constants.SHUFFLINGTYPE.class);
        return R.data(maps);
    }

    /**
     * 酒窖规则
     */
    @RequestMapping("/ruletypelist")
    public R ruletypelist(){
        List<Map<String, Object>> maps = EnumUtil.enumToMaps(Constants.RULETYPE.class);
        return R.data(maps);
    }

}
