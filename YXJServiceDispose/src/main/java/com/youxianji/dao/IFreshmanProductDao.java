package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.ProdInfo;

public interface IFreshmanProductDao {
	/**
	 * 查询新人专享商品
	 * @param userid
	 * @param telephone
	 * @return
	 */
	public List<ProdInfo> getFreshmanProductList();
	/**
	 *根据标签查询优先推荐商品
	 */
	public List<ProdInfo> getRecommendProductList(String tag);
	/**
	 *根据类别获取下午茶商品
	 */
	public List<ProdInfo> getProdByTypetList(String typeId);
	/**
	 * 查看用户是不是新人
	 * @param userid
	 * @param telephone
	 * @return
	 */
	public String findIsfreshmanByIdAndTelephone(String userid,String telephone);
}
