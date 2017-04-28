package com.youxianji.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import base.cn.web.mybatis.plugin.PageInterceptor;
import base.cn.web.mybatis.util.PageInfo;

import com.youxianji.dao.IOrderInfoDAO;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.service.IOrderInfoService;
import com.youxianji.util.Constants;

@Service("orderInfoService")
public class OrderInfoServiceImpl implements IOrderInfoService{
	
	@Resource
	private IOrderInfoDAO orderInfoDAO;

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<OrderInfo> getPageOrderList(Map<String, String> paramMap) {
		PageInterceptor.startPage(Integer.parseInt(paramMap.get("page")));
		orderInfoDAO.getOrderList(paramMap);
		
		return PageInterceptor.endPage();
	}

	@Override
	public OrderInfo getByOrderSn(String orderSn) {
		return orderInfoDAO.getByOrderSn(orderSn);
	}

	@Override
	public void update(OrderInfo order) {
		orderInfoDAO.update(order);
	}

	@Override
	public void doConfirmOrder(OrderInfo order) {
		order.setOrderstate(Constants.ORDER_ARRIVED);
		order.setReceivetime(new Date());
		orderInfoDAO.update(order);
		
		orderInfoDAO.udpateLogisticsState("5", order.getOrdersn());
	}

	@Override
	public List<OrderInfo> getBarginInfoList(String userid) {
		return orderInfoDAO.getBarginInfoList(userid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<OrderInfo> getPageWaitPayOrderList(Map<String, String> paramsMap) {
		PageInterceptor.startPage(Integer.parseInt(paramsMap.get("page")));
		orderInfoDAO.getOrderList(paramsMap);
		return PageInterceptor.endPage();
	}

	@Override
	public OrderInfo getPageBaseOrderDetailList(Map<String, String> paramMap) {
		
		return orderInfoDAO.getBaseOrderDeatailList(paramMap);
	}

	@Override
	public List<OrderInfo> getOrderInfoList(String baseOrdersn) {
		return orderInfoDAO.getOrderInfoList(baseOrdersn);
	}

}
