package com.youxianji.service;

import base.cn.exception.BaseException;

import com.youxianji.facade.product.bean.AddProdEvaluateRequestBean;
import com.youxianji.facade.product.bean.AddProdEvaluateResponseBean;
import com.youxianji.facade.product.bean.GetProdEvaluateRequestBean;
import com.youxianji.facade.product.bean.GetProdEvaluateResponseBean;



public interface IProdEvaluateService {

	/**
	 * 添加商品评价接口
	 * @param AddProdEvaluateRequestBean request
	 * @return
	 */
	AddProdEvaluateResponseBean addProdEvaluate(AddProdEvaluateRequestBean request) 
			throws BaseException;
	
	/**
	 * 获取商品评价接口
	 * @param GetProdEvaluateRequestBean request
	 * @return
	 */
	GetProdEvaluateResponseBean getProdEvaludate(GetProdEvaluateRequestBean request);
	
	
	
	
	
	 
}
