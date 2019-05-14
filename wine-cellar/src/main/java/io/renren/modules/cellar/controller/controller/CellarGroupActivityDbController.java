package io.renren.modules.cellar.controller.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.cellar.entity.CellarGroupActivityDbEntity;
import io.renren.modules.cellar.service.CellarGroupActivityDbService;
import io.renren.modules.job.entity.ScheduleJobEntity;
import io.renren.modules.job.service.ScheduleJobService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 拼团活动表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-04-01 16:22:22
 */
@RestController
@RequestMapping("cellar/cellargroupactivitydb")
public class CellarGroupActivityDbController extends AbstractController {
    @Autowired
    private CellarGroupActivityDbService cellarGroupActivityDbService;
    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cellar:cellargroupactivitydb:list")
    public R list(CellarGroupActivityDbEntity cellarGroupActivityDb){
        SysUserEntity user = this.getUser();
        cellarGroupActivityDb.setStoreId(user.getStoreId());
        PageUtils page = cellarGroupActivityDbService.queryPage(cellarGroupActivityDb);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{groupActivityId}")
    @RequiresPermissions("cellar:cellargroupactivitydb:info")
    public R info(@PathVariable("groupActivityId") Long groupActivityId){
			CellarGroupActivityDbEntity cellarGroupActivityDb = cellarGroupActivityDbService.getById(groupActivityId);

        return R.ok().put("cellarGroupActivityDb", cellarGroupActivityDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cellar:cellargroupactivitydb:save")
    public R save(@RequestBody CellarGroupActivityDbEntity cellarGroupActivityDb){
        cellarGroupActivityDb.setCreateTime(new Date());
        cellarGroupActivityDb.setState(Constants.STATE.zero.getKey());
        cellarGroupActivityDb.setHasGroupNumber(BigDecimal.ZERO);
        cellarGroupActivityDb.setStayGroupNumber(cellarGroupActivityDb.getGroupTotalNumber());
        cellarGroupActivityDbService.save(cellarGroupActivityDb);

        ScheduleJobEntity scheduleJob = new ScheduleJobEntity();
        scheduleJob.setBeanName("groupActivityTask");
        scheduleJob.setMethodName("groupActivity");
        scheduleJob.setParams(cellarGroupActivityDb.getGroupActivityId().toString());
        scheduleJob.setRemark("拼团定时任务,用来检测拼团是否成功");
        scheduleJob.setCronExpression(DateUtil.format(cellarGroupActivityDb.getGroupEndTime(),"ss mm HH dd MM ?"));
        scheduleJobService.save(scheduleJob);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cellar:cellargroupactivitydb:update")
    public R update(@RequestBody CellarGroupActivityDbEntity cellarGroupActivityDb){
        cellarGroupActivityDbService.updateById(cellarGroupActivityDb);

        ScheduleJobEntity scheduleJob = new ScheduleJobEntity();
        scheduleJob.setBeanName("groupActivityTask");
        scheduleJob.setMethodName("groupActivity");
        scheduleJob.setParams(cellarGroupActivityDb.getGroupActivityId().toString());
        scheduleJob.setRemark("拼团定时任务,用来检测拼团是否成功");
        ScheduleJobEntity scheduleJobServiceOne = scheduleJobService.getOne(new QueryWrapper<ScheduleJobEntity>().setEntity(scheduleJob)
        );
        scheduleJobServiceOne.setCronExpression(DateUtil.format(cellarGroupActivityDb.getGroupEndTime(),"ss mm HH dd MM ?"));
        scheduleJobService.update(scheduleJobServiceOne);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cellar:cellargroupactivitydb:delete")
    public R delete(@RequestBody Long[] groupActivityIds){
		cellarGroupActivityDbService.removeByIds(Arrays.asList(groupActivityIds));
		List<Long> longlist = new ArrayList<>();
        for (Long groupActivityId : groupActivityIds) {
            ScheduleJobEntity scheduleJob = new ScheduleJobEntity();
            scheduleJob.setBeanName("groupActivityTask");
            scheduleJob.setMethodName("groupActivity");
            scheduleJob.setParams(String.valueOf(groupActivityId));
            scheduleJob.setRemark("拼团定时任务,用来检测拼团是否成功");
            ScheduleJobEntity scheduleJobServiceOne = scheduleJobService.getOne(new QueryWrapper<ScheduleJobEntity>().setEntity(scheduleJob));
            longlist.add(scheduleJobServiceOne.getJobId());
        }
        scheduleJobService.deleteBatch(ArrayUtil.toArray(longlist,Long.class));
        return R.ok();
    }

}
