package com.youxianji.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.youxianji.pojo.CouponUseInfo;



public interface ICouponUseInfoDAO {
	
	public List<CouponUseInfo> getUsefulCouponList(String userId); 
	/**
	 * 根据CouponId是否使用过代金券
	 * @param CouponId
	 * @return
	 */
	public CouponUseInfo getUsedCoupon(String cuid); 
	/**
	 * 改变代金券记录状态
	 */
	public void update(CouponUseInfo couponUseInfo);

	public int insert(Map map); 
	/**
	 * 根据用户ID和订单号查询，状态 更新代金券
	 * 
	 */
	public void updateFinished(String userid,String ordersn);
	
	/**
	 * 作废过期的代金券
	 */
	public void updateCancel(String userid);
	
	public void doBackCoupon(String userid,String ordersn);
	
	public List<CouponUseInfo> getMatchUsefulCouponList(
			 @Param(value="userId")String userId
			,@Param(value="conditionAmt") BigDecimal conditionAmt);
	
}
