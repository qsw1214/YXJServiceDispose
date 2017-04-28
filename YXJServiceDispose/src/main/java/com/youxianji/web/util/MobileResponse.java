package com.youxianji.web.util;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.net.URLCodec;
import org.apache.log4j.Logger;
import org.test.facade.SignTool;

import base.cn.annotation.Facade;
import base.cn.util.ObjectTools;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.youxianji.util.GsonTools;
import com.youxianji.util.MD5Tools;
import com.youxianji.util.des.DesUtil;

/**
 * 手机应答类
 * */
public class MobileResponse {
	
	/**
	 * 发送错误信息
	 * */
	public static void sendError(HttpServletResponse response,ErrorEnum error){
		sendError(response,error,"UTF-8");
	}
	
	/**
	 * 发送错误信息
	 * */
	public static void sendError(HttpServletResponse response,ErrorEnum error,String charset){
		BaseResponse br = new BaseResponse();
		br.setSuccess("false");
		br.setReturncode(error.getCode());
		br.setReturnmessage(error.getMessage());
		
		//设置签名
		String sign = getReturnSign(br);
		br.setSign(sign);
		
		send(response,br,charset);
	}
	
	/**
	 * 发送错误信息
	 * */
	public static void sendError(HttpServletResponse response,ErrorEnum error,AbsFacade facade){
		BaseResponse br = new BaseResponse();
		br.setSuccess("false");
		br.setReturncode(error.getCode());
		br.setReturnmessage(error.getMessage());
		
		send(response,br,facade);
	}

	/**
	 * 发送对象信息
	 * */
	public static void send(HttpServletResponse response,BaseResponse responseParam){
		send(response,responseParam,"UTF-8");
	}
	
	/**
	 * 发送对象信息
	 * */
	public static void send(HttpServletResponse response,BaseResponse responseParam,AbsFacade facade){
		
		Logger logger = Logger.getLogger(MobileResponse.class);
		//设置签名
		String sign = getReturnSign(responseParam);
		responseParam.setSign(sign);
		
		String str = GsonTools.getJsonString(responseParam);
		String charset = "UTF-8";
		PrintWriter print = null;
		Facade facadeAnnotation = facade.getClass().getAnnotation(Facade.class);
		try{
			logger.info(facadeAnnotation.command()+"-"+facadeAnnotation.comment()+"-返回内容："+str);
			response.setCharacterEncoding(charset);
			response.setContentType("application/json; charset=utf-8");  
			print = response.getWriter();
			print.write(str);
			print.flush();
			
		}catch(Exception e){
			throw new RuntimeException("返回应答信息异常.",e);
		}finally{
			if(print != null){
				print.close();
			}
		}
	}
	

	/**
	 * 发送Jsonp对象信息
	 * */
	public static void sendJsonp(HttpServletRequest request,HttpServletResponse response,BaseResponse responseParam,AbsFacade facade){
		
		Logger logger = Logger.getLogger(MobileResponse.class);
		//设置签名
		String sign = getReturnSign(responseParam);
		responseParam.setSign(sign);
		
		String str = GsonTools.getJsonString(responseParam);
		String charset = "UTF-8";
		PrintWriter print = null;
		Facade facadeAnnotation = facade.getClass().getAnnotation(Facade.class);
		try{
			logger.info(facadeAnnotation.command()+"-"+facadeAnnotation.comment()+"-返回内容："+str);
			response.setCharacterEncoding(charset);
			response.setContentType("application/json; charset=utf-8");  
			print = response.getWriter();
			print.write(str);
			print.flush();
			
		}catch(Exception e){
			throw new RuntimeException("返回应答信息异常.",e);
		}finally{
			if(print != null){
				print.close();
			}
		}
	}
	
	/**
	 * 发送Jsonp错误信息
	 * */
	public static void sendJsonpError(HttpServletRequest request,HttpServletResponse response,ErrorEnum error,AbsFacade facade){
		BaseResponse br = new BaseResponse();
		br.setSuccess("false");
		br.setReturncode(error.getCode());
		br.setReturnmessage(error.getMessage());
		
		sendJsonp(request,response,br,facade);
	}
	
	/**
	 * 发送对象信息
	 * */
	public static void send(HttpServletResponse response,BaseResponse responseParam,String charset){
		String str = GsonTools.getJsonString(responseParam);
		try{
			sendString(response,str,charset);
		}catch(Exception e){
			throw new RuntimeException("返回应答信息异常.",e);
		}
	}
	
	/**
	 * 发送字符串信息
	 * */
	protected static void sendString(HttpServletResponse response,String str,String charset) throws Exception{
		Logger logger = Logger.getLogger(MobileResponse.class);
		PrintWriter print = null;
		try{
			logger.info("返回内容："+str);
			response.setCharacterEncoding(charset);
			print = response.getWriter();
			print.write(str);
			print.flush();
		}finally{
			if(print != null){
				print.close();
			}
		}
	}
	
	/**
	 * 生成返回信息签名
	 */
	private static String getReturnSign(BaseResponse responseParam){
		String sign = null;
		
		//获取值对象的所有属性集合
		Field[] fields;
		try {
			fields = responseParam.getClass().getFields();
		
			Map<String,String> paramMap = new HashMap<String,String>();
			
	
			for(Field f : fields){
				String propertyName = f.getName();
				
				if(!"sign".equals(propertyName)){
				
					Object propertyValue = f.get(responseParam);
					
					String stringValue = "";
					
					Class<?> fieldClazz = f.getType();
					
//					if(fieldClazz.isPrimitive())  continue; //判断是否为基本类型
					
					// 如果是List类型，得到其Generic的类
					if(fieldClazz.isAssignableFrom(List.class)){
						GsonBuilder gsonbuilder = new GsonBuilder().disableHtmlEscaping().serializeNulls();
						Gson gson = gsonbuilder.create();
						stringValue = gson.toJson(propertyValue);
					}else{
						stringValue = propertyValue.toString();
					}
					
					if(!paramMap.containsKey(propertyName)){
						//if(!ObjectTools.isNullByString(stringValue)){
							paramMap.put(propertyName, stringValue);
						//}
					}
				}
			}
	
			String encBeforeStr = SignTool.setEncBefore(paramMap);
		   
			URLCodec codec = new URLCodec();
			encBeforeStr=codec.encode(encBeforeStr);//URL转码-防中文乱码
			String encDes = DesUtil.encrypt(encBeforeStr);
			String replace_encDes = encDes.replaceAll("[\\t\\n\\r]", "");//去掉回车换行符
			sign = MD5Tools.getKeyedDigest(replace_encDes, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sign;
	}
	
	public static String getSignByOs(String os,BaseResponse responseParam){
		String sign = null;
		if("wechat".equals(os)){
			sign = getWechatReturnSign(responseParam);
		}else{
			sign = getReturnSign(responseParam);
		}
		return sign;
	}
	
	
	private static String getWechatReturnSign(BaseResponse responseParam){
		String sign = null;
		
		//获取值对象的所有属性集合
		Field[] fields;
		try {
			fields = responseParam.getClass().getFields();
		
			Map<String,String> paramMap = new HashMap<String,String>();
			
	
			for(Field f : fields){
				String propertyName = f.getName();
				
				if("paramdata".equals(propertyName)){
				
					Object propertyValue = f.get(responseParam);
					
					String stringValue = "";
					
					Class<?> fieldClazz = f.getType();
					
//					if(fieldClazz.isPrimitive())  continue; //判断是否为基本类型
					
					// 如果是List类型，得到其Generic的类
					if(fieldClazz.isAssignableFrom(List.class)){
						Gson gson = new Gson();
						stringValue = gson.toJson(propertyValue);
					}else{
						stringValue = propertyValue.toString();
					}
					
					if(!paramMap.containsKey(propertyName)){
						if(!ObjectTools.isNullByString(stringValue)){
							paramMap.put(propertyName, stringValue);
						}
					}
				}
			}
			
			String encBeforeStr = SignTool.setEncBefore(paramMap);

			URLCodec codec = new URLCodec();
			encBeforeStr=codec.encode(encBeforeStr);//URL转码-防中文乱码
			String encDes = DesUtil.encrypt(encBeforeStr);
			String replace_encDes = encDes.replaceAll("[\\t\\n\\r]", "");//去掉回车换行符
			sign = MD5Tools.getKeyedDigest(replace_encDes, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sign;
	}
	
}
