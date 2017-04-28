package com.youxianji.service;

import java.util.List;

import com.youxianji.facade.coupon.bean.ReceiveShareCouponResponseBean;
import com.youxianji.pojo.YxjUserCouponShareDetail;

public interface IYxjUserShareCouponDetailService {

	/**
	 * 根据红包ID获取所有已领取的明细信息 
	 * @param shareId
	 * @return
	 */
	public List<YxjUserCouponShareDetail> findCouponShareDetailByShareId(String shareId);
	
	/**
	 * 根据分享红包ID随机获取一张未分配的红包券
	 * @param shareId
	 * @return
	 */
	public YxjUserCouponShareDetail getOneShareDetailByShareId(String shareId);
	
	
	/**
	 * 根据红包ID和用户ID查看该用户是否已经领过
	 * @param shareId
	 * @param userId
	 * @return
	 */
	public YxjUserCouponShareDetail validateIsReceivedByShareId(String shareId,String userId);
	
	/**
	 * 领取红包操作
	 * @param userId
	 * @param shareDetail
	 * @param responseBean
	 */
	public ReceiveShareCouponResponseBean doGetOneCoupon(String userId,String shareId,ReceiveShareCouponResponseBean responseBean);
	
	/**
	 * 修改领取红包明细表
	 * @param shareDetail
	 */
	public void updateShareDetail(YxjUserCouponShareDetail shareDetail);
}
