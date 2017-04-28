package com.youxianji.facade.orderquery.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;
@InterfaceParam(command="2006",comment="总订单详情")
@Scope("prototype")
public class GetBaseOrderDetailRequestBean extends BaseRequest {
	
	private String userid;
	private String telephone;
	private String baseordersn;
	
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
	public String getBaseordersn() {
		return baseordersn;
	}
	public void setBaseordersn(String baseordersn) {
		this.baseordersn = baseordersn;
	}

}
