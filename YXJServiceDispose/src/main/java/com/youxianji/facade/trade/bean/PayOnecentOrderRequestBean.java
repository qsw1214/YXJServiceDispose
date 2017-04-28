package com.youxianji.facade.trade.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="4008",comment="一分购在线支付")
public class PayOnecentOrderRequestBean extends BaseRequest{
	
	private String prodid;//	String	订单号	
	private String paytype;//	String	支付方式	1.微信支付 2.支付宝支付 3.余额支付
	private String invitecode;
	
	
	
	public String getInvitecode() {
		return invitecode;
	}
	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	
	
	
	
	
}
