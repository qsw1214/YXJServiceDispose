package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IHomeAdDAO;
import com.youxianji.pojo.HomeAd;
import com.youxianji.service.IHomeAdService;

@Service("homeAdService")
public class HomeAdSerivceImpl implements IHomeAdService {
	
	@Resource
	private IHomeAdDAO homeAdDAO;

	@Override
	public List<HomeAd> getHomeAdList() {
		
		
		return homeAdDAO.getHomeAdList();
	}
	
	
}
