package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.YxjUserEmployee;



public interface IYxjUserEmployeeDAO {

	YxjUserEmployee getObject(String employnum);

	List<YxjUserEmployee> getUserEmployeeByTelephone(String telephone); 
	
}
