package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IAdviceSuggestionDao;
import com.youxianji.service.IAdviceSuggestionService;
@Service("adviceSuggestionService")
public class AdviceSuggestionServiceImpl implements IAdviceSuggestionService {
	@Resource
	private IAdviceSuggestionDao adviceSuggestionDao;
	
	@Override
	public void insertAdvice(String feedid,String userid, String content, String contact, String Createtime) {
		
		adviceSuggestionDao.insertAdvice(feedid,userid, content, contact, Createtime);

	}

}
