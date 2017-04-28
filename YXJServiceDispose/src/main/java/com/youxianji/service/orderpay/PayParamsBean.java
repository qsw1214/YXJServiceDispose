package com.youxianji.service.orderpay;

import com.youxianji.pojo.BaseOrderInfo;

public class PayParamsBean {
	private BaseOrderInfo baseOrder;
	private String openid;
	private String imei;
	private String ip;
	private String payPass;
	
	
	
	
	
	public String getPayPass() {
		return payPass;
	}
	public void setPayPass(String payPass) {
		this.payPass = payPass;
	}
	public BaseOrderInfo getBaseOrder() {
		return baseOrder;
	}
	public void setBaseOrder(BaseOrderInfo baseOrder) {
		this.baseOrder = baseOrder;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	
}
