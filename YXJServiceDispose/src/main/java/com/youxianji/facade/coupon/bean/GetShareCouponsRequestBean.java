package com.youxianji.facade.coupon.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="h001",comment="获取分享红包记录")
@Scope("prototype")
public class GetShareCouponsRequestBean extends BaseRequest{
	
	/* 总订单号 */
	private String baseordersn;

	public String getBaseordersn() {
		return baseordersn;
	}

	public void setBaseordersn(String baseordersn) {
		this.baseordersn = baseordersn;
	}
}
