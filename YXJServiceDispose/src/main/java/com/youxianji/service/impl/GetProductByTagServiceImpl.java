package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IGetProductByTagDao;
import com.youxianji.pojo.FreshmanProduct;
import com.youxianji.service.IGetProductByTagService;
@Service("getProductByTagService")
public class GetProductByTagServiceImpl implements IGetProductByTagService {
	@Resource
	private IGetProductByTagDao getProductByTagDao;
	@Override
	public List<FreshmanProduct> findProductByTag(String userid, String telephone, String tagid) {
		
		return getProductByTagDao.findProductByTag(userid, telephone, tagid);
	}

}
