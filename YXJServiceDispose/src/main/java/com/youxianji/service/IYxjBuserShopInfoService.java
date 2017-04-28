package com.youxianji.service;

import com.youxianji.facade.yxjia.bean.JIAGetNearByShopResponseBean;
import com.youxianji.facade.yxjia.bean.JIAGetShopDetailResponseBean;
import com.youxianji.pojo.YxjBuserShopInfo;



public interface IYxjBuserShopInfoService {

	
JIAGetNearByShopResponseBean getNearByShopList(String longitude,String latitude);

JIAGetShopDetailResponseBean getShopDetail(String shopid);

YxjBuserShopInfo getShopDetailByUserId(String userId);


}
