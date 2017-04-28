package com.youxianji.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class YxjUserBarginRules {
	private String id;//规则ID
	private String prodid;//砍价商品ID
	private BigDecimal rulesMoney;//单次砍价金额
	private BigDecimal lowestMoney;//单次砍价金额
	private Date begintime;//砍价开始时间
	private Date endtime;//砍价结束时间
	private String state;//状态 0.删除 1.正常
	private String rulesTitle;//砍价标题
	private String rulesDesc;//砍价描述
	private String rulesImageurl;//海报标题
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public BigDecimal getRulesMoney() {
		return rulesMoney;
	}
	public void setRulesMoney(BigDecimal rulesMoney) {
		this.rulesMoney = rulesMoney;
	}
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getRulesImageurl() {
		return rulesImageurl;
	}
	public void setRulesImageurl(String rulesImageurl) {
		this.rulesImageurl = rulesImageurl;
	}
	public BigDecimal getLowestMoney() {
		return lowestMoney;
	}
	public void setLowestMoney(BigDecimal lowestMoney) {
		this.lowestMoney = lowestMoney;
	}
	
}
