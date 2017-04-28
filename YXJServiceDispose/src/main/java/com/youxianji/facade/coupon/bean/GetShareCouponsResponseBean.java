package com.youxianji.facade.coupon.bean;


public class GetShareCouponsResponseBean{

	/* 是否存在红包 0.不存在 1.存在 */
	private String existsflag;
	
	/* 红包ID */
	private String shareid;
	
	/* 红包总金额 */
	private String totalMoney;

	public String getExistsflag() {
		return existsflag;
	}

	public void setExistsflag(String existsflag) {
		this.existsflag = existsflag;
	}

	public String getShareid() {
		return shareid;
	}

	public void setShareid(String shareid) {
		this.shareid = shareid;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	
}
