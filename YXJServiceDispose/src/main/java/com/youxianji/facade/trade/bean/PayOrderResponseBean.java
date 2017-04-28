package com.youxianji.facade.trade.bean;

import com.youxianji.facade.trade.bean.json.AliPayInfoBean;
import com.youxianji.facade.trade.bean.json.WechatPayInfoBean;


public class PayOrderResponseBean{

	private WechatPayInfoBean wechatpay;
	
	private AliPayInfoBean alipay;
	
	private String ordersn;

	public WechatPayInfoBean getWechatpay() {
		return wechatpay;
	}

	public void setWechatpay(WechatPayInfoBean wechatpay) {
		this.wechatpay = wechatpay;
	}

	public AliPayInfoBean getAlipay() {
		return alipay;
	}

	public void setAlipay(AliPayInfoBean alipay) {
		this.alipay = alipay;
	}

	public String getOrdersn() {
		return ordersn;
	}

	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	

	
}
