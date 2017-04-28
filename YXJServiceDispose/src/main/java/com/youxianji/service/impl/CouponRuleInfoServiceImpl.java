package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.ICouponRuleInfoDAO;
import com.youxianji.pojo.CouponRuleInfo;
import com.youxianji.service.ICouponRuleInfoService;

@Service("couponRuleInfoService")
public class CouponRuleInfoServiceImpl implements ICouponRuleInfoService {

	@Resource
	private ICouponRuleInfoDAO couponRuleInfoDAO;

	@Override
	public CouponRuleInfo findCouponInfoById(String couponId) {
		
		return couponRuleInfoDAO.findCouponInfoById(couponId);
	}

}
