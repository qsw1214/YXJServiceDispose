package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.CartInfo;


public interface ICartInfoDAO {
	
	public List<CartInfo> getListCartInfoByUserId(String userId);
	
	public CartInfo getById(String cartId);
	
	public CartInfo getByProdIdAndUserId(String prodId,String userId);
	
	public void update(CartInfo cartInfo);
	
	public void deleteByCartId(String cartId);	
	
	public void deleteByUserId(String userId);	
	
	public void deleteByProdId(String prodid,String userId);
	
	public void save(CartInfo cartInfo);
	
	public String getCartInfoCount(String userId);
	
	/*
	 * 查询该用户是否有新人专享产品
	 */
	public CartInfo queryIsFreshProdInfo(String userId);
	/*
	 * 查询该用户是否有一分购产品
	 */
	public CartInfo queryIsOnecentProdInfo(String userId);
	
}
