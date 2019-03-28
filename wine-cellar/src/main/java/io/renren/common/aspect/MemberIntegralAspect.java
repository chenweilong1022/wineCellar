package io.renren.common.aspect;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.modules.cellar.entity.*;
import io.renren.modules.cellar.service.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 会员积分切面类
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-18 09:47:37
 */
@Aspect
@Component
public class MemberIntegralAspect {

	@Autowired
	private CellarOrderDbService cellarOrderDbService;
	@Autowired
	private CellarMemberIntegralChangeRecordDbService cellarMemberIntegralChangeRecordDbService;
	@Autowired
	private CellarMemberDbService cellarMemberDbService;

	@Pointcut("@annotation(io.renren.common.annotation.MemberIntegral)")
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
		saveMemberIntegral(point,time);

		return result;
	}

	private void saveMemberIntegral(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		String methodName = signature.getName();//方法名称

		//请求的参数
		Object[] args = joinPoint.getArgs();

		/**
		 * 如果是支付成功方法
		 */
		if (methodName.equals("paySuccess")) {
			/**
			 * 获取第一个方法参数
			 */
			String outtradeno = (String) args[0];
			/**
			 * 根据支付号查询订单列表
			 */
			List<CellarOrderDbEntity> cellarOrderDbEntities = cellarOrderDbService.list(new QueryWrapper<CellarOrderDbEntity>().lambda()
					.eq(CellarOrderDbEntity::getOrderNo, outtradeno)
			);
			/**
			 * 判断
			 */
			if (ObjectUtil.isNull(cellarOrderDbEntities) && cellarOrderDbEntities.size() == 0) {
				return;
			}
			/**
			 * 循环
			 */
			for (CellarOrderDbEntity cellarOrderDbEntity : cellarOrderDbEntities) {
				/**
				 * 查询是否已经结算过积分 如果结算过直接返回
				 */
				int count = cellarMemberIntegralChangeRecordDbService.count(new QueryWrapper<CellarMemberIntegralChangeRecordDbEntity>().lambda()
						.eq(CellarMemberIntegralChangeRecordDbEntity::getOrderId, cellarOrderDbEntity.getOrderId())
						.eq(CellarMemberIntegralChangeRecordDbEntity::getState, Constants.STATE.zero.getKey())
				);
				if (count > Constants.Number.zero.getKey()) {
					return;
				}
				/**
				 * 查询该订单用户信息
				 */
				CellarMemberDbEntity cellarMemberDbEntity = cellarMemberDbService.getById(cellarOrderDbEntity.getMemberId());
				/**
				 * 如果积分大于0增加用户积分
				 * 增加用户积分记录
				 */
				if (cellarOrderDbEntity.getIntegral().compareTo(BigDecimal.ZERO) > Constants.Number.zero.getKey()) {
					CellarMemberIntegralChangeRecordDbEntity cellarMemberIntegralChangeRecordDbEntity = new CellarMemberIntegralChangeRecordDbEntity();
					cellarMemberIntegralChangeRecordDbEntity.setCreateTime(new Date());
					cellarMemberIntegralChangeRecordDbEntity.setState(Constants.STATE.zero.getKey());
					cellarMemberIntegralChangeRecordDbEntity.setOrderId(Long.valueOf(cellarOrderDbEntity.getOrderId()));
					cellarMemberIntegralChangeRecordDbEntity.setOrderNo(cellarOrderDbEntity.getOrderNo());
					cellarMemberIntegralChangeRecordDbEntity.setChangeType(Constants.CHANGETYPE.TWO.getKey());
					cellarMemberIntegralChangeRecordDbEntity.setChangeDesc(Constants.CHANGETYPE.TWO.getValue());
					cellarMemberIntegralChangeRecordDbEntity.setMemberId(cellarOrderDbEntity.getMemberId());
					cellarMemberIntegralChangeRecordDbEntity.setChangeIntegral(cellarOrderDbEntity.getIntegral());
					cellarMemberIntegralChangeRecordDbEntity.setBeforeChange(cellarMemberDbEntity.getIntegral());
					cellarMemberIntegralChangeRecordDbEntity.setAfterIntegral(cellarMemberDbEntity.getIntegral().add(cellarOrderDbEntity.getIntegral()));
					cellarMemberIntegralChangeRecordDbService.save(cellarMemberIntegralChangeRecordDbEntity);
					/**
					 * 用户积分增加
					 */
					cellarMemberDbEntity.setIntegral(cellarMemberIntegralChangeRecordDbEntity.getAfterIntegral());
					cellarMemberDbService.updateById(cellarMemberDbEntity);
				}
			}
		}










	}

}