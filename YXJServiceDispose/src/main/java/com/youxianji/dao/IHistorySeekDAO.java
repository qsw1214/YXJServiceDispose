package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.HistorySeek;


public interface IHistorySeekDAO {
	
	public List<HistorySeek> getHistorySeekListByUserId(String userid);
	
	public HistorySeek getHistoryByProdName(String userId,String prodName);
	
	public void insert(String historyId,String userId,String prodName,String dateStr);
	
	public void update(String historyId,String prodName,String dateStr);
	
	public void delete(String userId);
}
