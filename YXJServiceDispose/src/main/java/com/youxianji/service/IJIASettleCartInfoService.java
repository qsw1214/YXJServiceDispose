package com.youxianji.service;

import java.util.List;

import base.cn.exception.BaseException;

import com.youxianji.facade.yxjia.bean.JIASettleCartInfoResponseBean;
import com.youxianji.facade.yxjia.bean.json.JIACartDetailBean;


public interface IJIASettleCartInfoService {
	/*
	 * 结算购物车接口
	 */
	public JIASettleCartInfoResponseBean doSettleCartInfo(String userId,String os,String deviceNo,List<JIACartDetailBean> detailList) throws BaseException;

}
