package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IOrderprocessinfoDAO;
import com.youxianji.pojo.Orderprocessinfo;
import com.youxianji.service.IOrderprocessinfoService;
@Service("orderprocessinfoService")
public class OrderprocessinfoServiceImpl implements IOrderprocessinfoService{

	
	@Resource
	private IOrderprocessinfoDAO orderprocessinfoDao;

	@Override
	public List<Orderprocessinfo> findByOrdersn(String ordersn) {
		// TODO Auto-generated method stub
		return orderprocessinfoDao.findByOrdersn(ordersn);
	}

	@Override
	public void insert(Orderprocessinfo orderprocessinfo) {
		orderprocessinfoDao.insert(orderprocessinfo);
	}


}
