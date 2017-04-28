package com.youxianji.service;

import java.util.List;

import base.cn.exception.BaseException;

import com.youxianji.pojo.ProdInfo;

public interface IFreshmanProductService {
	/**
	 * 查询新人专享商品
	 */
	public List<ProdInfo> getFreshmanProductList();
	/**
	 *根据标签查询优先推荐商品
	 */
	public List<ProdInfo> getRecommendProductList(String tag);
	/**
	 *根据类别获取下午茶商品
	 */
	public List<ProdInfo> getProdByTypetList(String typeId);
	/**
	 * 获取一份购活动商品
	 */
	public List<ProdInfo> getPennyProductList(String employnum) throws BaseException;
	
	/**
	 * 查看用户是不是新人
	 */
	public String findIsfreshmanByIdAndTelephone(String userid,String telephone);
}
