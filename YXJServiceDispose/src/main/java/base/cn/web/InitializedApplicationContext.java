package base.cn.web;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

import base.cn.annotation.Facade;
import base.cn.annotation.InterfaceParam;

public class InitializedApplicationContext implements ApplicationContextAware,InitializingBean{

	private WebApplicationContext applicationContext; 
	
	@Override
	public void afterPropertiesSet() throws Exception {
		/**
		 * 装载WebApplicationContext 到AppContextManager
		 */
		WebApplicationContext wac = applicationContext;
		AppContextManager appManager = AppContextManager.instance();
		appManager.setWebApplicationContext(wac);
		
		/**
		 * 获取所有使用 @Facade 的BEAN
		 */
		appManager.setFacadeBeans(applicationContext.getBeansWithAnnotation(Facade.class));
		
		/**
		 * 获取所有使用 @InterfaceParam 的BEAN
		 */
		appManager.setInterfaceParamBeans(applicationContext.getBeansWithAnnotation(InterfaceParam.class));
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = (WebApplicationContext) applicationContext;
		
	}

}
