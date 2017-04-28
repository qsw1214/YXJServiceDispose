package base.cn.web;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import base.cn.annotation.Facade;
import base.cn.annotation.InterfaceParam;
import base.cn.util.ObjectTools;

/**
 * ApplicationContext管理类
 * */
public class AppContextManager {

	private static AppContextManager appContextManager = new AppContextManager();
	
	private WebApplicationContext wac;
	private ApplicationContext app;

	private Map<String, Object>	facadeBeans = null;
	private Map<String, Object>	interfaceParamBeans = null;
	
	//是否是从web启动
	private Boolean isWebAppContext = false;
	//applicationContext应用锁
	private static Object appLock = new Object();
	
	
	private AppContextManager(){
	}
	
	public static AppContextManager instance(){
		return appContextManager;
	}
	
	public void setWebApplicationContext(WebApplicationContext wac){
		this.wac = wac;
		this.isWebAppContext = true;
	}
	
	/**
	 * 获取指定FacadeBean对象
	 * */
	public Object getFacadeBean(String command){
		for(Object facadeBean : facadeBeans.values()){
			if(command.equals(facadeBean.getClass().getAnnotation(Facade.class).command())){
				return facadeBean;
			}
		}
		
		return null;
	}
	
	/**
	 * 获取指定InterfaceParam 对象
	 * @return
	 */
	public Object getParamBean(String command){
		for(Object interfaceParamBean : interfaceParamBeans.values()){
			if(command.equals(interfaceParamBean.getClass().getAnnotation(InterfaceParam.class).command())){
				String className = interfaceParamBean.getClass().getName();
				return ObjectTools.createObject(className);
			}
		}
		return null;
	}
	
	/**
	 * 获取指定Bean对象
	 * */
	public Object getBean(String beanName){
		//判断是否web启动
		if(this.isWebAppContext){
			return wac.getBean(beanName);
		}
		//应用程序启动
		if(this.app == null){
			synchronized (appLock) {
				if(this.app == null){
					this.app = new ClassPathXmlApplicationContext("/spring-mybatis.xml");
				}
			}
		}
		return this.app.getBean(beanName);
	}
	

	public WebApplicationContext getWac() {
		return wac;
	}

	public void setWac(WebApplicationContext wac) {
		this.wac = wac;
	}

	public void setFacadeBeans(Map<String, Object> facadeBeans) {
		this.facadeBeans = facadeBeans;
	}

	public void setInterfaceParamBeans(Map<String, Object> interfaceParamBeans) {
		this.interfaceParamBeans = interfaceParamBeans;
	}

	
}
