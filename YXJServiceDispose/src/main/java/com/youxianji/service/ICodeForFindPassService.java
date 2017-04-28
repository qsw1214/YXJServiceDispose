package com.youxianji.service;

import java.util.List;

import com.youxianji.pojo.CodeForFindPass;

public interface ICodeForFindPassService {

	/**
	 * 根据手机号查询验证码信息
	 * */
	public List<CodeForFindPass> getCodeForFindPass(String telephone);
	
	/**
	 * 更新验证码信息状态
	 * */
	public void updataCodeForFindPass(String id,String state);
	
	/**
	 * 创建验证码信息
	 * */
	public void saveCodeForFindPass(String id,String telephone,String verifyCode);
}
