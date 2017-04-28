package com.youxianji.facade.orderquery;

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

import com.youxianji.facade.orderquery.bean.QueryOrderListRequestBean;
import com.youxianji.facade.orderquery.bean.QueryOrderListResponseBean;
import com.youxianji.facade.orderquery.bean.jsonbean.PageOrderData;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.service.IOrderDetailService;
import com.youxianji.service.IOrderInfoService;
import com.youxianji.util.DateUtil;

@Facade(command="2001",comment="查询用户订单列表业务处理")
@Scope("prototype")
public class QueryOrderListFacade extends AbsFacade{

	@Resource
	private IOrderInfoService orderInfoService;
	@Resource
	private IOrderDetailService orderDetailService;
	
	
	public BaseResponse execute(BaseRequest baseRequest) {
		QueryOrderListRequestBean requestBean  = (QueryOrderListRequestBean)baseRequest;
		QueryOrderListResponseBean responseBean = new QueryOrderListResponseBean();
		BaseResponse responseParam = new BaseResponse();
		
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("page", requestBean.getPage());
		paramMap.put("userid", requestBean.getUserid());
		paramMap.put("status", requestBean.getStatus());
		paramMap.put("ordertype", "C");
		
		PageInfo<OrderInfo> pageOrderInfo = this.orderInfoService.getPageOrderList(paramMap);
		
		buildResultList(pageOrderInfo,responseBean);
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}
	
	private void buildResultList(PageInfo<OrderInfo> pageOrderInfo,QueryOrderListResponseBean responseBean){
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
		orderData.setBaseordersn(orderInfo.getBaseordersn().getBaseordersn());
	}
}
