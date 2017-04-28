package com.youxianji.service;

import base.cn.exception.BaseException;

import com.youxianji.facade.yxjia.bean.JIABuserChargeOfflineRequestBean;
import com.youxianji.facade.yxjia.bean.JIABuserChargeOfflineResponseBean;
import com.youxianji.facade.yxjia.bean.JIAOfflineChargeBillsRequestBean;
import com.youxianji.facade.yxjia.bean.JIAOfflineChargeBillsResponseBean;



public interface IYxjBuserGatherInfoService {

	/**
	 * paymoney
	 * gatheruserid
	 * paypass
	 * @return
	 */
	JIABuserChargeOfflineResponseBean doChargeByOffline(JIABuserChargeOfflineRequestBean
			request) throws BaseException;
	
	
	JIAOfflineChargeBillsResponseBean getOfflineChargeBills(JIAOfflineChargeBillsRequestBean
			request) throws BaseException;
	
}
