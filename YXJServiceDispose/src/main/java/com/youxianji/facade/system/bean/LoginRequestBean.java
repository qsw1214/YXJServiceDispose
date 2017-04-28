package com.youxianji.facade.system.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="1002",comment="登录请求参数BEAN")
public class LoginRequestBean extends BaseRequest{

private String verifycode;//请求验证码
private String openid;
private String sendchannel;


public String getSendchannel() {
	return sendchannel;
}
public void setSendchannel(String sendchannel) {
	this.sendchannel = sendchannel;
}
public String getVerifycode() {
	return verifycode;
}
public void setVerifycode(String verifycode) {
	this.verifycode = verifycode;
}
public String getOpenid() {
	return openid;
}
public void setOpenid(String openid) {
	this.openid = openid;
}





	
}
