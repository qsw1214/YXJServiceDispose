package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.ProdImageInfo;

public interface IProdImageInfoDAO {

	public List<ProdImageInfo> getProdImageInfoList(String prodId);
	
}
