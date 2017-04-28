package com.youxianji.service;

import java.util.List;
import java.util.Map;

import com.youxianji.pojo.CouponUseInfo;

public interface ICouponUseInfoService {
    //查询用户可使用的代金券列表
	public List<CouponUseInfo> getUsefulCouponList(String userId);

	public int insert(Map map);
	
	public void updateCancel(String userId);
	
	public void doBackCoupon(String userId,String ordersn);
	
	public CouponUseInfo getUsedCoupon(String cuid); 
}
