package com.youxianji.dao;

import java.util.Date;
import java.util.List;

import com.youxianji.pojo.YxjBuserWithdrawcashUpper;

public interface IYxjBuserWithdrawcashUpperDAO {

	public void insertWithdrawcashUpper(YxjBuserWithdrawcashUpper yxjBuserWithdrawcashUpper);
	
	public List<YxjBuserWithdrawcashUpper> getWithdrawcashUpper(String userid, Date nowtime1,Date noetime2);
}
