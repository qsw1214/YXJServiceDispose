package com.youxianji.dao;

import com.youxianji.pojo.YxjBuserWithdrawcashSummation;

public interface IYxjBuserWithdrawcashSummationDAO {

	public void insertSummation(YxjBuserWithdrawcashSummation drawcashSummation);
	
	public void updateSummation(YxjBuserWithdrawcashSummation drawcashSummation);
	
	public YxjBuserWithdrawcashSummation getSummations(String summationUserid);
}
