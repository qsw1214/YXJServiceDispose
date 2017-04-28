package com.youxianji.dao;

import java.util.List;
import java.util.Map;

import com.youxianji.pojo.OrderInfo;

public interface IOrderInfoDAO {
	
	int insert (OrderInfo order);
	
	int update (OrderInfo order);
	
	public OrderInfo getByOrderSn(String orderSn);
	
	public List<OrderInfo> getListByBaseOrderSn(String baseOrderSn);
	
	/**
	 * 根据条件分页查询订单信息
	 * @param paramHashMap
	 * @return
	 */
	public List<OrderInfo> getOrderList(Map<String, String> paramMap);
	
	///更改物流状态表状态
	
	public void udpateLogisticsState(String state,String ordersn);
	
	/**
	 * 根据userid查询一份购订单
	 */
	List<OrderInfo> getOnecentOrderList(String userid);
	
	/**
	 * 根据userid查询一份购订单
	 */
	List<OrderInfo> getBarginInfoList(String userid);
	
	/**
	 * 切换订单金额
	 * @return
	 */
	int doSwitchOrderAmt(OrderInfo order);
	
	public OrderInfo getBaseOrderDeatailList(Map<String, String> paramMap);
	
	public List<OrderInfo> getOrderInfoList(String baseOrdersn);

}
