package com.youxianji.facade.system.util;

import java.util.Date;
import java.util.Random;

public class FindPassTool {
	
	private final static long EffectiveLength =  1000L * 60 * 15;//验证码有效时长15分钟

	/**
	 * 验证码信息是否失效
	 * @return	true 有效
	 * 			false 失效
	 * */
	public static boolean isVerfycodInvalid(Date createDate){
		Long createTime = createDate.getTime();
		if(System.currentTimeMillis() < createTime + EffectiveLength){
			return true;
		}
		return false;
	}

	/**
	 * 获取验证码
	 * */
	public static String getVerfycode(){
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		String str = "";
		do{
			str = String.valueOf(random.nextLong());
		}while(str.length() < 6);
		
		return str.substring(str.length() - 6, str.length());
	}
}
