package com.youxianji.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youxianji.pojo.YxjUserCouponGrantConfig;
import com.youxianji.pojo.YxjUserCouponGrantConfigDetail;

public interface IYxjUserCouponGrantConfigService {
    
	public void doGrantRedPacket(String shareUserId,String baseOrdersn) throws Exception;

	/**
	 * 根据配置ID获取配置详细信息
	 * @param configId
	 * @return
	 */
	public List<YxjUserCouponGrantConfigDetail> findListGrantConfigDetail(
			@Param("configId")String configId);
	
	/**
	 * 根据红包发放类型获取配置信息
	 * @param configFlag
	 * @return
	 */
	public YxjUserCouponGrantConfig findGrantConfig(String configFlag);
}
