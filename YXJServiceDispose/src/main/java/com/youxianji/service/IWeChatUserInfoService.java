package com.youxianji.service;

import com.youxianji.pojo.WeChatUserInfo;

public interface IWeChatUserInfoService {
	
	  public WeChatUserInfo getWeChatUserInfoByUserId(String userId);
	/**
	 * 根据openid查询用户id
	 * @param openid
	 * @return
	 */
	public WeChatUserInfo getByOpenId(String openid);
	
	
	public int update(String openId,String userId);
	
}
