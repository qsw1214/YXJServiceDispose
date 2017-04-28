package com.youxianji.service;

import java.util.Map;

import base.cn.exception.BaseException;

public interface IUserRechargeService {
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
	
	
	public Map<String,String> doWechatChargeMemberCard(String cardid,String userId,String ip,String payType,String employnum) throws BaseException;
}
