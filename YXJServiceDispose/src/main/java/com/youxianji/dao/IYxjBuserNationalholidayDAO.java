package com.youxianji.dao;

import java.util.Date;

import com.youxianji.pojo.YxjBuserWithdrawcashNationalholiday;

public interface IYxjBuserNationalholidayDAO {

	public YxjBuserWithdrawcashNationalholiday getNationalholidayByTime(Date nowTime);
	public YxjBuserWithdrawcashNationalholiday getholidayByWorkTime(Date WorkTime);
}
