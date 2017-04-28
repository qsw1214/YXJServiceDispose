package com.youxianji.dao;

import java.util.List;
import java.util.Map;

import com.youxianji.pojo.OrderDetail;


public interface IOrderDetailDAO {
	
     public void save(OrderDetail orderDetail);
     
     public OrderDetail getById(String id);
     
     public List<OrderDetail> getDetailListByOrderSn(String orderSn);

     public String getMonthSellCountByProd(Map<String,String> map);
     
     
     public int doSwitchOrderDetailPrice(OrderDetail orderDetail);
     
     public List<OrderDetail> getByProdId(String orderSn,String prodId);
     
     public void delete(String id);
     
     public List<OrderDetail> getOrderDetailByCondition(Map<String,String> map);
}
