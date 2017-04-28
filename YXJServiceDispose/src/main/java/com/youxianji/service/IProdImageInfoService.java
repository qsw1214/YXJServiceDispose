package com.youxianji.service;

import java.util.List;

import com.youxianji.pojo.ProdImageInfo;

public interface IProdImageInfoService {

	public List<ProdImageInfo> getListProdImageInfoByProdId(String prodId);
	
}
