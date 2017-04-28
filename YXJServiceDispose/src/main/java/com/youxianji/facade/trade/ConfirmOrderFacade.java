package com.youxianji.facade.trade;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.util.IdGenerator;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.trade.bean.ConfirmOrderRequestBean;
import com.youxianji.facade.trade.bean.ConfirmOrderResponseBean;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.pojo.Orderprocessinfo;
import com.youxianji.service.IOrderInfoService;
import com.youxianji.service.IOrderprocessinfoService;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;
import com.youxianji.web.util.ErrorEnum;

@Facade(command="4006",comment="确认收货")
@Scope("prototype")
public class ConfirmOrderFacade extends AbsFacade{
	
	@Resource
	private IOrderInfoService orderInfoService;
	@Resource
	private IOrderprocessinfoService orderprocessinfoService;
	

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		ConfirmOrderResponseBean responseBean = new ConfirmOrderResponseBean();
		ConfirmOrderRequestBean requestBean = (ConfirmOrderRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		OrderInfo order = orderInfoService.getByOrderSn(requestBean.getOrdersn());
		if(!Constants.ORDER_SEND.equals(order.getOrderstate()) && "1".equals(order.getPayflag())){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("配送订单当前状态不能确认收货");
        	throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		if(!Constants.ORDER_YES_PAY.equals(order.getOrderstate()) && "2".equals(order.getPayflag())){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("自取订单当前状态不能确认收货");
        	throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		orderInfoService.doConfirmOrder(order);
		
		Orderprocessinfo orderprocessinfo = new Orderprocessinfo();
	    orderprocessinfo.setOrdersn(order.getOrdersn());
	    orderprocessinfo.setProcessdesc("已完成");
	    orderprocessinfo.setProcessid(IdGenerator.instance().generate(""));
	    orderprocessinfo.setProcesstime(DateUtil.toStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
	    orderprocessinfo.setLogisticsstate("5");
	    orderprocessinfoService.insert(orderprocessinfo);
		
		return responseParam;
	}

}
