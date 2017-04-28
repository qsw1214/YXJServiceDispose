package com.youxianji.facade.bargain.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="k001",comment="获取用户发起砍价信息")
@Scope("prototype")
public class GetUserLaunchBargainInfoRequestBean extends BaseRequest{

	private String userid;//用户ID
	private String telephone;//手机号
	private String openid;//微信唯一标识
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
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
