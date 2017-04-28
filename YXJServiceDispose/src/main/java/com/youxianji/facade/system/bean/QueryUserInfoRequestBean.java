package com.youxianji.facade.system.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="1006",comment="获取用户信息请求参数BEAN")
public class QueryUserInfoRequestBean extends BaseRequest{
	
	private String userid;
	private String amount;
	private String couponcount;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCouponcount() {
		return couponcount;
	}
	public void setCouponcount(String couponcount) {
		this.couponcount = couponcount;
	}
	
	
}
