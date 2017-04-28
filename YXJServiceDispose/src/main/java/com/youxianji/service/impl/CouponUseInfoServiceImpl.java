package com.youxianji.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.ICouponUseInfoDAO;
import com.youxianji.dao.IOrderDetailDAO;
import com.youxianji.dao.IProdInfoDAO;
import com.youxianji.pojo.CouponUseInfo;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.service.ICouponUseInfoService;

@Service("couponUseInfoService")
public class CouponUseInfoServiceImpl implements ICouponUseInfoService {

	@Resource
	private ICouponUseInfoDAO couponUseInfoDAO;

	@Override
	public List<CouponUseInfo> getUsefulCouponList(String userId) {
		return couponUseInfoDAO.getUsefulCouponList(userId);
	}

	@Override
	public int insert(Map map) {
		// TODO Auto-generated method stub
		 return	 couponUseInfoDAO.insert(map);
		
	
	}

	@Override
	public void updateCancel(String userId) {
		//更改已过期的代金券状态
		couponUseInfoDAO.updateCancel(userId);
		
	}

	@Override
	public void doBackCoupon(String userId, String ordersn) {
		couponUseInfoDAO.doBackCoupon(userId, ordersn);
		
	}

	@Override
	public CouponUseInfo getUsedCoupon(String cuid) {
		// TODO Auto-generated method stub
		return couponUseInfoDAO.getUsedCoupon(cuid);
	}
	
}
