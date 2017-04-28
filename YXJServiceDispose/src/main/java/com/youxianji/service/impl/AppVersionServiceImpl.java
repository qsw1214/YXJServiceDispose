package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IAppVersionDAO;
import com.youxianji.service.IAppVersionService;

@Service("appVersion")
public class AppVersionServiceImpl implements IAppVersionService {

	@Resource
	private IAppVersionDAO appVersionDAO;
	
	
}
