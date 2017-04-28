package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IYxjUserCouponShareDAO;
import com.youxianji.pojo.YxjUserCouponShare;
import com.youxianji.service.IYxjUserShareCouponService;

@Service("yxjUserShareCouponService")
public class YxjUserShareCouponServiceImpl implements IYxjUserShareCouponService{
	
	@Resource
	private IYxjUserCouponShareDAO yxjUserCouponShareDAO;

	@Override
	public YxjUserCouponShare findCouponShareByOrdersn(String baseOrderSn) {
		
		return yxjUserCouponShareDAO.findCouponShareByOrdersn(baseOrderSn);
	}

	@Override
	public YxjUserCouponShare validateTimeEndByShareId(String shareId) {
		
		return yxjUserCouponShareDAO.validateTimeEndByShareId(shareId);
	}

}
