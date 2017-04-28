package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IUpdateBindingTelephoneDao;
import com.youxianji.service.IUpdateBindingTelephoneService;
@Service("updateBindingTelephoneService")
public class UpdateBindingTelephoneServiceImpl implements IUpdateBindingTelephoneService {
	
	@Resource
	private IUpdateBindingTelephoneDao UpdateBindingTelephoneDao;
	
	@Override
	public void updateBindingTelephone(String userid, String telephone, String verifycode, String newphone) {
		
		UpdateBindingTelephoneDao.updateBindingTelephone(userid, telephone, verifycode, newphone);

	}

}
