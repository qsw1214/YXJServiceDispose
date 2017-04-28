package com.youxianji.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youxianji.pojo.YxjUserCouponGrantConfigDetail;


public interface IYxjUserCouponGrantConfigDetailDAO {
	
	List<YxjUserCouponGrantConfigDetail> findListGrantConfigDetail(
			@Param("configId")String configId);
	
    
}