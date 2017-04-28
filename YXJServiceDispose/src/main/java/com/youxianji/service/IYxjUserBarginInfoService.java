package com.youxianji.service;

import com.youxianji.pojo.YxjUserBarginInfo;

public interface IYxjUserBarginInfoService {

	YxjUserBarginInfo findBargainInfoByUserId(String rulesid,String userid);
	
	YxjUserBarginInfo findBargainInfoById(String bargainid);

	String insertBargainInfo(String rulesid, String userid,String remark);

	void updateBargainInfo(YxjUserBarginInfo yxjUserBarginInfo);

}