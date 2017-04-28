package com.youxianji.service;

import java.util.Map;

import com.youxianji.facade.trade.bean.PayOnecentOrderResponseBean;

import base.cn.exception.BaseException;


public interface IPayOnecentOrderService {
	
	public Map<String,String> doOnecentPayOrder(PayOnecentOrderResponseBean responseBean,String prodid,String payType
			,String userId,String payPass,String imei,String ip,String os,String deviceNo,String invitecode) throws BaseException;
	
}
