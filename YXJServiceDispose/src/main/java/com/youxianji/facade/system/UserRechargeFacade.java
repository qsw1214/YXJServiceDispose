package com.youxianji.facade.system;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.system.bean.UserRechargeFacadeRequestBean;
import com.youxianji.facade.system.bean.UserRechargeFacadeResponseBean;
import com.youxianji.facade.trade.bean.json.WechatPayInfoBean;
import com.youxianji.service.IUserRechargeService;

@Facade(command="1011",comment="用户充值业务处理")
@Scope("prototype")
public class UserRechargeFacade extends AbsFacade{
	
	@Resource
	private IUserRechargeService userRechargeService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		UserRechargeFacadeResponseBean responseBean = new UserRechargeFacadeResponseBean();
		UserRechargeFacadeRequestBean requestBean = (UserRechargeFacadeRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		String ip = requestBean.getPublicBean().getIp();
		String payType=requestBean.getPaytype();
		
		if("1".equals(payType) || "2".equals(payType)){
		   Map<String,String> map = userRechargeService.doWechatChargeMemberCard(requestBean.getDiscountid(), requestBean.getUserid(), ip,payType,requestBean.getEmploynum());
		   WechatPayInfoBean wp = new WechatPayInfoBean();
		   wp.setNoncestr(map.get("nonceStr"));
		   wp.setPackagestr(map.get("package"));
		   wp.setPaysign(map.get("paysign"));
		   wp.setSigntype(map.get("signType"));
		   wp.setTimestamp(map.get("timeStamp"));
		   
		    //微信APP支付
		   if("2".equals(payType)){
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





















