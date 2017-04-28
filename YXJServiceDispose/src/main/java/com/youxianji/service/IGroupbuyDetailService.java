package com.youxianji.service;

import java.util.List;

import com.youxianji.pojo.GroupbuyDetail;

public interface IGroupbuyDetailService {
	List<GroupbuyDetail> findGroupbuyDetailByGroupbuyId(String groupbuyid);

	GroupbuyDetail findGroupbuyDetailByProdid(String groupbuyid, String prodId);

}