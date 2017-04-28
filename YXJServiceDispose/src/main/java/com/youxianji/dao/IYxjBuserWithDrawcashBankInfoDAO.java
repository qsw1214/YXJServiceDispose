package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.YxjBuserWithDrawcashBankInfo;

public interface IYxjBuserWithDrawcashBankInfoDAO {
	public YxjBuserWithDrawcashBankInfo getBankInfo(String bankCode);
	
	public List<YxjBuserWithDrawcashBankInfo> getBankInfoAll();
}
