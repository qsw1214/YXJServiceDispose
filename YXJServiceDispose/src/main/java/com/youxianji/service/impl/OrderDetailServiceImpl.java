package com.youxianji.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IOrderDetailDAO;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.service.IOrderDetailService;

@Service("orderDetail")
public class OrderDetailServiceImpl implements IOrderDetailService {
	
	@Resource
	private IOrderDetailDAO orderDetailDAO;

	@Override
	public List<OrderDetail> getOrderDetailByOrdersn(String ordersn) {
		// TODO Auto-generated method stub
		return orderDetailDAO.getDetailListByOrderSn(ordersn);
	}

	@Override
	public String getMonthSellCountByProd(Map<String, String> map) {
		// TODO Auto-generated method stub
		return orderDetailDAO.getMonthSellCountByProd(map);
	}

	@Override
	public List<OrderDetail> getByOrdersnAndProdId(String orderSn, String prodId) {
		
		return orderDetailDAO.getByProdId(orderSn, prodId);
	}
	
}
