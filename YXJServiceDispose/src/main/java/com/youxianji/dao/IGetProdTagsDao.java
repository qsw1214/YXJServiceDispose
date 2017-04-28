package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.ProdTags;

public interface IGetProdTagsDao {
	public List<ProdTags> getProdTags(String userid,String telephone,String tagsId);
}
