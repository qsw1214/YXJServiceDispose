package com.youxianji.facade.coupon.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="h003",comment="获取领取红包列表记录")
@Scope("prototype")
public class GetReceiveCouponListRequestBean extends BaseRequest{
	
	/* 红包ID */
	private String shareid;

	public String getShareid() {
		return shareid;
	}

	public void setShareid(String shareid) {
		this.shareid = shareid;
	}
}
