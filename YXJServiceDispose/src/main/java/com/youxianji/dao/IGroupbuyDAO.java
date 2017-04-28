package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.Groupbuy;

public interface IGroupbuyDAO {

	List<Groupbuy> getGroupbuyByDate();

	Groupbuy findGroupbuyById(String groupbuyId);

}
