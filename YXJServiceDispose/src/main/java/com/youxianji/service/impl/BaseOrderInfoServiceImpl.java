package com.youxianji.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import base.cn.web.mybatis.plugin.PageInterceptor;
import base.cn.web.mybatis.util.PageInfo;

import com.youxianji.dao.IBaseOrderInfoDAO;
import com.youxianji.pojo.BaseOrderInfo;
import com.youxianji.service.IBaseOrderInfoService;
@Service("baseOrderInfoService")
public class BaseOrderInfoServiceImpl implements IBaseOrderInfoService {
	@Resource
	private IBaseOrderInfoDAO baseOrderInfoDAO;
	@Override
	public BaseOrderInfo getBaseOrderInfo(Map<String, String> paramMap) {
		
		return baseOrderInfoDAO.getBaseOrderInfo(paramMap);
	}
	@Override
	public PageInfo<BaseOrderInfo> getPageBaseOrderList(Map<String, String> paramsMap) {
		PageInterceptor.startPage(Integer.parseInt(paramsMap.get("page")));
		baseOrderInfoDAO.getWaitBaseOrderList(paramsMap);
		return PageInterceptor.endPage();
	}
	@Override
	public int update(BaseOrderInfo baseOrder) {
		baseOrderInfoDAO.update(baseOrder);
		return 0;
	}

}
