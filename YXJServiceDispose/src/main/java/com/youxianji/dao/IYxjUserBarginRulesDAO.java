package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.YxjUserBarginRules;

public interface IYxjUserBarginRulesDAO {

	List<YxjUserBarginRules> getBargainRulesByDate();

	YxjUserBarginRules findBargainRulesById(String rulesId);

}
