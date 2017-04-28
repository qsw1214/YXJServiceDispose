package com.youxianji.facade.orderquery.bean;

import java.util.List;

import com.youxianji.facade.orderquery.bean.jsonbean.OrderProcessBean;

public class GetOrderprocessinfoResponseBean {
	public List<OrderProcessBean> orderprocesslist;

	public List<OrderProcessBean> getOrderprocesslist() {
		return orderprocesslist;
	}

	public void setOrderprocesslist(List<OrderProcessBean> orderprocesslist) {
		this.orderprocesslist = orderprocesslist;
	}
	
	

	
}
