package com.youxianji.facade.yxjia;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.trade.bean.CommitOrderRequestBean;
import com.youxianji.facade.yxjia.bean.JIACommitOrderRequestBean;
import com.youxianji.service.IPayOrderService;

@Facade(command="8004",comment="JIA-提交订单")
@Scope("prototype")
public class JIACommitOrderFacade extends AbsFacade{
	
	@Resource
	private IPayOrderService payOrderService;
	

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		JIACommitOrderRequestBean realRequestBean = (JIACommitOrderRequestBean)baseRequest;
		CommitOrderRequestBean requestBean = setRequesrBean(realRequestBean);
		BaseResponse responseParam = new BaseResponse();
		
		payOrderService.doCommitOrder(requestBean);
		return responseParam;
	}
	
	private CommitOrderRequestBean setRequesrBean(JIACommitOrderRequestBean realRequestBean){
		CommitOrderRequestBean requestBean = new CommitOrderRequestBean();
		requestBean.setChannel("B");
		requestBean.setAddressid("");
		requestBean.setCouponid(realRequestBean.getCouponid());
		requestBean.setDistributiontime(realRequestBean.getDistributiontime());
		requestBean.setOrderremark(realRequestBean.getOrderremark());
		requestBean.setOrdersn(realRequestBean.getOrdersn());
		requestBean.setPaytype(realRequestBean.getPaytype());
		requestBean.setPublicBean(realRequestBean.getPublicBean());
		requestBean.setTelephone(realRequestBean.getTelephone());
		requestBean.setUserid(realRequestBean.getUserid());
		
		return requestBean;
	}
}
