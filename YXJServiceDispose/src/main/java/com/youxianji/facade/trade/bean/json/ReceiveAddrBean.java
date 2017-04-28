package com.youxianji.facade.trade.bean.json;

public class ReceiveAddrBean {

	private String receiveid;//	String	收货地址ID
	private String receivephone;//		String	收货人手机号
	private String receivename; //	String	收货人姓名
	private String receiveaddress;//		String	收货地址
	private String receiveprovince;//	String 收货省份
	private String receivecity;//	String 收货城市
	private String receivedistrict;//	String 收货区县
	
	public String getReceiveprovince() {
		return receiveprovince;
	}
	public void setReceiveprovince(String receiveprovince) {
		this.receiveprovince = receiveprovince;
	}
	public String getReceivecity() {
		return receivecity;
	}
	public void setReceivecity(String receivecity) {
		this.receivecity = receivecity;
	}
	public String getReceivedistrict() {
		return receivedistrict;
	}
	public void setReceivedistrict(String receivedistrict) {
		this.receivedistrict = receivedistrict;
	}
	public String getReceiveid() {
		return receiveid;
	}
	public void setReceiveid(String receiveid) {
		this.receiveid = receiveid;
	}
	public String getReceivephone() {
		return receivephone;
	}
	public void setReceivephone(String receivephone) {
		this.receivephone = receivephone;
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
	
	
	
}
