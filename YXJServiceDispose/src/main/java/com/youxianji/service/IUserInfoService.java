package com.youxianji.service;

import java.util.List;
import java.util.Map;

import com.youxianji.pojo.UserInfo;

public interface IUserInfoService {

	/**
	 * 根据用户ID和设备编号验证用户信息
	 */

	public UserInfo validateUser(String userid,String deviceno);
	
	public List<UserInfo> getUserInfoByTelephone(String telephone);
	
	public Map<String,Object> getUserInfoByUseridAndTelephone(String userid,String telephone);
	
	public void insertUser(UserInfo userInfo);
	
	public void updateUser(UserInfo userInfo);
	
	public UserInfo getByUserId(String userId);
	
	public int insertWechatId(String wetchid,String userId,String openId);
}
