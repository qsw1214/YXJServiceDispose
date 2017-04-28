package com.youxianji.service;

import java.util.List;
import java.util.Map;

import base.cn.web.mybatis.util.PageInfo;

import com.youxianji.pojo.UserBills;

public interface IUserBillsService {

	/**
	 * 根据用户ID获取账单信息
	 * @param userid
	 * @return
	 */
	public PageInfo<UserBills> getPageBillsByUserId(Map<String,String> paramsMap);
	
	public List<UserBills> getBillsByUserIdOut(String userid);
	
	public List<UserBills> getBillsByUserIdIn(String userid);
}
