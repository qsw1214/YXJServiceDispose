package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IYxjBuserWithdrawcashSummationDAO;
import com.youxianji.pojo.YxjBuserWithdrawcashSummation;
import com.youxianji.service.IYxjBuserWithdrawcashSummationService;
@Service("yxjBuserWithdrawcashSummationService")
public class YxjBuserWithdrawcashSummationServiceImpl implements IYxjBuserWithdrawcashSummationService {
	@Resource
	private IYxjBuserWithdrawcashSummationDAO yxjBuserWithdrawcashSummationDAO;

	@Override
	public void insertWithdrawcashSummation(YxjBuserWithdrawcashSummation drawcashSummation) {

		yxjBuserWithdrawcashSummationDAO.insertSummation(drawcashSummation);
	}

	@Override
	public void updateWithdrawcashSummation(YxjBuserWithdrawcashSummation drawcashSummation) {
		yxjBuserWithdrawcashSummationDAO.updateSummation(drawcashSummation);
	}

	@Override
	public YxjBuserWithdrawcashSummation getSummation(String summationUserid) {
		return yxjBuserWithdrawcashSummationDAO.getSummations(summationUserid);
	}
	
	
}
