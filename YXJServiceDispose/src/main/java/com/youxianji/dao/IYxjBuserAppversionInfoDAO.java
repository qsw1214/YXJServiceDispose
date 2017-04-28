package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.YxjBuserAppversionInfo;

public interface IYxjBuserAppversionInfoDAO {
	
	 public YxjBuserAppversionInfo getYxjBuserAppversionInfoById(String id);
	    
	    public List<YxjBuserAppversionInfo> getListYxjBuserAppversionInfo();
	    
	    public int insert(YxjBuserAppversionInfo appVersion);
	    
	    public int update(YxjBuserAppversionInfo appVersion);
	    
	    public int deleteById(String id);
	    
	    public YxjBuserAppversionInfo getYxjBuserAppversionInfoByOsAndVersionNum(String os,String versionNum);
	    
	    public YxjBuserAppversionInfo getNewAppDeliverInfo(String os,String curDate);
	    
	    public YxjBuserAppversionInfo getCurrentAppDeliverInfo(String os);
	    
	
}
