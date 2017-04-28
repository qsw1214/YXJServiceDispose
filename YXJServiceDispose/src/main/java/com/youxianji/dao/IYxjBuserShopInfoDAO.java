package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.YxjBuserShopInfo;




public interface IYxjBuserShopInfoDAO {

	List<YxjBuserShopInfo> getNearByShopList(String longitude,
	                                      String latitude,String shop_distance);
	
	
	YxjBuserShopInfo getShopDetail(String shopid);
	
	YxjBuserShopInfo getShopDetailByuserId(String userId);
}
