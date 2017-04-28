package com.youxianji.util.bill;

public class ReturnMsg {
	/* 返回码 */
	private String returnCode;
	
	/* 返回消息 */
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
}
