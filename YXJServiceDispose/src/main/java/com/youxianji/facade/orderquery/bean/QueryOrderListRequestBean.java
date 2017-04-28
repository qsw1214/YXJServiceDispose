package com.youxianji.facade.orderquery.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="2001",comment="查询用户订单业务请求参数BEAN")
@Scope("prototype")
public class QueryOrderListRequestBean extends BaseRequest{

	/* 页号 */
	private String page;
	
	private String status;	//String	查询状态	0.全部订单1.待付款，2.待发货，3.配送中，4.退单售后

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
}
