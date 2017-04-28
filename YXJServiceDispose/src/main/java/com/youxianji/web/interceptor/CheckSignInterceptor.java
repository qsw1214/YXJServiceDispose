package com.youxianji.web.interceptor;

import static base.cn.web.facade.bean.ObjMapTools.getValue;
import static com.youxianji.web.util.MobileResponse.sendError;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.youxianji.util.GsonTools;
import com.youxianji.util.MD5Tools;

import base.cn.util.ObjectTools;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.PublicBean;
import com.youxianji.web.util.ErrorEnum;

/**
 * 签名校验拦截器
 * */
public class CheckSignInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	//不需要做校验的接口命令字(多个命令字用','分隔)
	private String ignoreCommands;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if (handler instanceof HandlerMethod) {
			try{
				BaseRequest baseRequest = getBaseRequest(request, response);
				String command = baseRequest.getPublicBean().getCommand();
				//获取请求参数集合
				Map<String,Object> parameters = getParameters(request, response);
				if(!parameters.containsKey("public")){
					sendError(response,ErrorEnum.FAIL_9001);
					logger.error(ErrorEnum.FAIL_9001.getMessage());
					return false;
				}
				//获取公共参数对象
				PublicBean pb = baseRequest.getPublicBean();
				
				logger.info("============================================");
				logger.info("公共参数部分：");
				logger.info("接口命令："+ pb.getCommand());
				logger.info("手机IP地址："+ baseRequest.getPublicBean().getIp());
				logger.info("手机imei编码："+ pb.getImei());
				logger.info("手机操作系统："+ pb.getOs());
				logger.info("操作系统版本号："+ pb.getOs_version());
				logger.info("应用版本号："+ pb.getApp_version());
				logger.info("签名串："+ pb.getSign());
				logger.info("时间戳："+ pb.getTime_stamp());
				
//				如果是被忽略的接口直接执行
				if(isIgnoreCommnad(command)){
					return true;
				}
				

				//生成sign签名
				String sign = getCheckSign(parameters);
				logger.info("*********************************************");
				logger.info("系统生成签名串: "+sign);
				if(!sign.equals(pb.getSign())){
					sendError(response,ErrorEnum.FAIL_9002);
					logger.error(ErrorEnum.FAIL_9002.getMessage());
					return false;
				}
				
				//将新对象填入值堆栈中
				ServletWebRequest webRequest = getTheWebRequest(request,response);
				setTheWebRequest(webRequest,"publicBean", pb);
			}catch(Exception e){
				logger.error("CheckSignInterceptor异常!", e);
				sendError(response,ErrorEnum.FAIL_9002);
				return false;
			}
			
			return true;
		}else{
			return super.preHandle(request, response, handler);
		}
	}
	
	/**
	 * 获取请求bean
	 * */
	private BaseRequest getBaseRequest(HttpServletRequest request,
			HttpServletResponse response){
		return (BaseRequest)getTheWebRequest(request,response).getAttribute("baseRequest", ServletWebRequest.SCOPE_REQUEST);
	}
	
	/*private HttpServletRequest getRequest(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		return request;
	}
	
	private HttpServletResponse getResponse(){
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse(); 
		return response;
	}*/
	
	private ServletWebRequest setTheWebRequest(ServletWebRequest servletWebRequest,String key,Object object){
		servletWebRequest.setAttribute(key, object, ServletWebRequest.SCOPE_REQUEST);
		return servletWebRequest;
	}
	
	private ServletWebRequest getTheWebRequest(HttpServletRequest request,
			HttpServletResponse response){
		
		return new ServletWebRequest(request,response);
	}
	
	/**
	 * 获取请求的参数集合
	 * */
	@SuppressWarnings("unchecked")
	private Map<String,Object> getParameters(HttpServletRequest request,
			HttpServletResponse response){
		return (Map<String,Object>)getTheWebRequest(request,response).getAttribute("parameters", ServletWebRequest.SCOPE_REQUEST);
	}
	
	/**
	 * 获取PublicBean对象
	 * */
	private PublicBean getPublicBean(Map<String,Object> parameters){
		String jsonString = ((String)parameters.get("public"));
		return (PublicBean)GsonTools.getSignObject(jsonString, PublicBean.class);
	}
	
	/**
	 * 重新生成签名串
	 * */
	public String getCheckSign(Map<String,Object> parameters){
		//从参数集合中取出key的数组并排序
		String []keys = parameters.keySet().toArray(new String[]{});
		Arrays.sort(keys);
		//组织字符串
		String paramString = getParamString(keys,parameters);
		
		logger.info("============================================");
		logger.info("业务参数部分：");
		logger.info(paramString);
		
		//获取加密key并加密
		String signKey = (String) parameters.get("telephone");
		return MD5Tools.getKeyedDigest(paramString,signKey);
	}
	
	/**
	 * 组织加密字符串原串
	 * */
	private String getParamString(String []keys,Map<String,Object> parameters){
		if(keys.length == 0){
			return "";
		}
		StringBuilder temp = new StringBuilder();
		for(String key : keys){
			if("public".equals(key)){
				continue;
			}
			if("facade".equals(key)){
				continue;
			}
			if("baseRequest".equals(key)){
				continue;
			}
			temp.append(key);
			temp.append("=");
			temp.append(parameters.get(key));
			temp.append("&");
		}
		return temp.substring(0, temp.length() - 1);
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
