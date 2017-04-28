package com.youxianji.service;

import java.util.List;

import com.youxianji.pojo.ProdDetailInfo;

public interface IProdDetailInfoService {

	public List<ProdDetailInfo> getListProdDetailInfoByProdId(String prodId);
	
}
