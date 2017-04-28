package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IPayPasswordDao;
import com.youxianji.service.IPayPasswordservice;
@Service("payPasswordservice")
public class PayPasswordserviceImpl implements IPayPasswordservice {
	@Resource
	IPayPasswordDao payPasswordDao;

	@Override
	public void updateUserPayPassword(String userid, String password, String telephone, String verifycode) {
		payPasswordDao.updateUserPayPassword(userid, password, telephone, verifycode);
		
	}
	

}
