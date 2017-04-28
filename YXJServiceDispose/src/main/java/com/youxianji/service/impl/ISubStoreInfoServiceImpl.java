package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.ISubStoreInfoDAO;
import com.youxianji.service.ISubStoreInfoService;

@Service("subStoreInfo")
public class ISubStoreInfoServiceImpl implements ISubStoreInfoService {

		@Resource
		private ISubStoreInfoDAO subStoreInfoDAO;
		
}
