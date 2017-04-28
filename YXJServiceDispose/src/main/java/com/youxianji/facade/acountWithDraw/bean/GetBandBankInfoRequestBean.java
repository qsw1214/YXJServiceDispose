package com.youxianji.facade.acountWithDraw.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;
@InterfaceParam(command="c002",comment="获取绑定银行卡信息接口")
@Scope("prototype")
public class GetBandBankInfoRequestBean extends BaseRequest {
	
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
