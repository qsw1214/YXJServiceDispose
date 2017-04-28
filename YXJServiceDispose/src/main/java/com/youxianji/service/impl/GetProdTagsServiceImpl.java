package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IGetProdTagsDao;
import com.youxianji.pojo.ProdTags;
import com.youxianji.service.IGetProdTagsService;
@Service("getProdTagsService")
public class GetProdTagsServiceImpl implements IGetProdTagsService {
	@Resource
	private IGetProdTagsDao getProdTagsDao;
	@Override
	public List<ProdTags> getProdTags(String userid, String telephone, String tagsId) {
		
		return getProdTagsDao.getProdTags(userid, telephone, tagsId);
	}

}
