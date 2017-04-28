package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IWeChatUserInfoDAO;
import com.youxianji.pojo.WeChatUserInfo;
import com.youxianji.service.IWeChatUserInfoService;

@Service("weChatUserInfo")
public class WeChatUserInfoServiceImpl implements IWeChatUserInfoService {

	@Resource
	private IWeChatUserInfoDAO weChatUserInfoDAO;

	@Override
	public WeChatUserInfo getWeChatUserInfoByUserId(String userId) {
		// TODO Auto-generated method stub
		return weChatUserInfoDAO.getWeChatUserInfoByUserId(userId);
	}

	@Override
	public WeChatUserInfo getByOpenId(String openid) {
		// TODO Auto-generated method stub
		return weChatUserInfoDAO.getByOpenId(openid);
	}

	@Override
	public int update(String openId, String userId) {
		// TODO Auto-generated method stub
		return weChatUserInfoDAO.update(openId, userId);
	}
}
