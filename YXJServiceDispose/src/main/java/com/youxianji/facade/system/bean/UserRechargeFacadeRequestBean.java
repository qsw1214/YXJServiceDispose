package com.youxianji.facade.system.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;
@InterfaceParam(command="1011",comment="用户充值请求参数BEAN")
public class UserRechargeFacadeRequestBean extends BaseRequest {
	
	private String paytype;
	private String discountid;
	private String normalcharge;
	private String employnum;
	
	
	
	
	public String getEmploynum() {
		return employnum;
	}
	public void setEmploynum(String employnum) {
		this.employnum = employnum;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getDiscountid() {
		return discountid;
	}
	public void setDiscountid(String discountid) {
		this.discountid = discountid;
	}
	public String getNormalcharge() {
		return normalcharge;
	}
	public void setNormalcharge(String normalcharge) {
		this.normalcharge = normalcharge;
	}
	
	
}
