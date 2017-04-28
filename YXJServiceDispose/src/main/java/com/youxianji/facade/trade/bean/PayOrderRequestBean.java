package com.youxianji.facade.trade.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="4005",comment="在线支付订单")
public class PayOrderRequestBean extends BaseRequest{
	
	private String ordersn;//	String	订单号	
	private String paytype;//	String	支付方式	1.微信支付 2.支付宝支付 3.余额支付
	private String paypass;//	String	支付密码	余额支付时不能为空
	
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getPaypass() {
		return paypass;
	}
	public void setPaypass(String paypass) {
		this.paypass = paypass;
	}
	
	
	
}
