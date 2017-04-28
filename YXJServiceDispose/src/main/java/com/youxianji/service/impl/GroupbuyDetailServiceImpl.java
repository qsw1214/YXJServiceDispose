package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IGroupbuyDetailDAO;
import com.youxianji.pojo.GroupbuyDetail;
import com.youxianji.service.IGroupbuyDetailService;

@Service("groupbuyDetailService")
public class GroupbuyDetailServiceImpl implements IGroupbuyDetailService {
	@Resource
	private IGroupbuyDetailDAO groupbuyDetailDAO;

	@Override
	public List<GroupbuyDetail> findGroupbuyDetailByGroupbuyId(String groupbuyid) {
		List<GroupbuyDetail> groupbuyDetailList=groupbuyDetailDAO.findGroupbuyDetailByGroupbuyId(groupbuyid);
	
		return groupbuyDetailList;
	}

	@Override
	public GroupbuyDetail findGroupbuyDetailByProdid(String groupbuyid,String prodId) {
		GroupbuyDetail groupbuyDetail=null;
		List<GroupbuyDetail> groupbuyDetailList=groupbuyDetailDAO.findGroupbuyDetailByProdid(groupbuyid,prodId);
		if(groupbuyDetailList!=null&&groupbuyDetailList.size()>0){
			groupbuyDetail=groupbuyDetailList.get(0);
		}
		return groupbuyDetail;
	}
}