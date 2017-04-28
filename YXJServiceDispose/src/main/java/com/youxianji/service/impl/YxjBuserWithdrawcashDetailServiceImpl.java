package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IYxjBuserWithdrawcashDetailDAO;
import com.youxianji.pojo.YxjBuserWithdrawcashDetail;
import com.youxianji.service.IYxjBuserWithdrawcashDetailService;
@Service("yxjBuserWithdrawcashDetailService")
public class YxjBuserWithdrawcashDetailServiceImpl implements IYxjBuserWithdrawcashDetailService {
	@Resource
	private IYxjBuserWithdrawcashDetailDAO yxjBuserWithdrawcashDetailDAO;
	@Override
	public void saveWithdrawcashDetail(YxjBuserWithdrawcashDetail WithdrawcashDetail) {
		yxjBuserWithdrawcashDetailDAO.insertWithdrawcashDetail(WithdrawcashDetail);
	}

}
