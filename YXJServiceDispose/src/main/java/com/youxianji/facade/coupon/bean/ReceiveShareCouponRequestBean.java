package com.youxianji.facade.coupon.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="h002",comment="领取分享红包")
@Scope("prototype")
public class ReceiveShareCouponRequestBean extends BaseRequest{

	/* 红包ID */
	private String shareid;

	public String getShareid() {
		return shareid;
	}

	public void setShareid(String shareid) {
		this.shareid = shareid;
	}
	
}
