package com.youxianji.facade.yxjia;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.yxjia.bean.JIAEditCartInfoRequestBean;
import com.youxianji.facade.yxjia.bean.JIAEditCartInfoResponseBean;
import com.youxianji.service.IYxjBuserCartInfoService;

@Facade(command="8002",comment="JIA-编辑购物车")
@Scope("prototype")
public class JIAEditCartInfoFacade extends AbsFacade{
	
	@Resource
	private IYxjBuserCartInfoService yxjBuserCartInfoService;
	
	private final static Object LOCK_OBJECT = new Object();

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		JIAEditCartInfoRequestBean requestBean = (JIAEditCartInfoRequestBean)baseRequest;
		JIAEditCartInfoResponseBean responseBean = new JIAEditCartInfoResponseBean();
		BaseResponse responseParam = new BaseResponse();

	    synchronized (LOCK_OBJECT) {
		   yxjBuserCartInfoService.editYxjBuserCartInfo(requestBean);
	    }
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
		
	}

}
