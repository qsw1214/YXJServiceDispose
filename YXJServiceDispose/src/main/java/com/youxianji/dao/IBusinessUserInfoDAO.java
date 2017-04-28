package com.youxianji.dao;

import com.youxianji.pojo.BusinessUserInfo;

public interface IBusinessUserInfoDAO{
	
	/** 
	 * 根据用户名和密码获取商户信息
	 * @param buserName
	 * @param password
	 * @return
	 */
	public BusinessUserInfo validateBusinessUser(String buserName,String loginPass);
   
}