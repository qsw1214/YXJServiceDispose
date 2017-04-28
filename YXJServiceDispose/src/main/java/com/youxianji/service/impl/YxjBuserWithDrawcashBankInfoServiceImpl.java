package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IYxjBuserWithDrawcashBankInfoDAO;
import com.youxianji.pojo.YxjBuserWithDrawcashBankInfo;
import com.youxianji.service.IYxjBuserWithDrawcashBankInfoService;
@Service("yxjBuserWithDrawcashBankInfoService")
public class YxjBuserWithDrawcashBankInfoServiceImpl implements IYxjBuserWithDrawcashBankInfoService {
	@Resource
	private IYxjBuserWithDrawcashBankInfoDAO yxjBuserWithDrawcashBankInfoDAO;
	@Override
	public YxjBuserWithDrawcashBankInfo getBankInfo(String bankCode) {
		return yxjBuserWithDrawcashBankInfoDAO.getBankInfo(bankCode);
	}
	@Override
	public List<YxjBuserWithDrawcashBankInfo> getBankInfoAll() {
		return yxjBuserWithDrawcashBankInfoDAO.getBankInfoAll();
	}

}
