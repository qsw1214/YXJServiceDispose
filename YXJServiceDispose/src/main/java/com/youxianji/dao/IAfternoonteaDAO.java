package com.youxianji.dao;

import com.youxianji.pojo.Afternoontea;

public interface IAfternoonteaDAO {
	public void insert(Afternoontea afternoontea);
	
	public Afternoontea findByInvitecode(String invitecode);
}
