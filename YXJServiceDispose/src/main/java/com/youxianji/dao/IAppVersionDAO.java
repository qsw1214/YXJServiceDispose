package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.AppVersion;

public interface IAppVersionDAO {
	
	 	public AppVersion getAppVersionById(String id);
	    
	    public List<AppVersion> getListAppVersion();
	    
	    public int insert(AppVersion appVersion);
	    
	    public int update(AppVersion appVersion);
	    
	    public int deleteById(String id);
	
}
