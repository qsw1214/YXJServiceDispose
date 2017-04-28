package com.youxianji.service;

public interface IUpdateBindingTelephoneService {
	/**
	 * 修改绑定手机号业务处理
	 * @param userid
	 * @param telephone
	 * @param verifycode
	 * @param newphone
	 */
	public void updateBindingTelephone(String userid,String telephone,String verifycode,String newphone);
}
