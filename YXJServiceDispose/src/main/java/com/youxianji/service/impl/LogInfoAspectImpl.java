package com.youxianji.service.impl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import base.cn.annotation.SysLog;
import base.cn.util.IdGenerator;
import base.cn.util.UUIDGenerator;

import com.youxianji.pojo.LogInfo;
import com.youxianji.service.ILogInfoService;
import com.youxianji.util.Constants;


@Aspect
@Component
public class LogInfoAspectImpl {
	
	private LogInfo log = new LogInfo();
	
    @Resource
    private ILogInfoService logInfoService;
    
	@Pointcut("@annotation(base.cn.annotation.SysLog)")
	public void logPointcut(){

	}
	
	/**
     * 在所有标注@Log的地方切入
     * @param joinPoint
     */
    @Before("logPointcut()")
    public void beforeExec(JoinPoint joinPoint){
        saveInfo(joinPoint);
    }
	
    
    private void saveInfo(JoinPoint joinPoint){
    	
    	MethodSignature ms=(MethodSignature) joinPoint.getSignature();
        Method method=ms.getMethod();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        
        Object[] args = joinPoint.getArgs();
        String paramsStr = "";
        String operator = "";
        HttpServletRequest request = null; 
        for(int i=0;i<args.length;i++){
        	if(args[i] instanceof HttpServletRequest){
        		request = (HttpServletRequest)args[i];
        		paramsStr = getRequestParams(request);
        		/*BaseUserInfo baseUser = (BaseUserInfo)request.getSession().getAttribute(Constants.CONSOLE_SESSION);
        		if(baseUser != null){
        			operator = baseUser.getRealName();
        		}*/
        		
        	}
        }
    	
    	log.setId(UUIDGenerator.getUUID());
    	log.setOperateMethod(sysLog.methodName());
    	log.setOperateModule(sysLog.moduleName());
    	log.setOperator(operator);
    	log.setOperateTime(new Date());
    	log.setParameters(paramsStr);
    	if(request != null){
    		log.setReqIp(request.getRemoteAddr());
    	}
    	
    	logInfoService.saveLogInfo(log);
    	
    }
    
    @AfterThrowing(pointcut="logPointcut()",throwing="ex")
    public void throwExcetionExec(JoinPoint jp,Exception ex){
    	if(log != null){
    		log.setExceptionDesc(ex.toString().length()>1000?ex.toString().substring(0,999):ex.toString());
        	StackTraceElement[] st = ex.getStackTrace();
        	for (StackTraceElement stackTraceElement : st) {
        		 String exclass = stackTraceElement.getClassName();
    	    	 String method = stackTraceElement.getMethodName();
        		 if(exclass.startsWith("com.scan")){
        			 log.setExceptionAddr(exclass+" ==> "+method+" ==> "+stackTraceElement.getLineNumber()+"行");
        			 logInfoService.updateLogInfo(log);
        			 break;
        		 }
    		     
        	}
    	}
    	
    }
	
    
    private String getRequestParams(HttpServletRequest request) {  
    	StringBuffer strBuffer = new StringBuffer();
        Enumeration paramNames = request.getParameterNames();  
        while (paramNames.hasMoreElements()) {  
            String paramName = (String) paramNames.nextElement();  
            String[] paramValues = request.getParameterValues(paramName);  
            if (paramValues.length == 1) {  
                String paramValue = paramValues[0];  
                if (paramValue.length() != 0) {  
                    if(paramName.toUpperCase().contains("PASSWORD")){
                    	paramValue = "******";
                    }
                	strBuffer.append(paramName+"="+paramValue+"<br>");
                }  
            }  
        }          
        return strBuffer.toString();
    
    }  
}
