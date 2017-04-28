package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.YxjBuserCartInfo;


public interface IYxjBuserCartInfoDAO {
	
	public List<YxjBuserCartInfo> getListYxjBuserCartInfoByUserId(String userId);
	
	public YxjBuserCartInfo getById(String cartId);
	
	public YxjBuserCartInfo getByProdIdAndUserId(String prodId,String userId);
	
	public void update(YxjBuserCartInfo cartInfo);
	
	public void deleteByCartId(String cartId);	
	
	public void deleteByUserId(String userId);	
	
	public void deleteByProdId(String prodid,String userId);
	
	public void save(YxjBuserCartInfo cartInfo);
	
	public String getYxjBuserCartInfoCount(String userId);
	
	/*
	 * 查询该用户是否有新人专享产品
	 */
	public YxjBuserCartInfo queryIsFreshProdInfo(String userId);
	/*
	 * 查询该用户是否有一分购产品
	 */
	public YxjBuserCartInfo queryIsOnecentProdInfo(String userId);
	
}
