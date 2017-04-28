package com.youxianji.service;

public interface IPayPasswordservice {
	/**
	 * 根据用户手机号码和验证码设置新的支付密码
	 */
	public void updateUserPayPassword(String userid,String password,String telephone,String verifycode);
}
