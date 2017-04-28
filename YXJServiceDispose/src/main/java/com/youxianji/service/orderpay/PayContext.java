package com.youxianji.service.orderpay;

import java.util.Map;

public class PayContext {

	private PayOrderInterface payOrderInterface;
	
	public PayContext(){}

	public PayContext(String payType){
		
		switch(payType){
		    case "1"://微信公众号支付
		    	payOrderInterface = new WechatPayOrder();
		    	break;
		    case "2":
		    	payOrderInterface =  new AliPayOrder();
		    	break;
		    case "3":
		    	payOrderInterface =  new UserAccountPayOrder();
		    	break;
		    case "4":
		    	payOrderInterface =  new WechatAppPayOrder();
		    	break;
	    }
	}
	
	
	public Map<String,String> doPay(PayParamsBean payParamsBean){
		
		return payOrderInterface.doPay(payParamsBean);
	}
	
	
	
}
