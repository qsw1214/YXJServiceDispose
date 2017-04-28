package com.youxianji.facade.orderquery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.orderquery.bean.GetOrderDetailRequestBean;
import com.youxianji.facade.orderquery.bean.GetOrderDetailResponseBean;
import com.youxianji.facade.orderquery.bean.jsonbean.OrderDetailData;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.service.IOrderDetailService;
import com.youxianji.service.IOrderInfoService;
import com.youxianji.util.DateUtil;

@Facade(command="2002",comment="商品详情接口")
@Scope("prototype")
public class GetOrderDetailFacade extends AbsFacade{

	@Resource
	private IOrderInfoService orderInfoService;
	@Resource 
	private IOrderDetailService orderDetailService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetOrderDetailResponseBean responseBean = new GetOrderDetailResponseBean();
		GetOrderDetailRequestBean requestBean  = (GetOrderDetailRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		String orderSn = requestBean.getOrdersn();
		OrderInfo order = orderInfoService.getByOrderSn(orderSn);
		buildOrder(order,responseBean);
		
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}
	
	private void buildOrder(OrderInfo order,GetOrderDetailResponseBean responseBean){
		responseBean.setDeductamt(order.getDeductamt() == null?"0":order.getDeductamt().toString());
		responseBean.setDeliverfee(order.getDeliveryfee().toString());
		responseBean.setFirstamt(order.getFirstorderamt().toString());
		responseBean.setIsreview("0");
		if(order.getCouponamt().compareTo(new BigDecimal("0")) > 0){
			responseBean.setIssprcial("1");
		}else{
			responseBean.setIssprcial("0");
		}
		responseBean.setOrdersn(order.getOrdersn());
		responseBean.setOrdertime(DateUtil.toStr(order.getOrdertime(),"yyyy-MM-dd HH:mm:ss"));
		responseBean.setPayflag(order.getPaytype());
		responseBean.setReceiveaddress(order.getReceiveaddress());
		responseBean.setReceivename(order.getReceivename());
		responseBean.setReceivephone(order.getReceivetelephone());
		responseBean.setReceiveprovince(order.getReceiveprovince());
		responseBean.setReceivecity(order.getReceivecity());
		responseBean.setReceivedistrict(order.getReceivedistrict());
		responseBean.setReceivetime(DateUtil.toStr(order.getReceivetime(),"yyyy-MM-dd HH:mm:ss"));
		responseBean.setRemark(order.getRemark());
		responseBean.setSellprice(order.getSellprice().toString());
		//responseBean.setSendername(order.get);
		//responseBean.setSenderphone(senderphone);
		responseBean.setServicefee(order.getServicefee().toString());
		responseBean.setSpecialamt(order.getCouponamt().toString());
		responseBean.setState(order.getOrderstate());
		responseBean.setTotalprice(order.getPrimeprice().toString());
		responseBean.setCargocode(order.getCargocode());
		responseBean.setTotaldiscount(order.getDeductamt()
                        .add(order.getFirstorderamt())
                        .add(order.getCouponamt())
                        .toString());
		
		responseBean.setDetaillist(bulidResultData(order.getOrdersn()));
	}
	
	private List<OrderDetailData> bulidResultData(String orderSn){
		List<OrderDetailData> dataList = new ArrayList<OrderDetailData>();
		List<OrderDetail> detailList = orderDetailService.getOrderDetailByOrdersn(orderSn);
		OrderDetailData data = null;
		for(OrderDetail od:detailList){
			data = new OrderDetailData();
			data.setDetailnum(od.getDetailid());
			data.setProdid(od.getProd().getProdid());
			data.setImgurl(od.getProd().getImageUrl());
			data.setIsreview(od.getIsreview());
			data.setProdname(od.getProdname());
			data.setQuantity(od.getQuantity().toString());
			data.setTotalprice(od.getSellprice().multiply(new BigDecimal(od.getQuantity())).toString());
			data.setUnitprice(od.getSellprice().toString());
			data.setProdnum(od.getProd().getProdnum());
			data.setPreselltag(od.getProd().getPreSellTag());
			if(od.getProd().getPreSellTime() != null){
				data.setPreselltime(DateUtil.toStr(od.getProd().getPreSellTime(),"yyyy-MM-dd HH:mm:ss"));	
			}
			
			dataList.add(data);
		}
		
		return dataList;
	}
}
