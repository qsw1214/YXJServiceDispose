package com.youxianji.pojo;



public class YxjBuserShopInfo {

	 private String shopId; //`shop_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '主键',
	 private String userinfoUserId;// varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '商家端用户ID',
	 private String shopName;//varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺名称',
	 private String shopTelephone;// varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺联系方式',
	 private String shopAddress;// varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺地址',
	 private String shopLatitude;// varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺维度坐标',
	 private String shopLongitude;// varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺经度坐标',
	 private String shopBusinessbegin;// varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺营业开始时间',
	 private String shopBusinessend;// varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺营业结束时间',
	 private String shopFlag;// char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺标识  0.未发布 1.已开业 2.已歇业',
	 private String shopImageurl;// varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺门头图片',
	 private String shopState;// char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺状态 0.删除 1.正常',
	 private String shopProvince;//'收货省份',
	 private String shopCity;//'收货城市',
	 private String shopDistrict;//'收货区县',
	 private String qrCodeUrl;
	 
	 
	 
	  
	
	public String getQrCodeUrl() {
		return qrCodeUrl;
	}
	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
	public String getShopProvince() {
		return shopProvince;
	}
	public void setShopProvince(String shopProvince) {
		this.shopProvince = shopProvince;
	}
	public String getShopCity() {
		return shopCity;
	}
	public void setShopCity(String shopCity) {
		this.shopCity = shopCity;
	}
	public String getShopDistrict() {
		return shopDistrict;
	}
	public void setShopDistrict(String shopDistrict) {
		this.shopDistrict = shopDistrict;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getUserinfoUserId() {
		return userinfoUserId;
	}
	public void setUserinfoUserId(String userinfoUserId) {
		this.userinfoUserId = userinfoUserId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopTelephone() {
		return shopTelephone;
	}
	public void setShopTelephone(String shopTelephone) {
		this.shopTelephone = shopTelephone;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getShopLatitude() {
		return shopLatitude;
	}
	public void setShopLatitude(String shopLatitude) {
		this.shopLatitude = shopLatitude;
	}
	public String getShopLongitude() {
		return shopLongitude;
	}
	public void setShopLongitude(String shopLongitude) {
		this.shopLongitude = shopLongitude;
	}
	public String getShopBusinessbegin() {
		return shopBusinessbegin;
	}
	public void setShopBusinessbegin(String shopBusinessbegin) {
		this.shopBusinessbegin = shopBusinessbegin;
	}
	public String getShopBusinessend() {
		return shopBusinessend;
	}
	public void setShopBusinessend(String shopBusinessend) {
		this.shopBusinessend = shopBusinessend;
	}
	public String getShopFlag() {
		return shopFlag;
	}
	public void setShopFlag(String shopFlag) {
		this.shopFlag = shopFlag;
	}
	public String getShopImageurl() {
		return shopImageurl;
	}
	public void setShopImageurl(String shopImageurl) {
		this.shopImageurl = shopImageurl;
	}
	public String getShopState() {
		return shopState;
	}
	public void setShopState(String shopState) {
		this.shopState = shopState;
	}

	 
	 
	 
	 

}
