package com.youxianji.facade.orderquery.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="2003",comment="查询用户账单请求参数BEAN")
@Scope("prototype")
public class QueryUserBillsRequestBean extends BaseRequest{

	/* 页号 */
	private String page;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
}
