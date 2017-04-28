package com.youxianji.dao;

import com.youxianji.pojo.YxjUserCouponGrantConfig;


public interface IYxjUserCouponGrantConfigDAO {
	
	YxjUserCouponGrantConfig findGrantConfig(
			String configFlag);
	
    
}