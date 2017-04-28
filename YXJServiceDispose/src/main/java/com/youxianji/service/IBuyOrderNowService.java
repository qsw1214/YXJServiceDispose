package com.youxianji.service;

import java.util.Map;

import base.cn.exception.BaseException;

import com.youxianji.facade.bargain.bean.BuyOrderNowResponseBean;


public interface IBuyOrderNowService {
	
	public Map<String,String> doBuyOrderNow(
			BuyOrderNowResponseBean responseBean,String prodid
			,String payType,String userId,String addressid
			,String imei,String ip,String os
			,String deviceNo,String bargainid
			,String groupbuyid
			,String quantity
			,String invitecode) throws BaseException;
	
}
