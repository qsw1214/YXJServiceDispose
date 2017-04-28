package com.youxianji.facade.orderquery.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;


@InterfaceParam(command="2004",comment="订单流程Bean")
@Scope("prototype")
public class GetOrderprocessinfoRequestBean extends BaseRequest {
	
	private String ordersn;

	public String getOrdersn() {
		return ordersn;
	}

	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
}
