package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IYxjUserProdevaluateReplyDAO;
import com.youxianji.pojo.YxjUserProdevaluateReply;
import com.youxianji.service.IYxjUserProdevaluateReplyService;
@Service("yxjUserProdevaluateReplyService")
public class YxjUserProdevaluateReplyServiceImpl implements IYxjUserProdevaluateReplyService {
	@Resource
	private IYxjUserProdevaluateReplyDAO yxjUserProdevaluateReplyDAO;
	@Override
	public List<YxjUserProdevaluateReply> getProdevaluateReply(String commentid) {
		
		return yxjUserProdevaluateReplyDAO.getValuateReplys(commentid);
	}

}
