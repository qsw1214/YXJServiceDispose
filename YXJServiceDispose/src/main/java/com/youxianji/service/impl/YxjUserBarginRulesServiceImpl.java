package com.youxianji.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IYxjUserBarginRulesDAO;
import com.youxianji.pojo.YxjUserBarginRules;
import com.youxianji.service.IYxjUserBarginRulesService;

@Service("yxjUserBarginRulesService")
public class YxjUserBarginRulesServiceImpl implements IYxjUserBarginRulesService {
	@Resource
	private IYxjUserBarginRulesDAO yxjUserBarginRulesDAO;

	@Override
	public YxjUserBarginRules getBargainRulesByDate() {
		YxjUserBarginRules yxjUserBarginRules=null;
		List<YxjUserBarginRules> barginRuleList=yxjUserBarginRulesDAO.getBargainRulesByDate();
		if(barginRuleList!=null&&barginRuleList.size()>0){
			yxjUserBarginRules=barginRuleList.get(0);
		}
		return yxjUserBarginRules;
	}

	@Override
	public YxjUserBarginRules findBargainRulesById(String rulesId) {
		return yxjUserBarginRulesDAO.findBargainRulesById(rulesId);
	}

}