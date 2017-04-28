package com.youxianji.facade.trade;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.trade.bean.PayOnecentOrderRequestBean;
import com.youxianji.facade.trade.bean.PayOnecentOrderResponseBean;
import com.youxianji.facade.trade.bean.json.WechatPayInfoBean;
import com.youxianji.service.IPayOnecentOrderService;

@Facade(command="4008",comment="一分购在线支付")
@Scope("prototype")
public class PayOneCentOrderFacade extends AbsFacade{
	
	@Resource
	private IPayOnecentOrderService payOnecentOrderService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		PayOnecentOrderRequestBean requestBean = (PayOnecentOrderRequestBean)baseRequest;
		PayOnecentOrderResponseBean responseBean = new PayOnecentOrderResponseBean();
		BaseResponse responseParam = new BaseResponse();
        String prodid = requestBean.getProdid();
        String payType = requestBean.getPaytype();
        String userId = requestBean.getUserid();
        String imei = requestBean.getPublicBean().getImei();
        String ip = requestBean.getPublicBean().getIp();
        
		Map<String,String> map = payOnecentOrderService.doOnecentPayOrder(responseBean,prodid, payType, userId, null, imei
				, ip, requestBean.getPublicBean().getOs(), imei,requestBean.getInvitecode());
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
		
			
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}

}
