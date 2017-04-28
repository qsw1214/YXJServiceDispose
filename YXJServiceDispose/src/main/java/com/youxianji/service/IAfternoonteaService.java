package com.youxianji.service;

import com.youxianji.pojo.Afternoontea;

public interface IAfternoonteaService {
	
	public void insert(Afternoontea afternoontea);
	
	public Afternoontea findByInvitecode(String invitecode);
}
