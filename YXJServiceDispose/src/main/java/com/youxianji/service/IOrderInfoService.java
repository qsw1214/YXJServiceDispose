package com.youxianji.service;

import java.util.List;
import java.util.Map;

import com.youxianji.pojo.OrderInfo;

import base.cn.web.mybatis.util.PageInfo;

public interface IOrderInfoService {

	/**
	 * 根据条件分页查询订单信息
	 * @param paramHashMap
	 * @return
	 */
	public PageInfo<OrderInfo> getPageOrderList(Map<String, String> paramMap);
	
    public OrderInfo getByOrderSn(String orderSn);
    
    public void update (OrderInfo order);
    
    public void doConfirmOrder(OrderInfo order);

	public List<OrderInfo> getBarginInfoList(String userid);
	
	public PageInfo<OrderInfo> getPageWaitPayOrderList(Map<String,String> paramsMap);
	
	public OrderInfo getPageBaseOrderDetailList(Map<String, String> paramMap);
	
	public List<OrderInfo> getOrderInfoList(String baseOrdersn);
}
