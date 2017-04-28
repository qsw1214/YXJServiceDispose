package com.youxianji.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class YxjUserBarginDetail {
	
	private String detailId;//砍价关联明细表ID
	private String bargainId;//发起砍价记录ID
	private String relationUserId;//关联分享用户ID
	private Date bargainTime;//砍价时间
	private BigDecimal barginMoney;//砍价金额
	private String bargainRulesId;//砍价活动id
	
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getRelationUserId() {
		return relationUserId;
	}
	public void setRelationUserId(String relationUserId) {
		this.relationUserId = relationUserId;
	}
	public Date getBargainTime() {
		return bargainTime;
	}
	public void setBargainTime(Date bargainTime) {
		this.bargainTime = bargainTime;
	}
	public BigDecimal getBarginMoney() {
		return barginMoney;
	}
	public void setBarginMoney(BigDecimal barginMoney) {
		this.barginMoney = barginMoney;
	}
	public String getBargainId() {
		return bargainId;
	}
	public void setBargainId(String bargainId) {
		this.bargainId = bargainId;
	}
	public String getBargainRulesId() {
		return bargainRulesId;
	}
	public void setBargainRulesId(String bargainRulesId) {
		this.bargainRulesId = bargainRulesId;
	}
	
}
