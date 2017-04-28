package com.youxianji.util;


import base.cn.web.facade.bean.BaseResponse;
import base.cn.web.facade.bean.PublicBean;

import com.google.gson.Gson;

/**
 * Gson工具类
 * */
public class GsonTools {

	/**
	 * 将一个对象转换成json字符串
	 * */
	public static String getJsonString(Object obj){
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
	
	/**
	 * 将一个json字符串转化成一个对象
	 * @param <T>
	 * @return 
	 * */
	public static <T> T getSignObject(String jsonString,Class <T>clazz){
		Gson gson = new Gson();
		return gson.fromJson(jsonString, clazz);
	}
	
	public static void main(String []args){
		BaseResponse br = new BaseResponse();
		br.setSuccess("false");
		br.setReturncode("91");
		br.setReturnmessage("系统异常");
		System.out.println(getJsonString(br));
		
		PublicBean pb = new PublicBean();
		pb.setCommand("1001");
		pb.setIp("192.168.1.202");
		pb.setImei("098989932swe23");
		pb.setOs("android");
		pb.setOs_version("4.1.2 JZO54K");
		pb.setSign("sielwjioi2234");
		pb.setTime_stamp("201402191626");
		String gsonString = getJsonString(pb);
		System.out.println(gsonString);
		
		PublicBean pb1 = (PublicBean)getSignObject(gsonString,PublicBean.class);
		System.out.println(pb1.getCommand() + ";" + pb1.getIp());
	}
}
