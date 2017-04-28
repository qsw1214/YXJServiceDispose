package com.youxianji.network.sms.vo;

/**
 * 短信请求抽象基类
 * */
public abstract class AbsSmsRequestVO {

	private String mobiles;//接收电话号码
	private String content;//内容
	private String sendType = "0";//短信发送方式(0:自动)
	private String userName="九一数卡";
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMobiles() {
		return mobiles;
	}
	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}
	public String getSendType() {
		return sendType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	
}
