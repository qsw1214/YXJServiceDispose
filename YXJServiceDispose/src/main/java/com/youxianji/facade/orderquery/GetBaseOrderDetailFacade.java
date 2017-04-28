package com.youxianji.facade.orderquery;

import java.math.BigDecimal;
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

import com.youxianji.facade.orderquery.bean.GetBaseOrderDetailRequestBean;
import com.youxianji.facade.orderquery.bean.GetBaseOrderDetailResponseBean;
import com.youxianji.facade.orderquery.bean.jsonbean.OrderDetailData;
import com.youxianji.pojo.BaseOrderInfo;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.service.IBaseOrderInfoService;
import com.youxianji.service.IOrderDetailService;
import com.youxianji.service.IOrderInfoService;
import com.youxianji.util.DateUtil;
@Facade(command="2006",comment="总订单详情")
@Scope("prototype")
public class GetBaseOrderDetailFacade extends AbsFacade {

	@Resource
	private IOrderInfoService orderInfoService;
	@Resource 
	private IOrderDetailService orderDetailService;
	@Resource
	private IBaseOrderInfoService baseOrderInfoService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetBaseOrderDetailResponseBean responseBean = new GetBaseOrderDetailResponseBean();
		GetBaseOrderDetailRequestBean requestBean  = (GetBaseOrderDetailRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
	
		Map<String,String> hashMap = new HashMap<String,String>();
		hashMap.put("baseordersn", requestBean.getBaseordersn());
		hashMap.put("userid", requestBean.getUserid());
		hashMap.put("telephone", requestBean.getTelephone());
		
		BaseOrderInfo baseOrderInfo = baseOrderInfoService.getBaseOrderInfo(hashMap);
		buildOrder(baseOrderInfo,responseBean);
		
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}
	
	private void buildOrder(BaseOrderInfo order,GetBaseOrderDetailResponseBean responseBean){
		responseBean.setDeliverfee(order.getDeliveryfee().toString());
		responseBean.setOrdersn(order.getBaseordersn());
		responseBean.setOrdertime(DateUtil.toStr(order.getOrdertime(),"yyyy-MM-dd HH:mm:ss"));
		responseBean.setPayflag(order.getPaytype());
		responseBean.setReceiveaddress(order.getReceiveaddress());
		responseBean.setReceivename(order.getReceivename());
		responseBean.setReceivephone(order.getReceivephone());
		responseBean.setReceiveprovince(order.getReceiveprovince());
		responseBean.setReceivecity(order.getReceivecity());
		responseBean.setReceivedistrict(order.getReceivedistrict());
		responseBean.setRemark(order.getRemark());
		if("0".equals(order.getOrderstate())){
			responseBean.setState("初始");
		}else if("1".equals(order.getOrderstate())){
			responseBean.setState("待付款");
		}else if("2".equals(order.getOrderstate())){
			responseBean.setState("已支付");
		}else if("3".equals(order.getOrderstate())){
			responseBean.setState("已发货");
		}else if("4".equals(order.getOrderstate())){
			responseBean.setState("已到货");
		}else if("5".equals(order.getOrderstate())){
			responseBean.setState("订单取消");
		}else if("6".equals(order.getOrderstate())){
			responseBean.setState("退货中");
		}else if("7".equals(order.getOrderstate())){
			responseBean.setState("已退货");
		}
		if(order.getPrimeprice() != null){
			responseBean.setTotalprice(order.getPrimeprice().toString());
		}
		
		responseBean.setDetaillist(bulidResultData(order.getBaseordersn()));
		responseBean.setSellprice(order.getSellprice().toString());
		responseBean.setTotaldiscount(order.getDeductamt().toString());
	}
	
	private List<OrderDetailData> bulidResultData(String baseOrderSn){
		List<OrderDetailData> dataList = new ArrayList<OrderDetailData>();
		List<OrderInfo> orderInfoList = orderInfoService.getOrderInfoList(baseOrderSn);
		if(orderInfoList.size() != 0){
			for (OrderInfo orderInfo : orderInfoList) {
				List<OrderDetail> detailList = orderDetailService.getOrderDetailByOrdersn(orderInfo.getOrdersn());
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
			}
		}
		return dataList;
	}
}
