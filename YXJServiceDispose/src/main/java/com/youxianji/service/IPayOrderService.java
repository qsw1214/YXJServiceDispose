package com.youxianji.service;

import java.util.Map;

import base.cn.exception.BaseException;

import com.youxianji.facade.trade.bean.CommitOrderRequestBean;
import com.youxianji.facade.trade.bean.SwitchPayTypeRequestBean;
import com.youxianji.facade.trade.bean.SwitchPayTypeResponseBean;
import com.youxianji.pojo.OrderInfo;


public interface IPayOrderService {
	
	/*
	 * 提交订单接口
	 *  userid	String	用户ID
		telephone	String	手机号
		ordersn	String	订单号
		addressid	String	收货地址信息ID
		couponid	String	代金券ID
		orderremark	String	订单备注
	 */
	public void doCommitOrder(CommitOrderRequestBean requestBean) throws BaseException;
	/*
	 * 支付订单接口
	  ordersn	String	订单号	
      paytype	String	支付方式	1.微信支付 2.支付宝支付 3.余额支付
      paypass	String	支付密码	余额支付时不能为空
	 */
	public Map<String,String> doPayOrder(String orderSn,String payType,String userId,String payPass,String imei,String ip) throws BaseException;
	
	
	
	
	SwitchPayTypeResponseBean doSwitchPayType(SwitchPayTypeRequestBean requestBean) throws BaseException;

	void doDisposeStock(OrderInfo order,String channel);
}
