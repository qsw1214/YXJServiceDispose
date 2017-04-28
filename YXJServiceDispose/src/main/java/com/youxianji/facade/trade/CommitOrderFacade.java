package com.youxianji.facade.trade;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.trade.bean.CommitOrderRequestBean;
import com.youxianji.service.IPayOrderService;

@Facade(command="4004",comment="提交订单")
@Scope("prototype")
public class CommitOrderFacade extends AbsFacade{
	
	@Resource
	private IPayOrderService payOrderService;
	

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		CommitOrderRequestBean requestBean = (CommitOrderRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		synchronized (requestBean.getOrdersn().intern()) {
		   payOrderService.doCommitOrder(requestBean);
		}
		return responseParam;
	}

}
