package com.youxianji.facade.acountWithDraw.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;
@InterfaceParam(command="c005",comment="获取手续费接口")
@Scope("prototype")
public class GetPoundAgeRequestBean extends BaseRequest {
	
	private String userid;
	private String telephone;
	private String cashamount;//申请提现额度
	
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
	public String getCashamount() {
		return cashamount;
	}
	public void setCashamount(String cashamount) {
		this.cashamount = cashamount;
	}
	
	
}
