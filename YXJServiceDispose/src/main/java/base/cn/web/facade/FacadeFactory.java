package base.cn.web.facade;

import base.cn.web.AppContextManager;

/**
 * 业务facade生产工厂
 * */
public class FacadeFactory {
	
	private AppContextManager appManager = AppContextManager.instance();

	public AbsFacade getNewFacade(String command){
		return (AbsFacade)appManager.getFacadeBean(command);
	}
	
}
