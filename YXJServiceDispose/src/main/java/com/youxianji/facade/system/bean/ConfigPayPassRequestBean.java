package com.youxianji.facade.system.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;
@InterfaceParam(command="1004",comment="设置支付密码请求参数BEAN")
public class ConfigPayPassRequestBean extends BaseRequest{

	/* 新支付密码 */
	private String password;
	/* 手机号码 */
	private String telephone;
	/* 手机验证码 */
	private String verifycode;
	/* 用户id*/
	private String userid;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}
