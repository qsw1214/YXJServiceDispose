package com.youxianji.dao;

import java.util.List;
import java.util.Map;

import base.cn.web.mybatis.util.PageInfo;

import com.youxianji.pojo.UserBills;


public interface IUserBillsDAO {
	
	/**
	 * 根据用户ID获取账单信息
	 * @param userid
	 * @return
	 */
	public List<UserBills> getPageBillsByUserId(Map<String,String> paramsMap);
	
	public List<UserBills> getBillsByUserIdOut(String userid);
	
	public List<UserBills> getBillsByUserIdIn(String userid);
}
