package com.youxianji.service;

import java.math.BigDecimal;
import java.util.List;

import base.cn.exception.BaseException;

import com.youxianji.facade.yxjia.bean.JIAEditCartInfoRequestBean;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.YxjBuserCartInfo;


public interface IYxjBuserCartInfoService {
	
	/*
	 * 获取购物车
	 */
	public List<YxjBuserCartInfo> getListYxjBuserCartInfoByUserId(String userId) throws BaseException;
	
	/*
	 * 编辑购物车
	 */
	public void editYxjBuserCartInfo(JIAEditCartInfoRequestBean requestBean) throws BaseException;

	
	/**
	 * 获得购物车总数
	 */
	public String getYxjBuserCartInfoCount(String userId) throws BaseException;
	
	
	public BigDecimal getActualPrice(ProdInfo prodInfo);
	
	public BigDecimal getCartActualTotalPrice(List<YxjBuserCartInfo> cartInfoList);
	
    public BigDecimal getCartMemberTotalPrice(List<YxjBuserCartInfo> cartInfoList);
}
