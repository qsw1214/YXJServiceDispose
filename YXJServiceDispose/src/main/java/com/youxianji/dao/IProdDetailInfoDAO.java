package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.ProdDetailInfo;

public interface IProdDetailInfoDAO {

	public List<ProdDetailInfo> getProdDetailInfoList(String prodId);
	
}
