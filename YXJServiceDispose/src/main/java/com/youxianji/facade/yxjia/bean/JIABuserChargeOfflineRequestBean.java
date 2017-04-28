package com.youxianji.facade.yxjia.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="8005",comment="JIA-商家线下收款BEAN")
public class JIABuserChargeOfflineRequestBean extends BaseRequest{
	
	private String paymoney;//	String	付款金额
	private String gatheruserid;//	String	收款方用户ID
	private String paypass;//	String	支付密码
	public String getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}
	public String getGatheruserid() {
		return gatheruserid;
	}
	public void setGatheruserid(String gatheruserid) {
		this.gatheruserid = gatheruserid;
	}
	public String getPaypass() {
		return paypass;
	}
	public void setPaypass(String paypass) {
		this.paypass = paypass;
	}
	
	
	


	
	
	
	
	
}
