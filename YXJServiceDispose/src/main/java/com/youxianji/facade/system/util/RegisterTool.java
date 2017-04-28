package com.youxianji.facade.system.util;

import base.cn.util.ObjectTools;

public class RegisterTool {
	
	/**
	 * 检查手机号是否合法
	 * */
	public static boolean checkTelephone(String telephone){
		if(ObjectTools.isNullByString(telephone)){
			return false;
		}
		//判断是否是1开头
		if(!telephone.startsWith("1")){
			return false;
		}
		//判断是否是11位数字
		/*if(!isENum(telephone)){
			return false;
		}*/
		return true;
	}

	/**
	 * 检查密码是否合法
	 * */
	public static boolean checkPassword(String pwd){
		//判断是否为空
		if(ObjectTools.isNullByString(pwd)){
			return false;
		};
		/*密码加密由手机端完成,服务端不需要进行密码规则验证*/
		return true;
	}
	
	/**
	 * 检查支付密码是否合法
	 * */
	public static boolean checkPaypwd(String paypwd){
		//判断是否为空
		if(ObjectTools.isNullByString(paypwd)){
			return false;
		};
		/*密码加密由手机端完成,服务端不需要进行密码规则验证*/
		return true;
	}
	
	/**
	 * 判断是否是11位数字
	 * */
/*	private static boolean isENum(String value){
		String tmp = "[\\d]{11}";
		return matcherMethod(tmp,value);
	}*/
}
