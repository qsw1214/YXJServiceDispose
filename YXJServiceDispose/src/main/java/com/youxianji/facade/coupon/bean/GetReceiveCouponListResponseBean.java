package com.youxianji.facade.coupon.bean;

import java.util.List;

import com.youxianji.facade.coupon.bean.json.ReceiveCouponBean;

public class GetReceiveCouponListResponseBean {

	private List<ReceiveCouponBean> receivecouponlist;

	public List<ReceiveCouponBean> getReceivecouponlist() {
		return receivecouponlist;
	}

	public void setReceivecouponlist(List<ReceiveCouponBean> receivecouponlist) {
		this.receivecouponlist = receivecouponlist;
	}
	
	
}
