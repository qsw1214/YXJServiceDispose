package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IRecommendDAO;
import com.youxianji.pojo.Recommend;
import com.youxianji.service.IRecommendService;

@Service("recommendService")
public class IRecommendServiceImpl implements IRecommendService {

	@Resource
	private IRecommendDAO recommendDAO;

	@Override
	public List<Recommend> getRecommendProdList() {

		
		return recommendDAO.getRecommendProdList();
	}

	
	
	
}
