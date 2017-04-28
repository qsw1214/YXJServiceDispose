package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.GroupbuyDetail;

public interface IGroupbuyDetailDAO {

	List<GroupbuyDetail> findGroupbuyDetailByGroupbuyId(String groupbuyid);

	List<GroupbuyDetail> findGroupbuyDetailByProdid(String groupbuyid,String prodId);

	List<GroupbuyDetail> findGroupbuyDetailIsSpecial(String groupbuyid,String prodId);
}