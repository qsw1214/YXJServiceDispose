package com.youxianji.service;

import java.util.List;

import com.youxianji.pojo.YxjUserEmployee;

public interface IYxjUserEmployeeService {
	public abstract List<YxjUserEmployee> getUserEmployeeByTelephone(String telephone);

}
