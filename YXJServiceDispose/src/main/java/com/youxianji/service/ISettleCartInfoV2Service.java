package com.youxianji.service;

import java.math.BigDecimal;
import java.util.List;

import base.cn.exception.BaseException;

import com.youxianji.facade.trade.bean.SettleCartInfoResponseBean;
import com.youxianji.facade.trade.bean.json.CartDetailBean;
import com.youxianji.facade.trade.bean.json.VoucherBean;
import com.youxianji.pojo.BaseOrderInfo;
import com.youxianji.pojo.BusinessUserInfo;
import com.youxianji.pojo.OrderInfo;


public interface ISettleCartInfoV2Service {
	/*
	 * 结算购物车接口
	 */
	public SettleCartInfoResponseBean doSettleCartInfo(String userId,String os,String deviceNo,List<CartDetailBean> detailList,String invitecode) throws BaseException;

	
	public BaseOrderInfo doBulidBaseOrderInfo(String baseordersn,OrderInfo orderInfo);
	
	
	public BaseOrderInfo doBulidBaseOrderInfo(String baseordersn,List<OrderInfo> orderInfoList);
	
	
	public BigDecimal calcDeliverFee(BigDecimal sellprice,BusinessUserInfo businessUser,BigDecimal orderWeigth);

	public List<VoucherBean> getReturnVoucherBean(String userId
			,BigDecimal normalProdAmt,List<OrderInfo> orderInfoList);
}
