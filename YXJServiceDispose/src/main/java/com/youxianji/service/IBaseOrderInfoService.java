package com.youxianji.service;

import java.util.Map;

import base.cn.web.mybatis.util.PageInfo;

import com.youxianji.pojo.BaseOrderInfo;

public interface IBaseOrderInfoService {
	
	public BaseOrderInfo getBaseOrderInfo(Map<String, String> paramMap);
	public PageInfo<BaseOrderInfo> getPageBaseOrderList(Map<String,String> paramsMap);
	
	int update(BaseOrderInfo baseOrder);
}
