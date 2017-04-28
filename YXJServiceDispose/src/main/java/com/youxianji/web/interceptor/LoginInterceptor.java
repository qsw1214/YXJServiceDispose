package com.youxianji.web.interceptor;

import static com.youxianji.web.util.MobileResponse.sendError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.youxianji.pojo.UserInfo;
import com.youxianji.service.IUserInfoService;
import com.youxianji.web.util.ErrorEnum;

import base.cn.util.ObjectTools;
import base.cn.web.AppContextManager;
import base.cn.web.facade.bean.BaseRequest;


/**
 * 登录检查拦截器
 * <p>作用:检查手机号、密码是否正确</p>
 * */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private AppContextManager appManager = AppContextManager.instance();
	
	//不要做登录检查的接口命令字(多个命令字用','分隔)
	private String ignoreCommands;
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if (handler instanceof HandlerMethod) {
			BaseRequest baseRequest = getBaseRequest(request,response);
			String command = baseRequest.getPublicBean().getCommand();
			//如果是被忽略的接口直接执行
			if(isIgnoreCommnad(command)){
				return true;
			}
			//不是被忽略的接口需要进行登录检查
			try{
				IUserInfoService userInfoService = (IUserInfoService)appManager.getBean("userInfoService");
				UserInfo userInfo = userInfoService.validateUser(baseRequest.getUserid(), baseRequest.getPublicBean().getImei());
				if(ObjectTools.isNullByObject(userInfo)){
					sendError(response,ErrorEnum.FAIL_1000);
					logger.error("接口"+command+"手机号:" + baseRequest.getTelephone() + ", 用户ID："+ baseRequest.getUserid()+",设备号："+baseRequest.getPublicBean().getImei()+","+ ErrorEnum.FAIL_1000.getMessage());
					return false;
				}
				
			}catch(Exception e){
				sendError(response,ErrorEnum.FAIL_9999);
				logger.error("登录拦截器异常.", e);
				return false;
			}
			return true;

		}else{
			return super.preHandle(request, response, handler);
		}
	}

	/*------------------------------------------------------------------------------------*/
	
		/**
		 * 获取请求bean
		 * */
		private BaseRequest getBaseRequest(HttpServletRequest request,
				HttpServletResponse response){
			return (BaseRequest)getTheWebRequest(request,response).getAttribute("baseRequest", ServletWebRequest.SCOPE_REQUEST);
		}
		
		private ServletWebRequest getTheWebRequest(HttpServletRequest request,
				HttpServletResponse response){
			
			return new ServletWebRequest(request,response);
		}
	
	/**
	 * 判断是否是被忽略的关键字
	 * */
	private boolean isIgnoreCommnad(String command){
		if(ObjectTools.isNullByString(ignoreCommands)){
			return false;
		}
		String []ignoreCommand = ignoreCommands.split(",");
		for(String ignore : ignoreCommand){
			if(ignore.equals(command)){
				return true;
			}
		}
		return false;
	}
	
	public String getIgnoreCommands() {
		return ignoreCommands;
	}

	public void setIgnoreCommands(String ignoreCommands) {
		this.ignoreCommands = ignoreCommands;
	}
}
