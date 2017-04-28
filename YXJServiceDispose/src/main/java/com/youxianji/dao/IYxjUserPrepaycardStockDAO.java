package com.youxianji.dao;

import com.youxianji.pojo.YxjUserPrepaycardStock;



public interface IYxjUserPrepaycardStockDAO {

	YxjUserPrepaycardStock getByCardCode(String cardCode);
	
	int update(String cardCode,String userId); 
	
	YxjUserPrepaycardStock getByNoRepeat(String userId,String repeatFlag);
	
}
