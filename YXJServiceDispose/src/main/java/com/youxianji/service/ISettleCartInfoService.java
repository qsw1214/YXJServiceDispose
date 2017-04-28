package com.youxianji.service;

import java.math.BigDecimal;
import java.util.List;

import base.cn.exception.BaseException;

import com.youxianji.facade.trade.bean.SettleCartInfoResponseBean;
import com.youxianji.facade.trade.bean.json.CartDetailBean;
import com.youxianji.facade.trade.bean.json.VoucherBean;
import com.youxianji.pojo.BusinessUserInfo;
import com.youxianji.pojo.CouponUseInfo;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.pojo.OrderInfo;


public interface ISettleCartInfoService {
	/*
	 * 结算购物车接口
	 */
	public SettleCartInfoResponseBean doSettleCartInfo(String userId,String os,String deviceNo,List<CartDetailBean> detailList,String invitecode) throws BaseException;

	
	/**
	 * 计算配送费
	 */
	BigDecimal calcDeliverFee(BigDecimal sellprice,BusinessUserInfo businessUser);
	/**
	 * 计算满减金额
	 */
	BigDecimal calcDeductAmt(BigDecimal sellprice);
	/**
	 * 获得满赠商品
	 */
	String queryDeductProd(BigDecimal sellprice);
	
	public List<VoucherBean> queryIsUseCoupon(BigDecimal sellprice
			,List<CouponUseInfo> couponUseList);
	
	
	void caclDiscountAmount(OrderInfo order,BigDecimal sellprice
			,List<OrderDetail> orderDetailList,boolean deductFlag,VoucherBean voucherBean,boolean editDetailFlag);
}
