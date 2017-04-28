package com.youxianji.facade.trade.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="4011",comment="再次购买")
public class BuyAgainRequestBean extends BaseRequest{
	
	private String ordersn;//	String	订单号	

	public String getOrdersn() {
		return ordersn;
	}

	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	
	
	
	
	
}
