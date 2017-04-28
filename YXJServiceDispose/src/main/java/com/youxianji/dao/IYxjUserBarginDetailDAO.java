package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.YxjUserBarginDetail;


public interface IYxjUserBarginDetailDAO {

	List<YxjUserBarginDetail> findBargainDetail(String bargainId, String relationUserId);

	List<YxjUserBarginDetail> findBargainDetailByBargainId(String bargainid);

	void insertBargainDetail(YxjUserBarginDetail yxjUserBarginDetail);

	List<YxjUserBarginDetail> findBargainDetailByUser(String rulesId,
			String userid);
    
}