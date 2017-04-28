package com.youxianji.service;

import java.util.List;

import com.youxianji.pojo.YxjBuserWithDrawcashBankInfo;

public interface IYxjBuserWithDrawcashBankInfoService {
	public YxjBuserWithDrawcashBankInfo getBankInfo(String bankCode);
	
	public List<YxjBuserWithDrawcashBankInfo> getBankInfoAll();
	
}
