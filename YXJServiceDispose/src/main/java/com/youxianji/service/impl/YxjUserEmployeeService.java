package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IYxjUserEmployeeDAO;
import com.youxianji.pojo.YxjUserEmployee;
import com.youxianji.service.IYxjUserEmployeeService;
@Service("yxjUserEmployeeService")
public class YxjUserEmployeeService implements IYxjUserEmployeeService {
	@Resource
	private IYxjUserEmployeeDAO yxjUserEmployeeDAO;
	@Override
	public List<YxjUserEmployee>  getUserEmployeeByTelephone(String telephone) {
		List<YxjUserEmployee> employeeList=yxjUserEmployeeDAO.getUserEmployeeByTelephone(telephone);
		return employeeList;
	}
}
