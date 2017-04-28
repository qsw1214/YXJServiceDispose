package com.youxianji.facade.yxjia.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="8006",comment="JIA-获取周边店铺")
public class JIAGetNearByShopRequestBean extends BaseRequest{
	
	private String longitude;//	String	经度坐标
	private String latitude;//	String	纬度坐标
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	
	
	
	
}
