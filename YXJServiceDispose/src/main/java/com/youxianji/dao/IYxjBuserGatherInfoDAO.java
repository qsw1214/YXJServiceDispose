package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.YxjBuserGatherInfo;




public interface IYxjBuserGatherInfoDAO {

	
	int insert(YxjBuserGatherInfo yxjBuserGatherInfo);
	
	List<YxjBuserGatherInfo> getList(String buserId);
}
