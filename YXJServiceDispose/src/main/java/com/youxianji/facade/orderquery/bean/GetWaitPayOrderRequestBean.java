package com.youxianji.facade.orderquery.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;
@InterfaceParam(command="2005",comment="待付款订单列表")
@Scope("prototype")
public class GetWaitPayOrderRequestBean extends BaseRequest {
	
	private String userid;//用户id
	private String telephone;//手机号
	/* 页号 */
	private String page;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
}
