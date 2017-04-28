package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.YxjUserProdevaluateReply;

public interface IYxjUserProdevaluateReplyDAO {

	public List<YxjUserProdevaluateReply> getValuateReplys(String commentid);
}
