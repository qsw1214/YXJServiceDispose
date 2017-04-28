package com.youxianji.service;

import com.youxianji.pojo.YxjUserPrepaycardStock;


public interface IYxjUserPrepaycardStockService {
	
	YxjUserPrepaycardStock getByCardCode(String cardCode);
	
	
	YxjUserPrepaycardStock getByNoRepeat(String userId,String repeatFlag);
	
	
	void doChargeForUser(YxjUserPrepaycardStock stock,String userId);

}
