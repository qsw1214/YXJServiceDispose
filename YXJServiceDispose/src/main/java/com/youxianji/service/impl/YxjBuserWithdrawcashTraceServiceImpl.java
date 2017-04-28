package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IYxjBuserWithdrawcashTraceDAO;
import com.youxianji.pojo.YxjBuserWithdrawcashTrace;
import com.youxianji.service.IYxjBuserWithdrawcashTraceService;
@Service("yxjBuserWithdrawcashTraceService")
public class YxjBuserWithdrawcashTraceServiceImpl implements IYxjBuserWithdrawcashTraceService {

	@Resource
	private IYxjBuserWithdrawcashTraceDAO yxjBuserWithdrawcashTraceDAO;

	@Override
	public void insertWithdrawcashTrace(YxjBuserWithdrawcashTrace withdrawcashTrace) {
		yxjBuserWithdrawcashTraceDAO.insertWithdrawcashTrace(withdrawcashTrace);
	}
}
