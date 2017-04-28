package com.youxianji.service;

import java.util.List;

import com.youxianji.pojo.ProdTags;

public interface IGetProdTagsService {
	public List<ProdTags> getProdTags(String userid,String telephone,String tagsId);
}
