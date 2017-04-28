package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IPtShowRelationDao;
import com.youxianji.pojo.ProdTags;
import com.youxianji.service.IPtShowRelationService;
@Service("ptShowRelationService")
public class PtShowRelationServiceImpl implements IPtShowRelationService {
	@Resource
	private IPtShowRelationDao ptShowRelationDao;
	@Override
	public List<ProdTags> getProdTags(String prodid) {
		
		return ptShowRelationDao.getProdTags(prodid);
	}

}
