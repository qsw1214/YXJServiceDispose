package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.DeductAmtRules;



public interface IDeductAmtRulesDAO {

	public List<DeductAmtRules> getList();
	
	DeductAmtRules getByProdId(String prodId,String quantity);
	
}
