package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youxianji.dao.IActivityProdInfoDAO;
import com.youxianji.pojo.ActivityProdInfo;
import com.youxianji.service.IActivityProdInfoService;

@Service("activityProdInfoService")
public class ActivityProdInfoServiceImpl implements IActivityProdInfoService {

	@Resource
	private IActivityProdInfoDAO activityProdInfoDAO;

	@Override
	public ActivityProdInfo getActiveByProdId(String prodId) {

		return activityProdInfoDAO.getActiveByProdId(prodId);
	}
}
