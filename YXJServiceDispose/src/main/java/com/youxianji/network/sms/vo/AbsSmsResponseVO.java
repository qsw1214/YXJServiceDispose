package com.youxianji.network.sms.vo;

/**
 * 短信应答抽象基类
 * */
public abstract class AbsSmsResponseVO {

	private String returnCode;//返回码

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
}
