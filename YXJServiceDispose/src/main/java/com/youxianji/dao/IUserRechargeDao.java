package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.UserThirdCharge;

public interface IUserRechargeDao {
	/**
	 * 用户充值业务处理
	 * @param userid
	 * @param telephone
	 * @param discountid
	 * @param normalcharge
	 */
	public void userRecharge(String userid,String telephone,String discountid,String normalcharge);
	/**
	 * 查询用户是不是会员
	 * @param userid
	 * @param telephone
	 */
	public String queryUserIsmember(String userid,String telephone);

	public void insert(UserThirdCharge userThirdCharge);
	
	public UserThirdCharge getUserThirdChargeByOrderSn(String chargeSn);
	
	
	public List<UserThirdCharge> getEmployeeChargeList(String userId);
	/**
	 * 查询用户是不是会员
	 * @param userid
	 * @param telephone
	 */
	public List<UserThirdCharge> getListByUserIdAndAmount(String userId,String chargeAmount);
}
