package com.youxianji.facade.system.bean;

public class QueryUserInfoResponseBean {

	/* 用户ID */
	private String userId;
	
	/* 账户余额 */
	private String amount;
	
	/* 代金券数量 */
	private String couponcount;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
