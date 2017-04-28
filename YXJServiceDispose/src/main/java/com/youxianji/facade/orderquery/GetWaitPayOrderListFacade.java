package com.youxianji.facade.orderquery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import com.youxianji.facade.orderquery.bean.GetWaitPayOrderRequestBean;
import com.youxianji.facade.orderquery.bean.GetWaitPayOrderResponseBean;
import com.youxianji.facade.orderquery.bean.jsonbean.WaitPayOrderBean;
import com.youxianji.pojo.BaseOrderInfo;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.service.IBaseOrderInfoService;
import com.youxianji.service.IOrderDetailService;
import com.youxianji.service.IOrderInfoService;
import com.youxianji.util.DateUtil;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;
import base.cn.web.mybatis.util.PageInfo;
@Facade(command="2005",comment="待付款订单列表")
@Scope("prototype")
public class GetWaitPayOrderListFacade extends AbsFacade {
	
	@Resource
	private IOrderInfoService orderInfoService;
	@Resource 
	private IOrderDetailService orderDetailService;
	@Resource
	private IBaseOrderInfoService baseOrderInfoService;
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetWaitPayOrderResponseBean responseBean = new GetWaitPayOrderResponseBean();
		GetWaitPayOrderRequestBean requestBean  = (GetWaitPayOrderRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		
		Map<String,String> hashMap = new HashMap<String,String>();
		hashMap.put("page", requestBean.getPage());
		hashMap.put("userid", requestBean.getUserid());
		hashMap.put("telephone", requestBean.getTelephone());
		
		PageInfo<BaseOrderInfo> pageOrder = baseOrderInfoService.getPageBaseOrderList(hashMap);
		buildResultList(pageOrder,responseBean);
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}

	private void buildResultList(PageInfo<BaseOrderInfo> page,GetWaitPayOrderResponseBean responseBean){
		responseBean.setCurrentpage(page.getPageIndex().toString());
		responseBean.setPagecount(page.getPageSize().toString());
		responseBean.setTotalcount(page.getRecordCount().toString());
		responseBean.setTotalpage(page.getTotalpage().toString());
		
		List<WaitPayOrderBean> waitPayOrderList = new ArrayList<WaitPayOrderBean>();
		List<BaseOrderInfo> baseorderInfoList = page.getList();
		int size = page.getList().size();
		WaitPayOrderBean waitPayOrderBean = null;
		BaseOrderInfo baseorderInfo = null;
		for(int i=0;i<size;i++){
			waitPayOrderBean = new WaitPayOrderBean();
			baseorderInfo = baseorderInfoList.get(i);
			bulidResultData(waitPayOrderBean,baseorderInfo);
			waitPayOrderList.add(waitPayOrderBean);
		}
		responseBean.setJsonlist(waitPayOrderList);
	}
	
	private void bulidResultData(WaitPayOrderBean waitPayOrderBean,BaseOrderInfo orderInfo){
		List<OrderInfo> orderInfoList = orderInfoService.getOrderInfoList(orderInfo.getBaseordersn());
		int num = 0;
		String imageUrl = "";
		for (OrderInfo orderInfo2 : orderInfoList) {
			List<OrderDetail> orderDetailByOrdersnList = orderDetailService.getOrderDetailByOrdersn(orderInfo2.getOrdersn());
			for (OrderDetail orderDetail : orderDetailByOrdersnList) {
				num += orderDetail.getQuantity();
				imageUrl = orderDetail.getProd().getImageUrl();
			}
		}
	
		waitPayOrderBean.setAmount(String.valueOf(orderInfo.getSellprice()));
		waitPayOrderBean.setBaseordersn(orderInfo.getBaseordersn());
		//waitPayOrderBean.setCargocode(orderInfo.getCargocode());
		waitPayOrderBean.setOrderstate(orderInfo.getOrderstate());
		waitPayOrderBean.setOrdertime(DateUtil.toStr(orderInfo.getOrdertime(), "yyyy-MM-dd HH:mm:ss"));
		waitPayOrderBean.setPaytype(orderInfo.getPaytype());
		waitPayOrderBean.setProdquantity(String.valueOf(num));
		waitPayOrderBean.setProdimageurl(imageUrl);
	}
	
	
	
	
	
	
	
	
	
}
