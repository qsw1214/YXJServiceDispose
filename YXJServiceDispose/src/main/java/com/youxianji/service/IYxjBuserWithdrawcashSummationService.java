package com.youxianji.service;

import com.youxianji.pojo.YxjBuserWithdrawcashSummation;

public interface IYxjBuserWithdrawcashSummationService {

	public void insertWithdrawcashSummation(YxjBuserWithdrawcashSummation drawcashSummation);
	
	public void updateWithdrawcashSummation(YxjBuserWithdrawcashSummation drawcashSummation);
	
	public YxjBuserWithdrawcashSummation getSummation(String summationUserid);
}
