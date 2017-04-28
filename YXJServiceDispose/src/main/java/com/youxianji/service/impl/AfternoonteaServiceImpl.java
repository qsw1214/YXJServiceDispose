package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IAfternoonteaDAO;
import com.youxianji.pojo.Afternoontea;
import com.youxianji.service.IAfternoonteaService;

@Service("afternoonteaService")
public class AfternoonteaServiceImpl implements IAfternoonteaService {

	@Resource
	private IAfternoonteaDAO afternoonteaDAO;

	@Override
	public void insert(Afternoontea afternoontea) {
		afternoonteaDAO.insert(afternoontea);
	}

	@Override
	public Afternoontea findByInvitecode(String invitecode) {
		return afternoonteaDAO.findByInvitecode(invitecode);
	}
}
