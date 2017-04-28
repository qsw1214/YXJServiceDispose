package com.youxianji.pojo;

public class PtShowRelation {
	private String ptid;//关系ID
	private ProdInfo prodInfo;//商品ID
	private ProdTags prodTags;//显示标签
	public String getPtid() {
		return ptid;
	}
	public void setPtid(String ptid) {
		this.ptid = ptid;
	}
	public ProdInfo getProdInfo() {
		return prodInfo;
	}
	public void setProdInfo(ProdInfo prodInfo) {
		this.prodInfo = prodInfo;
	}
	public ProdTags getProdTags() {
		return prodTags;
	}
	public void setProdTags(ProdTags prodTags) {
		this.prodTags = prodTags;
	}
	
}
