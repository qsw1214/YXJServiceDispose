package com.youxianji.service;

import java.util.List;

import com.youxianji.pojo.FreshmanProduct;

public interface IGetProductByTagService {
	/**
	 * 按标签获取商品
	 * @param userid
	 * @param telephone
	 * @param tagid
	 * @return
	 */
	public List<FreshmanProduct> findProductByTag(String userid,String telephone,String tagid);
}
