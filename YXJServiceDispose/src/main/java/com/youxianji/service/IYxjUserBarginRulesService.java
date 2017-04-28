package com.youxianji.service;

import com.youxianji.pojo.YxjUserBarginRules;


public interface IYxjUserBarginRulesService {

	public abstract YxjUserBarginRules getBargainRulesByDate();

	public abstract YxjUserBarginRules findBargainRulesById(String rulesId);

}