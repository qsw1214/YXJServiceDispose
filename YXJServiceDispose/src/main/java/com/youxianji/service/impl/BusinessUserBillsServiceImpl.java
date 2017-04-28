package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IBusinessUserBillsDAO;
import com.youxianji.service.IBusinessUserBillsService;

@Service("businessUserBills")
public class BusinessUserBillsServiceImpl implements IBusinessUserBillsService {
	
	@Resource
	private IBusinessUserBillsDAO businessUserBillsDAO;
	
	
}
