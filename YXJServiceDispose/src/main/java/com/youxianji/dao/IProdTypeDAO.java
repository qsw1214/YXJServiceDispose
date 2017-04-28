package com.youxianji.dao;

import java.util.List;
import java.util.Map;

import com.youxianji.pojo.ProdType;



public interface IProdTypeDAO{
	
	public List<ProdType> getProdTypeList(Map<String,String> paramsMap) ;
	


}
