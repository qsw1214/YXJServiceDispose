package com.youxianji.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import base.cn.web.mybatis.plugin.PageInterceptor;
import base.cn.web.mybatis.util.PageInfo;

import com.youxianji.dao.IUserBillsDAO;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.pojo.UserBills;
import com.youxianji.service.IUserBillsService;

@Service("userBillsService")
public class UserBillsServiceImpl implements IUserBillsService {

	@Resource
	private IUserBillsDAO userBillsDAO;

	
	/**
	 * 根据用户ID获取账单信息
	 * @param userid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<UserBills> getPageBillsByUserId(Map<String,String> paramsMap){
		PageInterceptor.startPage(Integer.parseInt(paramsMap.get("page")));
		userBillsDAO.getPageBillsByUserId(paramsMap);
		return PageInterceptor.endPage();
	}


	@Override
	public List<UserBills> getBillsByUserIdOut(String userid) {
		
		return userBillsDAO.getBillsByUserIdOut(userid);
	}


	@Override
	public List<UserBills> getBillsByUserIdIn(String userid) {
		return userBillsDAO.getBillsByUserIdIn(userid);
	}

}
