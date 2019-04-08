package io.renren.modules.job.task;

/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.constants.Constants;
import io.renren.common.utils.R;
import io.renren.common.utils.pay.AliUtil;
import io.renren.common.utils.pay.WechatPayUtil;
import io.renren.common.validator.Assert;
import io.renren.modules.cellar.entity.CellarGroupActivityDbEntity;
import io.renren.modules.cellar.entity.CellarMemberDbEntity;
import io.renren.modules.cellar.entity.CellarOrderDbEntity;
import io.renren.modules.cellar.service.*;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 拼团活动定时任务
 * 用来检测拼团是否成功
 * groupActivityTask为spring bean的名称
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.2.0 2016-11-28
 */
@Component("groupActivityTask")
public class GroupActivityTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private CellarGroupActivityDbService cellarGroupActivityDbService;
	@Autowired
	private CellarOrderDbService cellarOrderDbService;
	@Autowired
	private CellarConfigDbService cellarConfigDbService;
	@Autowired
	private CellarStoreDbService cellarStoreDbService;
	@Autowired
	private CellarCommodityDbService cellarCommodityDbService;
	@Autowired
	private CellarMemberDbService cellarMemberDbService;
	@Autowired
	private CellarOrderDetailsDbService cellarOrderDetailsDbService;
	@Autowired
	private CellarMemberCouponDbService cellarMemberCouponDbService;
	@Autowired
	private CellarMemberBalanceChangeRecordDbService cellarMemberBalanceChangeRecordDbService;
	@Autowired
	private CellarMemberCardBalanceChangeRecordDbService cellarMemberCardBalanceChangeRecordDbService;

	public void groupActivity(String groupActivityId) {

		CellarGroupActivityDbEntity cellarGroupActivityDbEntity = cellarGroupActivityDbService.getById(Long.valueOf(groupActivityId));
		Assert.isNull(cellarGroupActivityDbEntity,"拼团活动不存在");
		/**
		 * 拼团失败
		 */
		if (cellarGroupActivityDbEntity.getStayGroupNumber().compareTo(BigDecimal.ZERO) > 0) {

			List<CellarOrderDbEntity> list = cellarOrderDbService.list(new QueryWrapper<CellarOrderDbEntity>().lambda()
					.eq(CellarOrderDbEntity::getGroupActivityId, cellarGroupActivityDbEntity.getGroupActivityId())
					.eq(CellarOrderDbEntity::getOrderStatus, Constants.ORDERSTATUS.ZERO.getKey())
					.eq(CellarOrderDbEntity::getState, Constants.STATE.zero.getKey())
			);
			for (CellarOrderDbEntity cellarOrderDbEntity : list) {
				CellarMemberDbEntity cellarMemberDbEntity = cellarMemberDbService.getById(cellarOrderDbEntity.getMemberId());
				/**
				 * 微信
				 */
				if (cellarOrderDbEntity.getMethodPayment().equals(Constants.METHODPAYMENT.WECHAT.getKey())) {

				}else if (cellarOrderDbEntity.getMethodPayment().equals(Constants.METHODPAYMENT.ALIPAY.getKey())) {
					AliUtil.refund(cellarOrderDbEntity.getOrderNo(),cellarOrderDbEntity.getActualOrderAmount());
				}else if (cellarOrderDbEntity.getMethodPayment().equals(Constants.METHODPAYMENT.BALANCE.getKey())) {
					cellarMemberBalanceChangeRecordDbService.balanceRefund(cellarMemberDbEntity,cellarOrderDbEntity.getActualOrderAmount(),cellarOrderDbEntity.getOrderNo(),null);
				}else if (cellarOrderDbEntity.getMethodPayment().equals(Constants.METHODPAYMENT.CARDBALANCE.getKey())) {
					cellarMemberCardBalanceChangeRecordDbService.cardBalanceRefund(cellarMemberDbEntity,cellarOrderDbEntity.getActualOrderAmount(),cellarOrderDbEntity.getOrderNo(),null);
				}
				cellarOrderDbService.refund(cellarOrderDbEntity.getOrderNo());
			}

		}

	}
	
}
