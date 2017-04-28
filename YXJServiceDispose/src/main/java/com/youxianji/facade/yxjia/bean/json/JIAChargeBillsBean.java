package com.youxianji.facade.yxjia.bean.json;

public class JIAChargeBillsBean {

	private String ordersn;	//String	订单号	
	private String gathermoney;//	String	收款金额	
	private String gathertime;//	String	收款时间	
	private String paytelephone;//	String	付款手机号	
	private String orderstate;//	String	订单状态	订单状态 1.成功
	private String remark;//	String	订单备注	
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	public String getGathermoney() {
		return gathermoney;
	}
	public void setGathermoney(String gathermoney) {
		this.gathermoney = gathermoney;
	}
	public String getGathertime() {
		return gathertime;
	}
	public void setGathertime(String gathertime) {
		this.gathertime = gathertime;
	}
	public String getPaytelephone() {
		return paytelephone;
	}
	public void setPaytelephone(String paytelephone) {
		this.paytelephone = paytelephone;
	}
	public String getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
