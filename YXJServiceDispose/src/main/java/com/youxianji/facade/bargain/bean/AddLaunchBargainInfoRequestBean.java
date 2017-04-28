package com.youxianji.facade.bargain.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="k004",comment="生成发起砍价记录")
@Scope("prototype")
public class AddLaunchBargainInfoRequestBean extends BaseRequest{

	private String userid;//	用户ID
	private String telephone;//用户手机号
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
