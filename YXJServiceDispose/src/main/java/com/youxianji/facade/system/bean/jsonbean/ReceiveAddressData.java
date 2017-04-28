package com.youxianji.facade.system.bean.jsonbean;

public class ReceiveAddressData {

	/* 收货地址ID */
	private String daid;
	
	/* 收货人手机号 */
	private String telephone;
	
	/* 收货人姓名 */
	private String receivename;
	
	/* 收货地址 */
	private String buildingname;
	
	/* 收货地址 */
	private String receiveaddress;
	
	/* 收货地址经度 */
	private String longitude;
	
	/* 收货地址纬度 */
	private String latitude;
	
	/* 默认标记 0.非默认 1.默认 */
	private String isdefault;
	
	/* 收货省份 */
	private String province;
	
	/* 收货城市 */
	private String city;
	
	/* 收货区县 */
	private String district;

	

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getBuildingname() {
		return buildingname;
	}

	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}

	public String getDaid() {
		return daid;
	}

	public void setDaid(String daid) {
		this.daid = daid;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getReceivename() {
		return receivename;
	}

	public void setReceivename(String receivename) {
		this.receivename = receivename;
	}

	public String getReceiveaddress() {
		return receiveaddress;
	}

	public void setReceiveaddress(String receiveaddress) {
		this.receiveaddress = receiveaddress;
	}

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

	public String getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}
	
	
}
