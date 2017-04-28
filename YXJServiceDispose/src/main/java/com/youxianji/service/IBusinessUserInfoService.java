package com.youxianji.service;

import com.youxianji.pojo.BusinessUserInfo;

public interface IBusinessUserInfoService {
	
	/** 
	 * 根据手机号和密码获取商户信息
	 * @param userName
	 * @param password
	 * @return
	 */
	public BusinessUserInfo validateBusinessUser(String userName,String loginPass);

}
