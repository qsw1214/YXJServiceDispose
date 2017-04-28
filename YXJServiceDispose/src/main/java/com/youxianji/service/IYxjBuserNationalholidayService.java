package com.youxianji.service;

import java.util.Date;

import com.youxianji.pojo.YxjBuserWithdrawcashNationalholiday;

public interface IYxjBuserNationalholidayService {

	public YxjBuserWithdrawcashNationalholiday getNationalholidayByTime(Date nowTime);
	public YxjBuserWithdrawcashNationalholiday getNationalholidayByWorkTime(Date WorkTime);
}
