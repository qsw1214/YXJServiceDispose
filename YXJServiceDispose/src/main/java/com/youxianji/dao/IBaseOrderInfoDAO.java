package com.youxianji.dao;

import java.util.List;
import java.util.Map;

import com.youxianji.pojo.BaseOrderInfo;
import com.youxianji.pojo.OrderInfo;

public interface IBaseOrderInfoDAO {
	
	int insert (BaseOrderInfo order);
	
	int update (BaseOrderInfo baseOrder);
	
	public BaseOrderInfo getByBaseOrderSn(String baseOrderSn);
	
	public List<OrderInfo> getOrderList(Map<String, String> paramMap);
	
	public BaseOrderInfo getBaseOrderInfo(Map<String, String> paramMap);
	
	public List<BaseOrderInfo> getBaseOrderList(Map<String, String> paramMap);
	
	public List<BaseOrderInfo> getWaitBaseOrderList(Map<String, String> paramMap);
}
