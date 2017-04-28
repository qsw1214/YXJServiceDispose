package com.youxianji.facade.acountWithDraw.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;
@InterfaceParam(command="c001",comment="获取可提现金额接口")
@Scope("prototype")
public class GetWithDrawMoneyRequestBean extends BaseRequest {

	private String userid;
	private String telephone;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
}
