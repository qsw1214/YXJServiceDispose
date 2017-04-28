package com.youxianji.service;

import java.util.List;

import com.youxianji.pojo.HistorySeek;


public interface IHistorySeekService {

	public List<HistorySeek> getHistorySeekList(String userid);
	
	public void saveHistorySeek(String userId,String prodName);
	
	public void deleteHistorySeek(String userId);
	 
}
