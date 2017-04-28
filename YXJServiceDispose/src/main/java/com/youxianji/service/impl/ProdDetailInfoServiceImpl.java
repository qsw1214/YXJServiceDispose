package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IProdDetailInfoDAO;
import com.youxianji.dao.IProdInfoDAO;
import com.youxianji.dao.IProdTypeDAO;
import com.youxianji.pojo.ProdDetailInfo;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.ProdType;
import com.youxianji.service.IProdDetailInfoService;

@Service("prodDetailInfoService")
public class ProdDetailInfoServiceImpl implements IProdDetailInfoService {

	@Resource
	private IProdDetailInfoDAO prodDetailInfoDAO;

	@Override
	public List<ProdDetailInfo> getListProdDetailInfoByProdId(String prodId) {
		
		return prodDetailInfoDAO.getProdDetailInfoList(prodId);
	}
			
}
