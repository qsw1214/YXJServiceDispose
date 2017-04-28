package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.ProductTags;

public interface IGetProductTagsDao {
	
	public List<ProductTags> getProductTags(String userid,String telephone,String tagid);
}
