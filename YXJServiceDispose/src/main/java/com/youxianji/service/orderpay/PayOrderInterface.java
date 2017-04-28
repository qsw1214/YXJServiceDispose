package com.youxianji.service.orderpay;

import java.util.Map;

import base.cn.exception.BaseException;

public interface PayOrderInterface {
	/**
	 * 统一支付接口
	 * @param payParamsBean
	 * @return
	 * @throws BaseException
	 */
	
	public  Map<String,String> doPay(PayParamsBean payParamsBean) throws BaseException;
}
