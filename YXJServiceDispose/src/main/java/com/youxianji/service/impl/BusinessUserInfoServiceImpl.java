package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IBusinessUserInfoDAO;
import com.youxianji.pojo.BusinessUserInfo;
import com.youxianji.service.IBusinessUserInfoService;

@Service("businessUserService")
public class BusinessUserInfoServiceImpl implements IBusinessUserInfoService{
	
	@Resource
	private IBusinessUserInfoDAO businessUserInfoDAO;

	/** 
	 * 根据手机号和密码获取商户信息
	 * @param userName
	 * @param password
	 * @return
	 */
	@Override
	public BusinessUserInfo validateBusinessUser(String userName, String password) {
		return businessUserInfoDAO.validateBusinessUser(userName, password);
	}

}
