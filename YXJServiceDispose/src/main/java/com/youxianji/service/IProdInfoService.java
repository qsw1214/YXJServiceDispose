package com.youxianji.service;

import java.util.List;
import java.util.Map;

import base.cn.web.mybatis.util.PageInfo;

import com.youxianji.facade.product.bean.GetNewHomeProdRequestBean;
import com.youxianji.facade.product.bean.GetNewHomeProdResponseBean;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.ProdType;

public interface IProdInfoService {

	public List<ProdInfo> getProdInfoList();
	
	/**
	 * 获取首页商品
	 */
	@Deprecated
	public List<ProdInfo> getHomeProdInfoList();
	
	/**
	 * 获取商家首页商品
	 */
	public List<ProdInfo> getJIAHomeProdInfoList();
	
	
	/**
	 * 按搜索关键字获取商品列表
	 */
	public List<ProdInfo> getProdListBySearchName(String searchName);
	
	public PageInfo<ProdInfo> getPageListProdInfo(Map<String,String> paramsMap);
	
	public List<ProdInfo> getProdInfoListByType(String type);
	
	/**
	 * 如果parentid为NULL则查询全部级别
	 * @param level
	 * @return
	 */
	public List<ProdType> getProdTypeList(String parentid);
	/**
	 * 获取商品详情接口
	 * 
	 */
	public ProdInfo getProdInfoById(String prodId);
	
	
	GetNewHomeProdResponseBean getNewHomeProdList(GetNewHomeProdRequestBean request);
	
	/**
	    * 增加商品库存
	    * purchaseQuantity 库存增量
	    */
	 void updateAddStock(String ordersn);
	
}
