package com.youxianji.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import base.cn.util.ObjectTools;
import base.cn.web.mybatis.plugin.PageInterceptor;
import base.cn.web.mybatis.util.PageInfo;

import com.youxianji.dao.IYxjBuserAppversionInfoDAO;
import com.youxianji.pojo.YxjBuserAppversionInfo;
import com.youxianji.service.IYxjBuserAppversionInfoService;
import com.youxianji.util.DateUtil;

@Service("yxjBuserAppversionInfoSerivce")
public class YxjBuserAppversionInfoSerivceImpl implements IYxjBuserAppversionInfoService {
	
	@Resource
	private IYxjBuserAppversionInfoDAO yxjBuserAppversionInfoDAO;

	@Override
	public YxjBuserAppversionInfo getYxjBuserAppversionInfoById(String id) {
		return yxjBuserAppversionInfoDAO.getYxjBuserAppversionInfoById(id);
	}

	@Override
	public void insert(YxjBuserAppversionInfo appVersion) {
		yxjBuserAppversionInfoDAO.insert(appVersion);
	}

	@Override
	public void deleteById(String id) {
		yxjBuserAppversionInfoDAO.deleteById(id);
	}

	@Override
	public List<YxjBuserAppversionInfo> getListYxjBuserAppversionInfo() {

		return yxjBuserAppversionInfoDAO.getListYxjBuserAppversionInfo();
	}

	@Override
	public void update(YxjBuserAppversionInfo appVersion) {
		yxjBuserAppversionInfoDAO.update(appVersion);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<YxjBuserAppversionInfo> getPageListByCondition(
			Map<String, String> hashMap) {
		
		PageInterceptor.startPage(Integer.parseInt(hashMap.get("page")));
		yxjBuserAppversionInfoDAO.getListYxjBuserAppversionInfo();
		return PageInterceptor.endPage();
	}

	@Override
	public YxjBuserAppversionInfo getNewAppDeliverInfo(String versionNum, String os) {

		YxjBuserAppversionInfo curAppInfo = yxjBuserAppversionInfoDAO.getYxjBuserAppversionInfoByOsAndVersionNum(os, versionNum);
		if(ObjectTools.isNullByObject(curAppInfo)){
			throw new RuntimeException("版本号:" + versionNum + "在系统中不存在.");
		}
		String curDate = DateUtil.toStr(curAppInfo.getBuserAppCurdate(),"yyyy-MM-dd HH:mm:ss");
		return yxjBuserAppversionInfoDAO.getNewAppDeliverInfo(os, curDate);
	}

	@Override
	public YxjBuserAppversionInfo getCurrentAppDeliverInfo(String os) {
		// TODO Auto-generated method stub
		return yxjBuserAppversionInfoDAO.getCurrentAppDeliverInfo(os);
	}

	
	
}
