package com.youxianji.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IYxjBuserNationalholidayDAO;
import com.youxianji.pojo.YxjBuserWithdrawcashNationalholiday;
import com.youxianji.service.IYxjBuserNationalholidayService;
@Service("yxjBuserNationalholidayService")
public class YxjBuserNationalholidayServiceImpl implements IYxjBuserNationalholidayService {
	@Resource
	private IYxjBuserNationalholidayDAO yxjBuserNationalholidayDAO;

	@Override
	public YxjBuserWithdrawcashNationalholiday getNationalholidayByTime(Date nowTime) {
		
		return yxjBuserNationalholidayDAO.getNationalholidayByTime(nowTime);
	}

	@Override
	public YxjBuserWithdrawcashNationalholiday getNationalholidayByWorkTime(Date WorkTime) {
		return yxjBuserNationalholidayDAO.getholidayByWorkTime(WorkTime);
	}
}
