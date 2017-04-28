package com.youxianji.facade.trade;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.trade.bean.BuyAgainRequestBean;
import com.youxianji.facade.trade.bean.BuyAgainResponseBean;
import com.youxianji.facade.trade.bean.EditCartInfoRequestBean;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.service.ICartInfoService;
import com.youxianji.service.IOrderDetailService;

@Facade(command="4011",comment="再次购买")
@Scope("prototype")
public class BuyAgainFacade extends AbsFacade{
	
	@Resource
	private ICartInfoService cartInfoService;
	@Resource
	private IOrderDetailService orderDetailService;
	
	private final static Object LOCK_OBJECT = new Object();

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		BuyAgainRequestBean requestBean = (BuyAgainRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();

		List<OrderDetail> detailList = orderDetailService.getOrderDetailByOrdersn(requestBean.getOrdersn());
			   
	    for(OrderDetail od:detailList){
		   
		   EditCartInfoRequestBean cartRequestBean = new EditCartInfoRequestBean();
		   cartRequestBean.setCartid("");
		   cartRequestBean.setConfigflag("1");
		   cartRequestBean.setProdid(od.getProd().getProdid());
		   cartRequestBean.setQuantity(od.getQuantity().toString());
		   cartRequestBean.setPublicBean(requestBean.getPublicBean());
		   cartRequestBean.setTelephone(requestBean.getTelephone());
		   cartRequestBean.setUserid(requestBean.getUserid());
		   cartInfoService.editCartInfo(cartRequestBean);
		   
	    }
		
		
		return responseParam;
		
	}

}
