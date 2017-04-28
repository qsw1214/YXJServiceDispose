package com.youxianji.facade.system.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;
@InterfaceParam(command="1001",comment="自动登录请求参数BEAN")
public class AutoLoginRequestBean extends BaseRequest {
	
	private String openid;
	private String sendchannel;
	
	 
	public String getSendchannel() {
		return sendchannel;
	}
	public void setSendchannel(String sendchannel) {
		this.sendchannel = sendchannel;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}
