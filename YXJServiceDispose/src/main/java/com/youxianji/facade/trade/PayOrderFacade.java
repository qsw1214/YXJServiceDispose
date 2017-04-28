package com.youxianji.facade.trade;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.trade.bean.PayOrderRequestBean;
import com.youxianji.facade.trade.bean.PayOrderResponseBean;
import com.youxianji.facade.trade.bean.json.WechatPayInfoBean;
import com.youxianji.service.IPayOrderService;

@Facade(command="4005",comment="在线支付订单")
@Scope("prototype")
public class PayOrderFacade extends AbsFacade{
	
	@Resource
	private IPayOrderService payOrderService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		PayOrderRequestBean requestBean = (PayOrderRequestBean)baseRequest;
		PayOrderResponseBean responseBean = new PayOrderResponseBean();
		BaseResponse responseParam = new BaseResponse();
        String orderSn = requestBean.getOrdersn();
        String payType = requestBean.getPaytype();
        String payPass = requestBean.getPaypass();
        String userId = requestBean.getUserid();
        String imei = requestBean.getPublicBean().getImei();
        String ip = requestBean.getPublicBean().getIp();
        //这里需要判断detailList 是否为空
      	synchronized (orderSn.intern()) {
			Map<String,String> map = payOrderService.doPayOrder(orderSn,payType,userId,payPass,imei,ip);
			if("1".equals(payType) || "4".equals(payType)){
			   WechatPayInfoBean wp = new WechatPayInfoBean();
			   wp.setNoncestr(map.get("nonceStr"));
			   wp.setPackagestr(map.get("package"));
			   wp.setPaysign(map.get("paysign"));
			   wp.setSigntype(map.get("signType"));
			   wp.setTimestamp(map.get("timeStamp"));
			   
			   //微信APP支付
			   if("4".equals(payType)){
				   wp.setAppid(map.get("appid"));
				   wp.setPrepayid(map.get("prepayid"));
				   wp.setPartnerid(map.get("partnerid"));  
				   wp.setNoncestr(map.get("noncestr"));
				   wp.setTimestamp(map.get("timestamp"));
			   }
			   
			   responseBean.setWechatpay(wp);
			}
			
			responseBean.setOrdersn(orderSn);
				
			responseParam.getParamdata().add(responseBean);
      	}
		return responseParam;
	}

}
