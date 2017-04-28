package com.youxianji.facade.bargain;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.bargain.bean.BuyOrderNowRequestBean;
import com.youxianji.facade.bargain.bean.BuyOrderNowResponseBean;
import com.youxianji.facade.trade.bean.json.WechatPayInfoBean;
import com.youxianji.service.IBuyOrderNowService;

@Facade(command="k006",comment="立即购买接口")
@Scope("prototype")
public class BuyOrderNowFacade extends AbsFacade{
	
	@Resource
	private IBuyOrderNowService buyOrderNowService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		BuyOrderNowRequestBean requestBean = (BuyOrderNowRequestBean)baseRequest;
		BuyOrderNowResponseBean responseBean = new BuyOrderNowResponseBean();
		BaseResponse responseParam = new BaseResponse();
		
		String prodid = requestBean.getProdid();
        String userId = requestBean.getUserid();
        String imei = requestBean.getPublicBean().getImei();
        String ip = requestBean.getPublicBean().getIp();
        String payType = requestBean.getPaytype();
        String addressid = requestBean.getAddressid();
  
        synchronized (requestBean.getUserid().intern()) {
			Map<String,String> map = buyOrderNowService.doBuyOrderNow(responseBean,prodid
					, payType, userId, addressid, imei
					, ip, requestBean.getPublicBean().getOs()
					, imei,requestBean.getBargainid()
					,requestBean.getGroupbuyid()
					,requestBean.getQuantity()
					,requestBean.getInvitecode());
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
        }
		
		return responseParam;
		
	}
	
}
