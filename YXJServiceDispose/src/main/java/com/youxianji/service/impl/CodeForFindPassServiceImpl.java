package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.ICodeForFindPassDAO;
import com.youxianji.pojo.CodeForFindPass;
import com.youxianji.service.ICodeForFindPassService;

@Service("codeForFindPassService")
public class CodeForFindPassServiceImpl implements ICodeForFindPassService{

	@Resource
	private ICodeForFindPassDAO codeForFindpassDAO;

	/**
	 * 根据手机号查询验证码信息
	 * */
	@Override
	public List<CodeForFindPass> getCodeForFindPass(String telephone) {
		
		return this.codeForFindpassDAO.getCodeForFindPass(telephone);
	}

	/**
	 * 更新验证码信息状态
	 * */
	@Override
	public void updataCodeForFindPass(String id, String state) {
		this.codeForFindpassDAO.updataCodeForFindPass(id, state);
		
	}

	/**
	 * 创建验证码信息
	 * */
	@Override
	public void saveCodeForFindPass(String id, String telephone,
			String verifyCode) {
		this.codeForFindpassDAO.saveCodeForFindPass(id, telephone, verifyCode);
		
	}
}
