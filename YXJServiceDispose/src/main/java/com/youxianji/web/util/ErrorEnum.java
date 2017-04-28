package com.youxianji.web.util;

/**
 * 异常信息枚举类
 * */
public enum ErrorEnum {

	SUCCESS("0000","成功"),
	FAIL_0001("0001","交易失败"),
	FAIL_1000("1000","用户信息验证错误"),
	FAIL_1001("1001","用户ID异常"),
	FAIL_1002("1002","参数不合法"),
	FAIL_1003("1003","查询信息不存在"),
	FAIL_1004("1004","登录密码错误"),
	FAIL_1005("1005","支付密码错误"),
	FAIL_1006("1006","用户状态异常"),
	FAIL_1007("1007","手机号被占用"),
	FAIL_1008("1008","登录密码不合法"),
	FAIL_1009("1009","手机号不合法"),
	FAIL_1010("1010","验证码失效"),
	FAIL_1011("1011","验证码不正确"),
	FAIL_1012("1012","没有可用代金券进行充值"),
	
	FAIL_9001("9001","public信息不存在"),
	FAIL_9002("9002","sign签名不正确"),
	FAIL_9003("9003","command关键字错误"),
	FAIL_9004("9004","用户已冻结"),
	FAIL_9009("9009","未获取到信息，请重试"),
	FAIL_9998("9998","该功能需要升级最新版本后才能继续使用"),
	FAIL_9999("9999","服务异常"),
	
	
	FAIL_5555("5555","自定义异常");
	
	private String code;
	private String message;
	
	private ErrorEnum(String code,String message){
		this.code = code;
		this.message = message;
	}
	
	public String getCode(){
		return code;
	}
	public String getMessage(){
		return message;
	}
	public void changetMessage(String message){
		this.message = message;
	}
}
