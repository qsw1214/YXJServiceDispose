package com.youxianji.facade.orderquery;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.orderquery.bean.GetOrderprocessinfoRequestBean;
import com.youxianji.facade.orderquery.bean.GetOrderprocessinfoResponseBean;
import com.youxianji.facade.orderquery.bean.jsonbean.OrderProcessBean;
import com.youxianji.pojo.Orderprocessinfo;
import com.youxianji.service.IOrderprocessinfoService;


@Facade(command="2004",comment="订单流接口")
@Scope("prototype")
public class GetOrderprocessinfoFacade extends AbsFacade{
	@Resource
	private IOrderprocessinfoService orderprocessinfoService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetOrderprocessinfoRequestBean requestBean=(GetOrderprocessinfoRequestBean) baseRequest;
		GetOrderprocessinfoResponseBean responseBean = new GetOrderprocessinfoResponseBean();
		BaseResponse responseParam=new BaseResponse();
		
		List<Orderprocessinfo> processInfoList = orderprocessinfoService.findByOrdersn(requestBean.getOrdersn());
		List<OrderProcessBean> orderprocesslist = new ArrayList<OrderProcessBean>();
		OrderProcessBean processBean = null;
		for (Orderprocessinfo processInfo : processInfoList) {
			 processBean = new OrderProcessBean();
			 processBean.setOrdersn(processInfo.getOrdersn());
			 processBean.setProcessdesc(processInfo.getProcessdesc());
			 processBean.setProcessremark(processInfo.getProcessremark());
			 processBean.setProcesstime(processInfo.getProcesstime());
			
		 	 orderprocesslist.add(processBean);
		}
		responseBean.setOrderprocesslist(orderprocesslist);
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
		
	}


	
	

}
