package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IyxjSystemSmsConfigDAO;
import com.youxianji.pojo.yxj_system_sms_config;
import com.youxianji.service.IyxjSystemSmsConfigService;
@Service("yxjSystemSmsConfigService")
public class YxjSystemSmsConfigServiceImpl implements IyxjSystemSmsConfigService {

	@Resource
	private IyxjSystemSmsConfigDAO yxjSystemSmsConfigDAO;

	@Override
	public yxj_system_sms_config getSmsConfigByPointCode(String pointCode) {
		return yxjSystemSmsConfigDAO.getSysSmsConfigByPointCode(pointCode);
	}
	
}
