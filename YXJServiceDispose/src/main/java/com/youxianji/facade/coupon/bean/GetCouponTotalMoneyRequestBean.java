package com.youxianji.facade.coupon.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="h004",comment="获取指定红包发放类型总金额")
@Scope("prototype")
public class GetCouponTotalMoneyRequestBean extends BaseRequest{
	
	/* 红包发放类型 1.注册发放 2.订单分享 3.客服发放 4.批量发放专享红包 */
	private String configflag;

	public String getConfigflag() {
		return configflag;
	}

	public void setConfigflag(String configflag) {
		this.configflag = configflag;
	}

	
}
