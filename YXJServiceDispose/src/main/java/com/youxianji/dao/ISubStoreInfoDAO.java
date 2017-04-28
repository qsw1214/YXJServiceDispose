package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.SubStoreInfo;



public interface ISubStoreInfoDAO{
	
	
	public List<SubStoreInfo> findList();
	
	public List<SubStoreInfo> findListInArea(String latitude,String longitude);


}
