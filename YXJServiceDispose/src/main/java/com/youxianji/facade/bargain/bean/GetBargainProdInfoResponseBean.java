package com.youxianji.facade.bargain.bean;


public class GetBargainProdInfoResponseBean {
	private String isenable	;//是否存在活动	0.不存在 1.存在	
	private String rulesTitle;//砍价标题
	private String rulesDesc;//砍价描述
	private String lowestmoney;//砍价描述
	private String prodid;
	private String prodname;//商品名称
	private String imageUrl;//商品图片
	private String valuePrice;//商品原价
	private String prodstock;//库存数
	private String rulesMoney;//单次砍价金额
	private String endTime;//活动结束时间
	public String getIsenable() {
		return isenable;
	}
	public void setIsenable(String isenable) {
		this.isenable = isenable;
	}
	
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getValuePrice() {
		return valuePrice;
	}
	public void setValuePrice(String valuePrice) {
		this.valuePrice = valuePrice;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getRulesMoney() {
		return rulesMoney;
	}
	public void setRulesMoney(String rulesMoney) {
		this.rulesMoney = rulesMoney;
	}
	public String getRulesTitle() {
		return rulesTitle;
	}
	public void setRulesTitle(String rulesTitle) {
		this.rulesTitle = rulesTitle;
	}
	public String getRulesDesc() {
		return rulesDesc;
	}
	public void setRulesDesc(String rulesDesc) {
		this.rulesDesc = rulesDesc;
	}
	public String getProdstock() {
		return prodstock;
	}
	public void setProdstock(String prodstock) {
		this.prodstock = prodstock;
	}
	public String getLowestmoney() {
		return lowestmoney;
	}
	public void setLowestmoney(String lowestmoney) {
		this.lowestmoney = lowestmoney;
	}
	
	
}
