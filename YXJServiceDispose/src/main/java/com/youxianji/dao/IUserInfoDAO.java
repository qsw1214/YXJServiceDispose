package com.youxianji.dao;

import java.util.List;
import java.util.Map;

import com.youxianji.pojo.UserInfo;

public interface IUserInfoDAO {
	
	
	/**
	 * 根据用户ID和设备编号验证用户信息
	 */
	public Map<String,Object> getUserInfoByUseridAndTelephone(String userid,String telephone);
	
	public UserInfo validateUser(String userid,String deviceno);
	
	public List<UserInfo> getUserInfoByTelephone(String telephone);
	
	public UserInfo getById(String userId);
	
	public void insertUser(UserInfo userInfo);
	
	public void updateUser(UserInfo userInfo);
	
	public int insertWechatId(String wetchid,String userId,String openId);
}
