package com.youxianji.service;

import java.util.List;

import com.youxianji.pojo.Orderprocessinfo;


public interface IOrderprocessinfoService {

	List<Orderprocessinfo> findByOrdersn(String ordersn);
	
	public void insert(Orderprocessinfo orderprocessinfo);
	

}
