package com.youxianji.facade.trade;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.trade.bean.EditCartInfoRequestBean;
import com.youxianji.facade.trade.bean.EditCartInfoResponseBean;
import com.youxianji.service.ICartInfoService;

@Facade(command="4002",comment="编辑购物车")
@Scope("prototype")
public class EditCartInfoFacade extends AbsFacade{
	
	@Resource
	private ICartInfoService cartInfoService;
	
	private final static Object LOCK_OBJECT = new Object();

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		EditCartInfoRequestBean requestBean = (EditCartInfoRequestBean)baseRequest;
		EditCartInfoResponseBean responseBean = new EditCartInfoResponseBean();
		BaseResponse responseParam = new BaseResponse();

	    synchronized (LOCK_OBJECT) {
		   	cartInfoService.editCartInfo(requestBean);
	    }
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
		
	}

}
