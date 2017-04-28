package com.youxianji.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import base.cn.web.mybatis.plugin.PageInterceptor;
import base.cn.web.mybatis.util.PageInfo;

import com.youxianji.dao.ILogInfoDAO;
import com.youxianji.pojo.LogInfo;
import com.youxianji.service.ILogInfoService;

@Service("logInfoService")
public class LogInfoServiceImpl implements ILogInfoService{
	
	@Resource
	private ILogInfoDAO logInfoDAO;

	@Override
	public PageInfo<LogInfo> getPageListByCondition(Map<String, String> hashMap) {
		PageInterceptor.startPage(Integer.parseInt(hashMap.get("page")));
		logInfoDAO.getLogInfoList();
		return PageInterceptor.endPage();
	}

	@Override
	public void saveLogInfo(LogInfo logInfo) {
		logInfoDAO.insert(logInfo);
		
	}

	@Override
	public void updateLogInfo(LogInfo logInfo) {
		logInfoDAO.update(logInfo);
		
	}

	@Override
	public LogInfo getLogById(String id) {
		return logInfoDAO.getLogById(id);
	}


}
