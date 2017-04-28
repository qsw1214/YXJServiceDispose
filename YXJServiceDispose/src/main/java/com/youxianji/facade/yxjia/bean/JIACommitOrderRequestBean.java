package com.youxianji.facade.yxjia.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="8004",comment="JIA-提交订单")
public class JIACommitOrderRequestBean extends BaseRequest{
	
	private String ordersn;//	String	订单号
	private String addressid;//	String	收货地址信息ID
	private String couponid;//	String	代金券ID
	private String orderremark;//	String	订单备注
	private String distributiontime;//配送时间段
	private String paytype;// String 支付方式
	private String channel;//请求渠道 B.商家端
	
	
	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getDistributiontime() {
		return distributiontime;
	}
	public void setDistributiontime(String distributiontime) {
		this.distributiontime = distributiontime;
	}
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	public String getAddressid() {
		return addressid;
	}
	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}
	public String getCouponid() {
		return couponid;
	}
	public void setCouponid(String couponid) {
		this.couponid = couponid;
	}
	public String getOrderremark() {
		return orderremark;
	}
	public void setOrderremark(String orderremark) {
		this.orderremark = orderremark;
	}
	
	
	
	
}
