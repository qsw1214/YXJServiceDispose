package com.youxianji.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IYxjBuserWithdrawcashUpperDAO;
import com.youxianji.pojo.YxjBuserWithdrawcashUpper;
import com.youxianji.service.IYxjBuserWithdrawcashUpperService;
@Service("yxjBuserWithdrawcashUpperService")
public class YxjBuserWithdrawcashUpperServiceImpl implements IYxjBuserWithdrawcashUpperService {
	@Resource
	private IYxjBuserWithdrawcashUpperDAO yxjBuserWithdrawcashUpperDAO;

	@Override
	public void insertWithdrawcashUpper(YxjBuserWithdrawcashUpper yxjBuserWithdrawcashUpper) {
		yxjBuserWithdrawcashUpperDAO.insertWithdrawcashUpper(yxjBuserWithdrawcashUpper);
	}

	@Override
	public List<YxjBuserWithdrawcashUpper> getWithdrawcashUpper(String userid, Date nowtime1,Date noetime2) {
		return yxjBuserWithdrawcashUpperDAO.getWithdrawcashUpper(userid, nowtime1,noetime2);
	}
	
	
}
