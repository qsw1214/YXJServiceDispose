package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IGetProductTagsDao;
import com.youxianji.pojo.ProductTags;
import com.youxianji.service.IGetProductTagsService;
@Service("getProductTagsService")
public class GetProductTagsServiceImpl implements IGetProductTagsService {
	@Resource
	private IGetProductTagsDao getProductTagsDao;
	@Override
	public List<ProductTags> getProductTags(String userid, String telephone, String tagid) {
		
		return getProductTagsDao.getProductTags(userid, telephone, tagid);
	}
	
}
