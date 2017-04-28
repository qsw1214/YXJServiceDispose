package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.LogInfo;

public interface ILogInfoDAO {
	
	public List<LogInfo> getLogInfoList();
	
	public int insert(LogInfo logInfo);
	
	public int update(LogInfo logInfo);
	
	public LogInfo getLogById(String id);

}
