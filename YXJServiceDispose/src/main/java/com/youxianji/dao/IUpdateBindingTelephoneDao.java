package com.youxianji.dao;

public interface IUpdateBindingTelephoneDao {
	/**
	 * 修改绑定手机号业务处理
	 * @param userid
	 * @param telephone
	 * @param verifycode
	 * @param newphone
	 */
	public void updateBindingTelephone(String userid,String telephone,String verifycode,String newphone);
}
