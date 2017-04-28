package com.youxianji.service;

import java.math.BigDecimal;
import java.util.List;

import base.cn.exception.BaseException;

import com.youxianji.facade.trade.bean.EditCartInfoRequestBean;
import com.youxianji.pojo.CartInfo;
import com.youxianji.pojo.ProdInfo;


public interface ICartInfoService {
	
	/*
	 * 获取购物车
	 */
	public List<CartInfo> getListCartInfoByUserId(String userId) throws BaseException;
	
	/*
	 * 编辑购物车
	 */
	public void editCartInfo(EditCartInfoRequestBean requestBean) throws BaseException;

	
	/**
	 * 获得购物车总数
	 */
	public String getCartInfoCount(String userId) throws BaseException;
	
	
	public BigDecimal getActualPrice(ProdInfo prodInfo);
	
	public BigDecimal getCartActualTotalPrice(List<CartInfo> cartInfoList);
	
    public BigDecimal getCartMemberTotalPrice(List<CartInfo> cartInfoList);
}
