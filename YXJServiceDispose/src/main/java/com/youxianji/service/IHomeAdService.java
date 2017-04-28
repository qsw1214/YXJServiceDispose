package com.youxianji.service;

import java.util.List;

import com.youxianji.pojo.HomeAd;

public interface IHomeAdService {
    /**
     * 获取首页广告接口
     * @return
     */
	public List<HomeAd> getHomeAdList();
	
	
}
