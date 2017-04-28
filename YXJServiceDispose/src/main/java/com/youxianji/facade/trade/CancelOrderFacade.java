package com.youxianji.facade.trade;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.util.IdGenerator;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.trade.bean.CancelOrderRequestBean;
import com.youxianji.pojo.BaseOrderInfo;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.pojo.Orderprocessinfo;
import com.youxianji.service.IBaseOrderInfoService;
import com.youxianji.service.ICouponUseInfoService;
import com.youxianji.service.IOrderInfoService;
import com.youxianji.service.IOrderprocessinfoService;
import com.youxianji.service.IProdInfoService;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;
import com.youxianji.web.util.ErrorEnum;

@Facade(command="4007",comment="取消订单")
@Scope("prototype")
public class CancelOrderFacade extends AbsFacade{
	
	@Resource
	private IOrderInfoService orderInfoService;
	@Resource
	private ICouponUseInfoService couponUseInfoService;
	@Resource
	private IOrderprocessinfoService orderprocessinfoService;
	@Resource
	private IProdInfoService prodInfoService;
	@Resource
	private IBaseOrderInfoService baseOrderInfoService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		CancelOrderRequestBean requestBean = (CancelOrderRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("userid", requestBean.getUserid());
		paramsMap.put("baseordersn", requestBean.getOrdersn());
		BaseOrderInfo baseOrder = baseOrderInfoService.getBaseOrderInfo(paramsMap);
		if(!Constants.ORDER_NO_PAY.equals(baseOrder.getOrderstate())){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("当前状态不能取消订单");
        	throw new BaseException(ErrorEnum.FAIL_5555);
		}
		baseOrder.setOrderstate(Constants.ORDER_CANCEL);
		baseOrderInfoService.update(baseOrder);
		
		List<OrderInfo> orderList = orderInfoService.getOrderInfoList(requestBean.getOrdersn());
		
	
		for(OrderInfo order:orderList){
			order.setOrderstate(Constants.ORDER_CANCEL);
			orderInfoService.update(order);
			//退回代金券
			if("1".equals(order.getCouponflag())
					 && order.getCouponamt().doubleValue() > 0){
				 couponUseInfoService.doBackCoupon(requestBean.getUserid(),order.getOrdersn());
			}
			//返回库存
			prodInfoService.updateAddStock(order.getOrdersn());
			
			Orderprocessinfo orderprocessinfo = new Orderprocessinfo();
		    orderprocessinfo.setOrdersn(order.getOrdersn());
		    orderprocessinfo.setProcessdesc("已取消订单");
		    orderprocessinfo.setProcessid(IdGenerator.instance().generate(""));
		    orderprocessinfo.setProcesstime(DateUtil.toStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
		    orderprocessinfo.setLogisticsstate("7");
		    orderprocessinfoService.insert(orderprocessinfo);
		}
		
		return responseParam;
	}

}
