package com.youxianji.facade.trade.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="4009",comment="根据支付方式切换价格")
public class SwitchPayTypeRequestBean extends BaseRequest{
	
	private String ordersn;//	String	订单号	
	private String paytype;//	String	支付方式	1.微信公众号支付 2.支付宝支付  3.余额支付 4微信APP支付
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
	
	
}
