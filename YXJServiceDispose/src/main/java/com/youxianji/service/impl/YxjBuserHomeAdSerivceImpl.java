package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IYxjBuserHomeAdDAO;
import com.youxianji.pojo.YxjBuserHomeAd;
import com.youxianji.service.IYxjBuserHomeAdService;

@Service("yxjBuserHomeAdService")
public class YxjBuserHomeAdSerivceImpl implements IYxjBuserHomeAdService {
	
	@Resource
	private IYxjBuserHomeAdDAO yxjBuserHomeAdDAO;

	@Override
	public List<YxjBuserHomeAd> getYxjBuserHomeAdList() {
		
		
		return yxjBuserHomeAdDAO.getYxjBuserHomeAdList();
	}
	
	
}
