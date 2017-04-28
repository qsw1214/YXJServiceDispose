package com.youxianji.facade.system.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;
@InterfaceParam(command="1014",comment="自动登录请求参数BEAN")
public class MemberCardRequestBean  extends BaseRequest {
	private String cardtype;//B.店铺用户充值产品 C.微信商城用户充值产品

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	
	
	
	
	
	

}
