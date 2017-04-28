package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.Orderprocessinfo;


public interface IOrderprocessinfoDAO {
	
	public List<Orderprocessinfo> findByOrdersn(String ordersn);
	
	public void insert(Orderprocessinfo orderprocessinfo);
}
