package com.youxianji.web.interceptor;

import static com.youxianji.web.util.MobileResponse.sendError;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import base.cn.util.ObjectTools;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.FacadeFactory;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BeanFactory;
import base.cn.web.facade.bean.PublicBean;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.youxianji.util.GsonTools;
import com.youxianji.util.des.DesUtil;
import com.youxianji.web.util.ErrorEnum;

public class BeanFacadeFactroyInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method =null;
            method = handlerMethod.getMethod();
            

    		try{
    			//获取publicbean信息
    			PublicBean pb = null;
    			Map<String,Object> parameters2 = request.getParameterMap();
    			if(!request.getParameterMap().containsKey("public")){
    				String jsondata = request.getParameter("jsondata");
    				JsonParser parser = new JsonParser();
				    JsonObject jsonObject = parser.parse(jsondata).getAsJsonObject();
				    String code = jsonObject.get("public").toString();
				    pb=GsonTools.getSignObject(code, PublicBean.class);
    			}else{
    				 pb = getPublicBean(request.getParameterMap());
    			}
    			
    			@SuppressWarnings("unchecked")
				Map<String,Object> parameters= null;
    			if(!request.getParameterMap().containsKey("public")){
    				parameters= getTestBusinessParam(request,pb);
    				
    			}else{
    				parameters= getBusinessParam(request.getParameterMap(),pb);
    			}
    		
    			//根据参数集合对Action重新赋值
    			setValues(request,response,parameters,pb);
    		}catch(Exception e){
    			//返回异常信息
    			sendError(response,ErrorEnum.FAIL_9003);
    			logger.error("BeanFactoryInterceptor通过反射生成对象异常.",e);
    			return false;
    		}
            
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
	}
	
	  private Map<String,Object> getTestBusinessParam(HttpServletRequest request,PublicBean publicBean) throws Exception{

	    	String jsondata = request.getParameter("jsondata");
			JsonParser parser = new JsonParser();
		    JsonObject jsonObject = parser.parse(jsondata).getAsJsonObject();
		   
		    
			String publicString = jsonObject.get("public").toString();
			//获取加密串
			String secretString =  jsonObject.get("businessparam").toString();
			
			String decryptString = "";
			//如果不是微信端或浏览器端请求，则需要解密业务报文
			if(!"wechat".equals(publicBean.getOs())){
				//解密加密串
				decryptString = DesUtil.decrypt(secretString);
			}else{
				decryptString = converStr(secretString);
			}
			
			//拼接成完整的JSON参数串
			String jsonString = "public="+publicString+"&"+decryptString;
			
			return getParameterMap(jsonString);
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
	 * 重新设置属性值
	 * @throws Exception 
	 * */
	private void setValues(HttpServletRequest request,
			HttpServletResponse response,Map<String,Object> parameters,PublicBean pb) throws Exception{
		
		String reqIp = request.getRemoteAddr();
		pb.setIp(reqIp);
		//通过反射生成BaseRequest对象
		BaseRequest baseRequest = new BeanFactory().getNewBean(pb.getCommand(), parameters);
		baseRequest.setPublicBean(pb);
		//通过反射生成Facade对象
		AbsFacade facade = new FacadeFactory().getNewFacade(pb.getCommand());
		/*HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();*/
		facade.setRequest(request);
		facade.setResponse(response);
		
		//将新对象填入webRequest中
		ServletWebRequest webRequest = getTheWebRequest(request,response);
		setTheWebRequest(webRequest,"facade", facade);
		setTheWebRequest(webRequest,"baseRequest", baseRequest);
		setTheWebRequest(webRequest,"parameters", parameters);
	}
	
	/**
	 * 获取PublicBean对象
	 * @throws Exception 
	 * */
	private PublicBean getPublicBean(Map<String,Object> parameters) throws Exception{
		Object pbObject = parameters.get("public");
		if(!ObjectTools.isNullByObject(pbObject)){
			String jsonString = ((String[])parameters.get("public"))[0];
			URLCodec codec = new URLCodec();
			jsonString = codec.decode(jsonString);
			return (PublicBean)GsonTools.getSignObject(jsonString, PublicBean.class);
		}else{
			
			return null;
		}
		
	}
	
	/**
	 * 获取业务参数加密串
	 * @throws Exception 
	 * */
	private Map<String,Object> getBusinessParam(Map<String,Object> parameters,PublicBean publicBean) throws Exception{
		
		String publicString = ((String[])parameters.get("public"))[0];
		//获取加密串
		String secretString = ((String[])parameters.get("businessparam"))[0];
		
		String decryptString = "";
		//如果不是微信端或浏览器端请求，则需要解密业务报文
		if(!"wechat".equals(publicBean.getOs())){
			//解密加密串
			decryptString = DesUtil.decrypt(secretString);
		}else{
			decryptString = converStr(secretString);
		}
		
		//拼接成完整的JSON参数串
		String jsonString = "public="+publicString+"&"+decryptString;
		
		return getParameterMap(jsonString);
	}
	
	private String converStr(String secretString){
		
		return secretString.replace("{", "").replace("}", "").replace(":", "=").replace(",", "&").replace("\"", "");
	}
	
	/**
	 * 根据请求参数字符串生成MAP
	 */
	private Map<String,Object> getParameterMap(String paramStr){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		String responseArray[] = paramStr.split("&");
		for(String firstSplitStr:responseArray){
			if(firstSplitStr.indexOf("=")!=-1){
				if(firstSplitStr.indexOf("detaillist") != -1 && paramStr.contains("wechat")){
					paramMap.put("detaillist"+firstSplitStr.split("detaillist=")[0], firstSplitStr.split("detaillist=")[1]
							.replace("\\", "\"").replace("=", ":").replace("[", "[{").replace("]", "}]"));
				
				}else{
					if(firstSplitStr.endsWith("=")){
						paramMap.put(firstSplitStr.split("=")[0], "");
					}else{
						paramMap.put(firstSplitStr.split("=")[0], firstSplitStr.split("=")[1]);
					}
				}
					
				
				
			}
		}
		
		return paramMap;
		
	}
	
	/*@Override  
    public void postHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception {  
		if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method =null;
            method = handlerMethod.getMethod();
            System.out.println("调用方法名："+method.getName());
		}
	}*/
	
}
