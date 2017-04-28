package com.youxianji.facade.yxjia.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="6001",comment="JIA-进货订单查看")
@Scope("prototype")
public class JIAQueryOrderListRequestBean extends BaseRequest{

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
