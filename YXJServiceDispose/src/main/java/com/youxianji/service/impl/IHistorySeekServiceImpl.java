package com.youxianji.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import base.cn.util.UUIDGenerator;

import com.youxianji.dao.IHistorySeekDAO;
import com.youxianji.pojo.HistorySeek;
import com.youxianji.service.IHistorySeekService;
import com.youxianji.util.DateUtil;

@Service("historySeekService")
public class IHistorySeekServiceImpl implements IHistorySeekService {

	@Resource
	private IHistorySeekDAO historySeekDAO;

	@Override
	public List<HistorySeek> getHistorySeekList(String userid) {

		return historySeekDAO.getHistorySeekListByUserId(userid);
	}

	@Override
	public void saveHistorySeek(String userId,String prodName) {
		HistorySeek history = historySeekDAO.getHistoryByProdName(userId,prodName);
		String dateStr = DateUtil.toStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		if(history == null){
			List<HistorySeek> seekList = historySeekDAO.getHistorySeekListByUserId(userId);
			//如果HistorySeek记录小于10,就插入，大于10更新时间最早的查询词
			if(seekList == null || seekList.size()<10){
				historySeekDAO.insert(UUIDGenerator.getUUID(),userId, prodName,dateStr);
			}else{
				historySeekDAO.update(seekList.get(seekList.size()-1).getHistoryid()
						,prodName,dateStr);
			}
		}else{
			historySeekDAO.update(history.getHistoryid(),prodName,dateStr);
		}
		
	}

	@Override
	public void deleteHistorySeek(String userId) {
		historySeekDAO.delete(userId);
		
	}
	
	
}
