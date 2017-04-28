package com.youxianji.facade.acountWithDraw.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;
@InterfaceParam(command="c006",comment="申请提现接口")
@Scope("prototype")
public class ApplyWithDrawRequestBean extends BaseRequest {
	
	private String userid;
	private String telephone;
	private String cashamount;
	private String paypass;
	
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
	public String getCashamount() {
		return cashamount;
	}
	public void setCashamount(String cashamount) {
		this.cashamount = cashamount;
	}
	public String getPaypass() {
		return paypass;
	}
	public void setPaypass(String paypass) {
		this.paypass = paypass;
	}
	
	
}
