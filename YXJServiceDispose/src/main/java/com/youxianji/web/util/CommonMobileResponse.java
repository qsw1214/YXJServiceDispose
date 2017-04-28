package com.youxianji.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseResponse;

/**
 * 手机应答类
 * */
public class CommonMobileResponse {
	
	private static Logger logger = Logger.getLogger(CommonMobileResponse.class);
	

	public static void send(HttpServletRequest request,HttpServletResponse response
			,BaseResponse responseParam,AbsFacade facade,String os){
		logger.info("=====os:"+os);
		if("wechat".equals(os)){
			MobileResponse.sendJsonp(request,response,responseParam,facade);
		}else{
			MobileResponse.send(response,responseParam,facade);
	    }

	}
	
	
	public static void sendError(HttpServletRequest request,HttpServletResponse response,ErrorEnum error,AbsFacade facade,String os){
		logger.info("=====os:"+os);
		if("wechat".equals(os)){
			MobileResponse.sendJsonpError(request,response,error,facade);
		}else{
			MobileResponse.sendError(response,error);
	    }

	}
	
	
	
}
