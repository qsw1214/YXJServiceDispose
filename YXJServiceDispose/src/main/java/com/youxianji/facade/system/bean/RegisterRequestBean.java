package com.youxianji.facade.system.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="1001",comment="注册请求参数BEAN")
public class RegisterRequestBean extends BaseRequest{

private String verifycode;//请求验证码

public String getVerifycode() {
	return verifycode;
}
public void setVerifycode(String verifycode) {
	this.verifycode = verifycode;
}

	
}
