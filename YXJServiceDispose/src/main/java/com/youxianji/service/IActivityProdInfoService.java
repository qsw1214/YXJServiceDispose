package com.youxianji.service;

import com.youxianji.pojo.ActivityProdInfo;


public interface IActivityProdInfoService {
	
	public ActivityProdInfo getActiveByProdId(String prodId);

}
