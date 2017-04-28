package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IProdImageInfoDAO;
import com.youxianji.pojo.ProdImageInfo;
import com.youxianji.service.IProdImageInfoService;

@Service("prodImageInfoService")
public class ProdImageInfoServiceImpl implements IProdImageInfoService {

	@Resource
	private IProdImageInfoDAO prodImageInfoDAO;

	@Override
	public List<ProdImageInfo> getListProdImageInfoByProdId(String prodId) {

		return prodImageInfoDAO.getProdImageInfoList(prodId);
	}

	
}
