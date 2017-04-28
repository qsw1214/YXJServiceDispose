package com.youxianji.service;

import java.util.List;
import java.util.Map;

import base.cn.web.mybatis.util.PageInfo;

import com.youxianji.pojo.YxjBuserAppversionInfo;

public interface IYxjBuserAppversionInfoService {
	public YxjBuserAppversionInfo getYxjBuserAppversionInfoById(String id);
	  
	  public List<YxjBuserAppversionInfo> getListYxjBuserAppversionInfo();
	    
	  public void insert(YxjBuserAppversionInfo appVersion);
	  
	  public void update(YxjBuserAppversionInfo appVersion);
	    
	  public void deleteById(String id);
	  
		/*
		 * 分页查询
		 */
		public PageInfo<YxjBuserAppversionInfo> getPageListByCondition(
				Map<String, String> hashMap);
		
		/**
		 * 根据现有版本号查询是否有更新的版本
		 * */
		public YxjBuserAppversionInfo getNewAppDeliverInfo(String versionNum,String os);
		
		/**
		 * 获取最新版本信息
		 */
		public YxjBuserAppversionInfo getCurrentAppDeliverInfo(String os);
}
