package io.renren.common.aspect;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.annotation.MemberMessage;
import io.renren.common.constants.Constants;
import io.renren.modules.cellar.entity.*;
import io.renren.modules.cellar.service.CellarMemberDbService;
import io.renren.modules.cellar.service.CellarMemberFootprintDbService;
import io.renren.modules.cellar.service.CellarMemberMessageDbService;
import io.renren.modules.cellar.service.CellarOrderDbService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 会员足迹切面类
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-18 09:47:37
 */
@Aspect
@Component
public class MemberFootprintAspect {
	@Autowired
	private CellarMemberMessageDbService cellarMemberMessageDbService;
	@Autowired
	private CellarOrderDbService cellarOrderDbService;
	@Autowired
	private CellarMemberDbService cellarMemberDbService;
	@Autowired
	private CellarMemberFootprintDbService cellarMemberFootprintDbService;
	
	@Pointcut("@annotation(io.renren.common.annotation.MemberFootprint)")
	public void logPointCut() {
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		/**
		 * 保存会员会员足迹
		 */
		saveMemberFootprint(point,time);

		return result;
	}

	private void saveMemberFootprint(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//		Method method = signature.getMethod();
//		String methodName = signature.getName();//方法名称
		CellarMemberFootprintDbEntity cellarMemberFootprintDbEntity = new CellarMemberFootprintDbEntity();


		//请求的参数
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			/**
			 * 如果是会员
			 */
			if (arg instanceof CellarMemberDbEntity) {
				CellarMemberDbEntity cellarMemberDbEntity = (CellarMemberDbEntity) arg;
				if (ObjectUtil.isNull(cellarMemberDbEntity)) {
					return;
				}
				cellarMemberFootprintDbEntity.setMemberId(cellarMemberDbEntity.getMemberId());
				/**
				 * 如果是商品
				 */
			}else if (arg instanceof CellarCommodityDbEntity) {
				CellarCommodityDbEntity cellarCommodityDbEntity = (CellarCommodityDbEntity) arg;
				if (ObjectUtil.isNull(cellarCommodityDbEntity)) {
					return;
				}
				cellarMemberFootprintDbEntity.setCommodityId(cellarCommodityDbEntity.getCommodityId());
			}else if (ObjectUtil.isNull(arg)) {
				return;
			}
		}

		cellarMemberFootprintDbEntity.setCreateTime(new Date());
		cellarMemberFootprintDbEntity.setState(Constants.STATE.zero.getKey());
		cellarMemberFootprintDbEntity.setLastTime(new Date());

		CellarMemberFootprintDbEntity memberFootprintDbEntityRet = cellarMemberFootprintDbService.getOne(new QueryWrapper<CellarMemberFootprintDbEntity>().lambda()
				.eq(CellarMemberFootprintDbEntity::getCommodityId, cellarMemberFootprintDbEntity.getCommodityId())
				.eq(CellarMemberFootprintDbEntity::getState, Constants.STATE.zero.getKey())
				.eq(CellarMemberFootprintDbEntity::getMemberId, cellarMemberFootprintDbEntity.getMemberId())
		);
		if (ObjectUtil.isNotNull(memberFootprintDbEntityRet)) {
			memberFootprintDbEntityRet.setLastTime(new Date());
			memberFootprintDbEntityRet.setVisits(memberFootprintDbEntityRet.getVisits() + Constants.Number.one.getKey());
			cellarMemberFootprintDbService.updateById(memberFootprintDbEntityRet);
			return;
		}
		cellarMemberFootprintDbEntity.setVisits(Constants.Number.one.getKey());
		cellarMemberFootprintDbService.save(cellarMemberFootprintDbEntity);
	}

}