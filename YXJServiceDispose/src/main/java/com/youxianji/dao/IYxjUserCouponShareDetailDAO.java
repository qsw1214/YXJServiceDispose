package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.YxjUserCouponShareDetail;

public interface IYxjUserCouponShareDetailDAO {
	
	/**
	 * 根据红包ID获取所有已领取的明细信息 
	 * @param shareId
	 * @return
	 */
	public List<YxjUserCouponShareDetail> findCouponShareDetailByShareId(String shareId);
	
	/**
	 * 根据红包ID随机获取一张红包券
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
	
	int insert(YxjUserCouponShareDetail shareDetail);
	
	public void update(YxjUserCouponShareDetail shareDetail);
    
}