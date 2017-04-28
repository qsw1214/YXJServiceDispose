package com.youxianji.facade.bargain.bean;


public class GetUserLaunchBargainInfoResponseBean {
	private String userid	;//用户ID	
	private String telephone;//用户手机号	
	private String ismakebargain;//是否发起砍价	0.未发起 1.已发起
	private String isregister;//是否注册	0.未注册 1.已注册
	private String isenable;//是否存在活动	0.不存在 1.存在
	private String isorder;//是否下单	0.未下单 1.已下单
	private String bargainid;//砍价id
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getIsmakebargain() {
		return ismakebargain;
	}
	public void setIsmakebargain(String ismakebargain) {
		this.ismakebargain = ismakebargain;
	}
	public String getIsregister() {
		return isregister;
	}
	public void setIsregister(String isregister) {
		this.isregister = isregister;
	}
	public String getIsenable() {
		return isenable;
	}
	public void setIsenable(String isenable) {
		this.isenable = isenable;
	}
	public String getBargainid() {
		return bargainid;
	}
	public void setBargainid(String bargainid) {
		this.bargainid = bargainid;
	}
	public String getIsorder() {
		return isorder;
	}
	public void setIsorder(String isorder) {
		this.isorder = isorder;
	}
	
	
}
