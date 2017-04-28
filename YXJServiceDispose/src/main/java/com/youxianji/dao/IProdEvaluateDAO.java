package com.youxianji.dao;

import java.util.List;
import java.util.Map;

import com.youxianji.pojo.ProdEvaluate;




public interface IProdEvaluateDAO {
	
	public int insert(ProdEvaluate prodEvaluate);
	
	public List<ProdEvaluate> getListByProdId(String prodId);
	
	//根据用户ID、订单号、商品ID查找评价信息
	public List<ProdEvaluate> validateEvaluate(Map<String,String> map);
}
