package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.ActivityProdInfo;
import com.youxianji.pojo.ProdInfo;



public interface IActivityProdInfoDAO {
	
	public ActivityProdInfo getActiveByProdId(String pordId);
	
	public List<ProdInfo> getPennyProductList();
	
	//查询一份购商品
	public ActivityProdInfo getOnecentActiveByProdId(String pordId);
	
}
