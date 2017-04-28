package com.youxianji.service;

import java.util.List;

import com.youxianji.pojo.ProductTags;

public interface IGetProductTagsService {
	
	public List<ProductTags> getProductTags(String userid,String telephone,String tagid);
	
}
