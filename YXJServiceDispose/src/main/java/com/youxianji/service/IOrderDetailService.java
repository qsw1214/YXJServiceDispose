package com.youxianji.service;

import java.util.List;
import java.util.Map;

import com.youxianji.pojo.OrderDetail;

public interface IOrderDetailService {

	 public List<OrderDetail> getOrderDetailByOrdersn(String ordersn);
	
	 public String getMonthSellCountByProd(Map<String,String> map);
	 
	 public  List<OrderDetail> getByOrdersnAndProdId(String orderSn,String prodId);
}
