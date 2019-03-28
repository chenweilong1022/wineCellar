package io.renren.common.aspect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.egzosn.pay.common.bean.PayOutMessage;
import com.google.gson.Gson;
import io.renren.common.annotation.MemberMessage;
import io.renren.common.annotation.SysLog;
import io.renren.common.constants.Constants;
import io.renren.common.utils.HttpContextUtils;
import io.renren.common.utils.IPUtils;
import io.renren.common.utils.pay.AliUtil;
import io.renren.common.utils.pay.CallbackUtil;
import io.renren.common.utils.pay.WechatPayUtil;
import io.renren.common.validator.Assert;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.entity.CellarMemberMessageDbEntity;
import io.renren.modules.cellar.entity.CellarOrderDbEntity;
import io.renren.modules.cellar.service.CellarMemberDbService;
import io.renren.modules.cellar.service.CellarMemberMessageDbService;
import io.renren.modules.cellar.service.CellarOrderDbService;
import io.renren.modules.sys.entity.SysLogEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysLogService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
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
 * 会员消息切面类
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-03-18 09:47:37
 */
@Aspect
@Component
public class MemberMessageAspect {
	@Autowired
	private CellarMemberMessageDbService cellarMemberMessageDbService;
	@Autowired
	private CellarOrderDbService cellarOrderDbService;
	@Autowired
	private CellarMemberDbService cellarMemberDbService;
	
	@Pointcut("@annotation(io.renren.common.annotation.MemberMessage)")
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
		 * 保存会员消息
		 */
		saveMemberMessage(point,time);

		return result;
	}

	private void saveMemberMessage(ProceedingJoinPoint joinPoint, long time) {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		String methodName = signature.getName();//方法名称
		CellarMemberMessageDbEntity messageDbEntity = new CellarMemberMessageDbEntity();


		/**
		 * 获取方法上的注解
		 */
		MemberMessage annotation = method.getAnnotation(MemberMessage.class);
		if(annotation != null){
			//注解上的描述
			messageDbEntity.setMessageHead(annotation.MESSAGEHEAD());//设置消息标题
			messageDbEntity.setMessageType(annotation.MESSAGETYPE().getKey());//设置消息类型
			messageDbEntity.setMessageContent(annotation.MESSAGECONTENT());//设置消息内容
		}

		messageDbEntity.setState(Constants.STATE.zero.getKey());
		messageDbEntity.setCreateTime(new Date());
		messageDbEntity.setHaveRead(Constants.HAVEREAD.UNREAD.getKey());
		//请求的参数
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
/*			if (arg instanceof CellarMemberDbEntity) {
				*//**
				 * 获取消息上的参数
				 *//*
				CellarMemberDbEntity member = (CellarMemberDbEntity) arg;
				messageDbEntity.setMemberId(member.getMemberId());
			}else*/
			if (arg instanceof Long[]){
				/**
				 * 获取所有订单
				 */
				Long[] orderIds = (Long[]) arg;
				for (Long orderId : orderIds) {
					CellarOrderDbEntity cellarOrderDbEntity = cellarOrderDbService.getById(orderId);
					CellarMemberMessageDbEntity clone = messageDbEntity.clone();
					clone.setMemberId(cellarOrderDbEntity.getMemberId());
					cellarMemberMessageDbService.save(clone);
				}
			}else if (arg instanceof String) {
				String outtradeno = (String) arg;
				/**
				 * 根据支付号查询订单列表
				 */
				List<CellarOrderDbEntity> cellarOrderDbEntities = cellarOrderDbService.list(new QueryWrapper<CellarOrderDbEntity>().lambda()
						.eq(CellarOrderDbEntity::getOrderNo, outtradeno)
				);
				cellarOrderDbEntities.forEach( cellarOrderDbEntity -> {
					CellarMemberMessageDbEntity clone = messageDbEntity.clone();
					clone.setMemberId(cellarOrderDbEntity.getMemberId());
					cellarMemberMessageDbService.save(clone);
				});
				return;
			}else if (arg instanceof CellarMemberMessageDbEntity) {
				CellarMemberMessageDbEntity cellarMemberMessageDbEntity = (CellarMemberMessageDbEntity)arg;
				messageDbEntity.setMessageContent(cellarMemberMessageDbEntity.getMessageContent());
			}else if (arg instanceof CellarOrderDbEntity) {
				CellarOrderDbEntity cellarOrderDbEntity = (CellarOrderDbEntity)arg;
				CellarOrderDbEntity cellarOrderDbEntity1 = cellarOrderDbService.getById(Long.valueOf(cellarOrderDbEntity.getOrderId()));
				messageDbEntity.setMemberId(cellarOrderDbEntity1.getMemberId());
			}
		}


		/**
		 * 给会员批量发送消息
		 */
		if (methodName.equals("batch")) {
			/**
			 * 新起一个线程批量发送消息,及时响应
			 */
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					List<CellarMemberDbEntity> list = cellarMemberDbService.list(new QueryWrapper<CellarMemberDbEntity>().lambda()
							.eq(CellarMemberDbEntity::getState, Constants.STATE.zero.getKey())
					);
					list.forEach( cellarMemberDbEntity -> {
						CellarMemberMessageDbEntity clone = messageDbEntity.clone();
						clone.setMemberId(cellarMemberDbEntity.getMemberId());
						cellarMemberMessageDbService.save(clone);
					});
				}
			});
			return;
		}
		cellarMemberMessageDbService.save(messageDbEntity);
	}

}