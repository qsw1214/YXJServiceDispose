package com.youxianji.facade.yxjia.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="6002",comment="JIA-线下收款订单查看BEAN")
@Scope("prototype")
public class JIAOfflineChargeBillsRequestBean extends BaseRequest{

	/* 页号 */
	private String page;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
}
