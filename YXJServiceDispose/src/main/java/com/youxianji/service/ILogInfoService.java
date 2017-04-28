package com.youxianji.service;

import java.util.Map;

import base.cn.web.mybatis.util.PageInfo;

import com.youxianji.pojo.LogInfo;


public interface ILogInfoService {
	
	public PageInfo<LogInfo> getPageListByCondition(
			Map<String, String> hashMap);
	
	public void saveLogInfo(LogInfo logInfo);
	
	public void updateLogInfo(LogInfo logInfo);
	
	public LogInfo getLogById(String id);
	
}
