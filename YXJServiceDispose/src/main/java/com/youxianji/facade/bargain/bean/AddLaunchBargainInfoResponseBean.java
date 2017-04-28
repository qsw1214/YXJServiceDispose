package com.youxianji.facade.bargain.bean;

public class AddLaunchBargainInfoResponseBean {
	private String bargainid;
	private String isconcern;//是否砍过 0未砍过 1已砍过

	public String getBargainid() {
		return bargainid;
	}

	public void setBargainid(String bargainid) {
		this.bargainid = bargainid;
	}

	public String getIsconcern() {
		return isconcern;
	}

	public void setIsconcern(String isconcern) {
		this.isconcern = isconcern;
	}
	
}
