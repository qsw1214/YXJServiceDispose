package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.YxjUserBarginInfo;

public interface IYxjUserBarginInfoDAO {

	YxjUserBarginInfo findBargainInfoById(String bargainid);

	void insertBargainInfo(YxjUserBarginInfo yxjUserBarginInfo);

	List<YxjUserBarginInfo> findBargainInfoByUserId(String rulesid,String userid);

	void updateBargainInfo(YxjUserBarginInfo yxjUserBarginInfo);
    
}