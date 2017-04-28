package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.ProdTags;

public interface IPtShowRelationDao {
	public List<ProdTags> getProdTags(String prodid);
}
