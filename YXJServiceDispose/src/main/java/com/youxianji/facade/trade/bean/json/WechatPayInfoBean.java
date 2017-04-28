package com.youxianji.facade.trade.bean.json;

public class WechatPayInfoBean {

	private String timestamp;//	String	
	private String noncestr;//	String	
	private String packagestr;//	String	
	private String signtype;//	String	
	private String paysign;//	String	
	
	private String appid;
	private String prepayid;
	private String partnerid;
	
	
	
	
	public String getPartnerid() {
		return partnerid;
	}
	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPrepayid() {
		return prepayid;
	}
	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNoncestr() {
		return noncestr;
	}
	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}
	public String getPackagestr() {
		return packagestr;
	}
	public void setPackagestr(String packagestr) {
		this.packagestr = packagestr;
	}
	public String getSigntype() {
		return signtype;
	}
	public void setSigntype(String signtype) {
		this.signtype = signtype;
	}
	public String getPaysign() {
		return paysign;
	}
	public void setPaysign(String paysign) {
		this.paysign = paysign;
	}
	
	
	
}
