package com.youxianji.facade.trade;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.trade.bean.SwitchPayTypeRequestBean;
import com.youxianji.facade.trade.bean.SwitchPayTypeResponseBean;
import com.youxianji.service.IPayOrderService;

@Facade(command="4009",comment="根据支付方式切换价格")
@Scope("prototype")
public class SwitchPayTypeFacade extends AbsFacade{
	
	@Resource
	private IPayOrderService payOrderService;
	

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		SwitchPayTypeRequestBean requestBean = (SwitchPayTypeRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		synchronized (requestBean.getUserid().intern()) {
			SwitchPayTypeResponseBean responseBean = payOrderService.doSwitchPayType(requestBean);
			responseParam.getParamdata().add(responseBean);
		}
		return responseParam;
	}

}
