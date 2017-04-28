package com.youxianji.service;

import com.youxianji.pojo.YxjUserCouponShare;

public interface IYxjUserShareCouponService {

	/**
	 * 根据总单号查询分享红包主表信息
	 * @param baseOrderSn
	 * @return
	 */
	public YxjUserCouponShare findCouponShareByOrdersn(String baseOrderSn);
	
	/**
	 * 根据红包ID验证该红包是否过期
	 * @param shareId
	 * @return
	 */
	public YxjUserCouponShare validateTimeEndByShareId(String shareId);
}
