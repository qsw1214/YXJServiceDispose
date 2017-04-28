package com.youxianji.facade.yxjia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;
import base.cn.web.mybatis.util.PageInfo;

import com.youxianji.facade.orderquery.bean.jsonbean.PageOrderData;
import com.youxianji.facade.yxjia.bean.JIAQueryOrderListRequestBean;
import com.youxianji.facade.yxjia.bean.JIAQueryOrderListResponseBean;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.service.IOrderDetailService;
import com.youxianji.service.IOrderInfoService;
import com.youxianji.util.DateUtil;

@Facade(command="6001",comment="JIA-进货订单查看")
@Scope("prototype")
public class JIAQueryOrderListFacade extends AbsFacade{

	@Resource
	private IOrderInfoService orderInfoService;
	@Resource
	private IOrderDetailService orderDetailService;
	
	
	public BaseResponse execute(BaseRequest baseRequest) {
		JIAQueryOrderListRequestBean requestBean  = (JIAQueryOrderListRequestBean)baseRequest;
		JIAQueryOrderListResponseBean responseBean = new JIAQueryOrderListResponseBean();
		BaseResponse responseParam = new BaseResponse();
		
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("page", requestBean.getPage());
		paramMap.put("userid", requestBean.getUserid());
		paramMap.put("status", requestBean.getStatus());
		paramMap.put("ordertype", "B");
		
		PageInfo<OrderInfo> pageOrderInfo = this.orderInfoService.getPageOrderList(paramMap);
		
		buildResultList(pageOrderInfo,responseBean);
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}
	
	private void buildResultList(PageInfo<OrderInfo> pageOrderInfo,JIAQueryOrderListResponseBean responseBean){
		responseBean.setCurrentpage(pageOrderInfo.getPageIndex().toString());
		responseBean.setPagecount(pageOrderInfo.getPageSize().toString());
		responseBean.setTotalcount(pageOrderInfo.getRecordCount().toString());
		responseBean.setTotalpage(pageOrderInfo.getTotalpage().toString());
		
		List<PageOrderData> jsonlist = new ArrayList<PageOrderData>();
		List<OrderInfo> OrderInfoList = pageOrderInfo.getList();
		int size = pageOrderInfo.getList().size();
		PageOrderData orderData = null;
		OrderInfo orderInfo = null;
		for(int i=0;i<size;i++){
			orderData = new PageOrderData();
			orderInfo = OrderInfoList.get(i);
			bulidResultData(orderData,orderInfo);
			jsonlist.add(orderData);
		}
		
		responseBean.setJsonlist(jsonlist);
	}
	
	private void bulidResultData(PageOrderData orderData,OrderInfo orderInfo){
		orderData.setOrdersn(orderInfo.getOrdersn());
		orderData.setAmount(orderInfo.getSellprice().toString());
		orderData.setOrdertime(DateUtil.toStr(orderInfo.getOrdertime(),"yyyy-MM-dd HH:mm:ss"));
		
		orderData.setCargocode(orderInfo.getCargocode());
		orderData.setOrderstate(orderInfo.getOrderstate());
		List<OrderDetail> odlist = orderDetailService.getOrderDetailByOrdersn(orderInfo.getOrdersn());
		orderData.setProdquantity(String.valueOf(odlist.size()));
		if(odlist.get(0).getProd() != null){
			orderData.setProdimageurl(odlist.get(0).getProd().getImageUrl());
		}
		orderData.setPaytype(orderInfo.getPaytype());
	}
}
