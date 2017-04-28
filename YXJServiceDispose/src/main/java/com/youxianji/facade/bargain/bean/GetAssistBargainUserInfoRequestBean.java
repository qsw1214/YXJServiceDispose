package com.youxianji.facade.bargain.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="k003",comment="获取协助砍价人信息列表")
@Scope("prototype")
public class GetAssistBargainUserInfoRequestBean extends BaseRequest{

	private String userid;//用户ID
	private String telephone;//手机号
	private String bargainid;//发起砍价记录ID
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
	public String getBargainid() {
		return bargainid;
	}
	public void setBargainid(String bargainid) {
		this.bargainid = bargainid;
	}
	
}
