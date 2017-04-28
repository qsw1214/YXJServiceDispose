package com.youxianji.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IUserInfoDAO;
import com.youxianji.pojo.UserInfo;
import com.youxianji.service.IUserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService{
	
	@Resource
	private IUserInfoDAO userInfoDAO;

	@Override
	public UserInfo validateUser(String userid, String deviceno) {
		
		return userInfoDAO.validateUser(userid,deviceno);
	}

	@Override
	public List<UserInfo> getUserInfoByTelephone(String telephone) {

//		return userInfoDAO.validateUser(telephone);
		return userInfoDAO.getUserInfoByTelephone(telephone);
	}

	@Override
	public void insertUser(UserInfo userInfo) {
		
		userInfoDAO.insertUser(userInfo);
	}

	@Override
	public void updateUser(UserInfo userInfo) {
		
		userInfoDAO.updateUser(userInfo);
	}

	@Override
	public UserInfo getByUserId(String userId) {
		return userInfoDAO.getById(userId);
	}

	@Override
	public Map<String,Object> getUserInfoByUseridAndTelephone(String userid, String telephone) {
		
		return userInfoDAO.getUserInfoByUseridAndTelephone(userid, telephone);
	}

	@Override
	public int insertWechatId(String wetchid,String userId, String openId) {
		
		return userInfoDAO.insertWechatId(wetchid,userId, openId);
	}

}
