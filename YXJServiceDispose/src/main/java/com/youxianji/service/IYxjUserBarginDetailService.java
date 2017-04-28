package com.youxianji.service;

import java.math.BigDecimal;
import java.util.List;

import com.youxianji.facade.bargain.bean.json.AssistbargainBean;
import com.youxianji.pojo.YxjUserBarginDetail;
import com.youxianji.pojo.YxjUserBarginInfo;
import com.youxianji.pojo.YxjUserBarginRules;


public interface IYxjUserBarginDetailService {

	YxjUserBarginDetail findBargainDetail(String id, String userid);

	List<AssistbargainBean> findAssistbargainList(String bargainid);

	void insertBargainDetail(String rulesid,String bargainid, String userid,
			 YxjUserBarginInfo yxjUserBarginInfo, BigDecimal rulesMoney,
			BigDecimal totalMoney, BigDecimal leave, BigDecimal lowestMoney);

	List<AssistbargainBean> findUserAssistBargainInfoList(String id);

	List<YxjUserBarginDetail> findBargainDetailByBargainId(String bargainid);

	List<YxjUserBarginDetail> findBargainDetailByUser(String rulesId,
			String userid);

    
}